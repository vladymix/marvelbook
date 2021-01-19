package com.altamirano.fabricio.marvelbook.services

import com.altamirano.fabricio.marvelbook.Constants
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {
    @GET("/v1/public/characters?ts=1&apikey=${Constants.API_KEY}")
    fun listCharactersStartWith(@Query("hash")hash:String,@Query("nameStartsWith")startWith: String): Call<ResponseCharacters>

    @GET("/v1/public/characters?ts=1&apikey=${Constants.API_KEY}")
    fun listCharacters(@Query("hash")hash:String, @Query("offset")offset:Int): Call<ResponseCharacters>

    @GET("/v1/public/characters/{idchar}?ts=1&apikey=${Constants.API_KEY}")
    fun getCharacter(@Path("idchar")idCharacter:String, @Query("hash")hash:String): Call<ResponseCharacters>

}