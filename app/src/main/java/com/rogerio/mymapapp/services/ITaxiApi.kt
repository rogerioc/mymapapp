package com.rogerio.mymapapp.services

import com.rogerio.mymapapp.services.models.PoiList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ITaxiApi {

    @GET(".")
    fun getTaxis(@Query("p1Lat") p1Lat: Double,
                 @Query("p2Lon") p1Lon: Double,
                 @Query("p2Lat") p2Lat: Double,
                 @Query("p1Lon") p2Lon: Double): Single<PoiList>
}