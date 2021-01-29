
package com.altamirano.dagger.models

import android.provider.CalendarContract.Events


class Character (
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var resourceURI: String? = null,
    var urls: List<Url>? = null,
    var thumbnail: Thumbnail? = null,
    var comics: Comics? = null,
    var stories: Stories? = null,
    var events: Events? = null,
    var series: Series? = null
)