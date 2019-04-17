package com.rogerio.mymappapp.services.repository

import com.rogerio.mymappapp.services.models.PoiList
import io.reactivex.Single

interface TaxisApiDataSource {
    fun getTaxis(p1Lat: Double,
                 p1Lon: Double,
                 p2Lat: Double,
                 p2Lon: Double): Single<PoiList>
}