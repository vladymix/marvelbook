package com.altamirano.fabricio.marvelbook.models

data class ResponseCharacters (
    var code: String? = null,
    var status: String? = null,
    var copyright: String? = null,
    var attributionText: String? = null,
    var attributionHTML: String? = null,
    var data: Data? = null,
    var etag: String? = null,
)