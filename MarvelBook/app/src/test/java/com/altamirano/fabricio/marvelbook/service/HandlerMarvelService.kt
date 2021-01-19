package com.altamirano.fabricio.marvelbook.service

import java.io.InputStreamReader


class HandlerMarvelService(path: String) {
    val content: String
    init {

        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }

}