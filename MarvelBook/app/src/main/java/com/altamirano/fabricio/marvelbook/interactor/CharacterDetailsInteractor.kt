package com.altamirano.fabricio.marvelbook.interactor

import android.widget.ImageView
import com.altamirano.fabricio.marvelbook.Constants.getAsUrl
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDeatilsInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsPresenter
import com.altamirano.fabricio.marvelbook.models.ResponseCharacters
import com.altamirano.fabricio.marvelbook.models.Thumbnail
import com.altamirano.fabricio.marvelbook.services.MarvelService
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CharacterDetailsInteractor(private var presenter:ICharactersDetailsPresenter) :ICharactersDeatilsInteractor,
    Callback<ResponseCharacters> {

    private val service: MarvelService by lazy {
        MarvelService.instance
    }

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