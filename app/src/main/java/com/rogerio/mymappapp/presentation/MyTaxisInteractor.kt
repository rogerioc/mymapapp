package com.rogerio.mymappapp.presentation

import com.rogerio.mymappapp.services.models.PoiList
import com.rogerio.mymappapp.services.repository.TaxisApiDataSource
import io.reactivex.Single

class MyTaxisInteractor(
    private val repository: TaxisApiDataSource
) {
    fun getTaxis(
        p1Lat: Double,
        p1Lon: Double,
        p2Lat: Double,
        p2Lon: Double
    ): Single<PoiList> {
        return repository.getTaxis(p1Lat, p1Lon, p2Lat, p2Lon)
    }
}