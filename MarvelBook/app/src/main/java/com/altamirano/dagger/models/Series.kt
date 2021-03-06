package com.altamirano.dagger.models

data class Series (
    var available: String? = null,
    var returned: String? = null,
    var collectionURI: String? = null,
    var items: List<Item>? = null
)