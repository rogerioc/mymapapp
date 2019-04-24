package com.rogerio.mymapapp.presentation

import com.rogerio.mymapapp.model.CarPositionViewEntity
import com.rogerio.mymapapp.services.models.PoiList
import com.rogerio.mymapapp.services.models.PoiListItem
import com.rogerio.mymapapp.services.repository.TaxisApiDataSource
import io.reactivex.Single

class MyTaxisInteractor(
    private val repository: TaxisApiDataSource
) {
    fun getTaxis(
        p1Lat: Double,
        p1Lon: Double,
        p2Lat: Double,
        p2Lon: Double
    ): Single<List<CarPositionViewEntity>> {
        return repository.getTaxis(p1Lat, p1Lon, p2Lat, p2Lon)
            .map(::mapperList)
    }

    private fun mapperList(poiList: PoiList): List<CarPositionViewEntity>? =
            poiList.poiList?.map(::mapperCar)

    private fun mapperCar(poiListItem: PoiListItem) = CarPositionViewEntity(
        latitude = poiListItem.coordinate.latitude,
        longitude = poiListItem.coordinate.longitude,
        fleetType = poiListItem.fleetType,
        heading = poiListItem.heading
    )
}