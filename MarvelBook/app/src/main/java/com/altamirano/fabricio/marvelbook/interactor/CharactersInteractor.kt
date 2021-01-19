package com.altamirano.fabricio.marvelbook.interactor

import com.altamirano.fabricio.marvelbook.interfaces.ICharactersInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersPresenter
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import com.altamirano.fabricio.marvelbook.services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersInteractor(private val presenter: ICharactersPresenter, private val service:IMarvelService) : ICharactersInteractor,
    Callback<ResponseCharacters> {

    private var offset: Int = 0

    override fun loadCharacters(nameStartsWith: String?) {
        if (nameStartsWith.isNullOrBlank())
            service.listCharacters(offset, this)
        else
            service.listCharacters(offset, this)
    }

    override fun onResponse(
        call: Call<ResponseCharacters>,
        response: Response<ResponseCharacters>
    ) {
        response.body()?.data?.count?.let {
            offset+=it
        }
        response.body()?.data?.results?.let {
            presenter.showResults(it)
        }
    }

    override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
        presenter.invalidOperation(t)
    }

}




