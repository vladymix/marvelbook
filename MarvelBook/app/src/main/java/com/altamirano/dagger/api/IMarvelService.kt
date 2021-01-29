package com.altamirano.dagger.api

import com.altamirano.dagger.models.ResponseCharacters
import retrofit2.Callback

interface IMarvelService {
    fun listCharacters(offset: Int, listener: Callback<ResponseCharacters>)
    fun loadCharacter(id: String, listener: Callback<ResponseCharacters>)
}