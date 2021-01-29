package com.altamirano.dagger.api

import com.altamirano.dagger.domain.repository.CharactersRepository
import com.altamirano.dagger.exceptions.MarvelApiException
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.models.ResponseCharacters
import com.altamirano.dagger.util.Constants
import javax.inject.Inject

class MarvelRepositoryAPI @Inject constructor(private val apiService:IApiService) :CharactersRepository {

    override fun getCharactes(offset:Int): ArrayList<Character> {

      val apiResponse=  apiService.listCharacters(Constants.HASH_ID, offset).execute()

        if(apiResponse.isSuccessful){
            if (apiResponse.code() != 200 || apiResponse.body() == null) {
                throw getException(apiResponse.code())
            } else{
                val list = (apiResponse.body() as ResponseCharacters).data?.results!!
                return ArrayList<Character>().apply { addAll(list) }
            }
        }else{
         throw MarvelApiException(apiResponse.code(), "apiResponse", apiResponse.raw().message())
        }
    }

    override fun getCharacter(id: String): Character? {
        val apiResponse=  apiService.getCharacter(id,Constants.HASH_ID).execute()

        if(apiResponse.isSuccessful){
            if (apiResponse.code() != 200 || apiResponse.body() == null) {
                throw getException(apiResponse.code())
            } else{
                val item = (apiResponse.body() as ResponseCharacters).data?.results!![0]
                return item
            }
        }else{
            throw MarvelApiException(apiResponse.code(), "apiResponse", apiResponse.raw().message())
        }
    }

    private fun getException(code:Int):Exception{
        return when (code) {
            401 -> MarvelApiException(401,"Invalid Referer | Invalid Hash","Occurs when a referrer which is not valid for the passed apikey parameter is sent. or Occurs when a ts, hash and apikey parameter are sent but the hash is not valid per the above hash generation rule.")
            403 -> MarvelApiException(403,"Forbidden","Occurs when a user with an otherwise authenticated request attempts to access an endpoint to which they do not have access.")
            405 -> MarvelApiException(405,"Method Not Allowed","Occurs when an API endpoint is accessed using an HTTP verb which is not allowed for that endpoint.")
            409 -> MarvelApiException(409,"Missing API Key | Missing Hash | Missing Timestamp","Occurs when the apikey parameter is not included with a request.")
            404 -> MarvelApiException(404,"Character not found.", "Character not found.")
            else -> Exception("Error when caller api")
        }
    }
}