package com.altamirano.dagger.exceptions

class MarvelApiException(val code:Int,val title:String?, message: String) : BaseException(message)