package com.rogerio.mymapapp.services.repository

import com.rogerio.mymapapp.services.ITaxiApi

class TaxisApiRepository(
    private val service: ITaxiApi
) : TaxisApiDataSource {
    override fun getTaxis(p1Lat: Double, p1Lon: Double, p2Lat: Double, p2Lon: Double) =
        service.getTaxis(p1Lat, p1Lon, p2Lat, p2Lon)

}