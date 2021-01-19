package com.altamirano.fabricio.marvelbook.services

import com.altamirano.fabricio.marvelbook.Constants
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelService private constructor() : IMarvelService {
    companion object {
        val instance by lazy {
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
                        val error = when (response.code()) {
                            401 -> Exception401()
                            403 -> Exception403()
                            405 -> Exception405()
                            409 -> Exception409()
                            else -> Exception("Error when caller api")
                        }
                        listener.onFailure(call, error)
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
                    val error = when (response.code()) {
                        401 -> Exception401()
                        404 -> Exception404()
                        403 -> Exception403()
                        405 -> Exception405()
                        409 -> Exception409()
                        else -> Exception("Error when caller api")
                    }
                    listener.onFailure(call, error)
                } else {
                    listener.onResponse(call, response)
                }
            }

            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                listener.onFailure(call, t)
            }
        })
    }

}