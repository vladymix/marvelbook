package com.altamirano.fabricio.marvelbook.interactor

import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDeatilsInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsPresenter
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import com.altamirano.fabricio.marvelbook.services.IMarvelService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsInteractor(private var presenter:ICharactersDetailsPresenter,private val service: IMarvelService) :ICharactersDeatilsInteractor,
    Callback<ResponseCharacters> {

    override fun loadCharacter(id: String) {
        service.loadCharacter(id, this)
    }

    override fun onResponse(
        call: Call<ResponseCharacters>,
        response: Response<ResponseCharacters>
    ) {
        response.body()?.data?.results?.let {
            if(it.isEmpty()){
                presenter.invalidOperation(Exception("Data not found"))
            }else{
                presenter.showResult(it[0])
            }
        }
    }

    override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
        presenter.invalidOperation(t)
    }
}