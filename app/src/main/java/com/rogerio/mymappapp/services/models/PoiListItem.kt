package com.rogerio.mymappapp.services.models

import com.squareup.moshi.Json

data class PoiListItem(@Json(name = "coordinate")
                       val coordinate: Coordinate,
                       @Json(name = "fleetType")
                       val fleetType: String = "",
                       @Json(name = "heading")
                       val heading: Double = 0.0,
                       @Json(name = "id")
                       val id: Int = 0)