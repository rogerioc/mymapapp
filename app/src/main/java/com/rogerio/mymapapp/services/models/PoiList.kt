package com.rogerio.mymapapp.services.models

import com.squareup.moshi.Json

data class PoiList(@Json(name = "poiList")
                   val poiList: List<PoiListItem>?)