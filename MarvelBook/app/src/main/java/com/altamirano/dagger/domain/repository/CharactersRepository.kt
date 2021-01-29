
package com.altamirano.dagger.domain.repository

import com.altamirano.dagger.models.Character
import com.altamirano.dagger.exceptions.ConnectionException
import com.altamirano.dagger.exceptions.MarvelApiException


interface CharactersRepository {

    @Throws(MarvelApiException::class, ConnectionException::class)
    fun getCharactes(offset:Int): ArrayList<Character>

    @Throws(MarvelApiException::class, ConnectionException::class)
    fun getCharacter(id:String): Character?

}