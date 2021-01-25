package com.altamirano.fabricio.marvelbook.exceptions

class MarvelApiException(val code:Int,val title:String?, message: String) : BaseException(message)