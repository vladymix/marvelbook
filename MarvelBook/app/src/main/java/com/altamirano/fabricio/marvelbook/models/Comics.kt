package com.altamirano.fabricio.marvelbook.models

data class Comics (
    var available: String? = null,
    var returned: String? = null,
    var collectionURI: String? = null,
    var items: List<Item>? = null,
)