package com.rogerio.mymappapp.services.models

import com.squareup.moshi.Json

data class Coordinate(@Json(name = "latitude")
                      val latitude: Double = 0.0,
                      @Json(name = "longitude")
                      val longitude: Double = 0.0)