package com.altamirano.fabricio.marvelbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altamirano.fabricio.marvelbook.models.Thumbnail
import java.math.BigInteger

object Constants {

    const val ID_CHARACTER = "ID_CHARACTER"
    const val TITLE_CHARACTER = "TITLE_CHARACTER"
    const val DESCRIPTION_CHARACTER = "DESCRIPTION_CHARACTER"

    const val SHOWED_KEY = "SHOWED_KEY"
    const val HISTORY_SHARED = "HISTORY_SHARED"

    const val API_KEY = "38dd6369c9ad1a2531e78995d7987f6d"
    private val PRIVATE_KEY: String = "e76b81518b31c5a8dca94476ff774ef73026f1a4"
    val BASE_URL: String = "https://gateway.marvel.com/"

    val HASH_ID: String by lazy {
        val md = java.security.MessageDigest.getInstance("MD5")
        md.update(("1$PRIVATE_KEY$API_KEY").toByteArray())
        val hash = BigInteger(1, md.digest()).toString(16)
        hash
    }


    fun ViewGroup.inflate(idLayout: Int): View {
        return LayoutInflater.from(this.context).inflate(idLayout, this, false)
    }

    fun Thumbnail?.getAsUrl(): String? {
        if (this == null || this.extension.isNullOrEmpty() || this.path.isNullOrEmpty()) {
            return null
        }

        return "${this.path!!.replace("http://", "https://")}.${this.extension}"
    }

    fun Context.isShowed(idCharacter: String): Boolean {
        return this.getSharedPreferences(HISTORY_SHARED, Context.MODE_PRIVATE)
            .getBoolean("$SHOWED_KEY$idCharacter", false)
    }

    fun Context.setAsShowed(idCharacter: String) {
        this.getSharedPreferences(HISTORY_SHARED, Context.MODE_PRIVATE).edit()
            .putBoolean("$SHOWED_KEY$idCharacter", true).apply()
    }

}
