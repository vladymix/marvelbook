package com.altamirano.fabricio.marvelbook.services

import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import retrofit2.Callback

interface IMarvelService {
    fun listCharacters(offset: Int, listener: Callback<ResponseCharacters>)
    fun loadCharacter(id: String, listener: Callback<ResponseCharacters>)
}