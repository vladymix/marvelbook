package com.altamirano.fabricio.marvelbook.services

import com.altamirano.fabricio.marvelbook.Constants
import com.altamirano.fabricio.marvelbook.exceptions.MarvelApiException
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelService private constructor() : IMarvelService {
    companion object {
        val instance:IMarvelService by lazy {
            MarvelService()
        }
    }

    private var api: IApiService = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(IApiService::class.java)

    override fun listCharacters(offset: Int, listener: Callback<ResponseCharacters>) {
        api.listCharacters(Constants.HASH_ID, offset)
            .enqueue(object : Callback<ResponseCharacters> {
                override fun onResponse(
                    call: Call<ResponseCharacters>,
                    response: Response<ResponseCharacters>
                ) {
                    if (response.code() != 200 || response.body() == null) {
                        listener.onFailure(call, getException(response.code()))
                    } else {
                        listener.onResponse(call, response)
                    }
                }

                override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                    listener.onFailure(call, t)
                }

            })
    }

    override fun loadCharacter(id: String, listener: Callback<ResponseCharacters>) {
        api.getCharacter(id, Constants.HASH_ID).enqueue(object : Callback<ResponseCharacters> {
            override fun onResponse(
                call: Call<ResponseCharacters>,
                response: Response<ResponseCharacters>
            ) {
                if (response.code() != 200 || response.body() == null) {
                    listener.onFailure(call, getException(response.code()))
                } else {
                    listener.onResponse(call, response)
                }
            }

            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                listener.onFailure(call, t)
            }
        })
    }

    private fun getException(code:Int):Exception{
      return when (code) {
            401 -> MarvelApiException(401,"Invalid Referer | Invalid Hash","Occurs when a referrer which is not valid for the passed apikey parameter is sent. or Occurs when a ts, hash and apikey parameter are sent but the hash is not valid per the above hash generation rule.")
            403 ->MarvelApiException(403,"Forbidden","Occurs when a user with an otherwise authenticated request attempts to access an endpoint to which they do not have access.")
            405 -> MarvelApiException(405,"Method Not Allowed","Occurs when an API endpoint is accessed using an HTTP verb which is not allowed for that endpoint.")
            409 ->MarvelApiException(409,"Missing API Key | Missing Hash | Missing Timestamp","Occurs when the apikey parameter is not included with a request.")
            404 -> MarvelApiException(404,"Character not found.", "Character not found.")
            else -> Exception("Error when caller api")
        }
    }

}