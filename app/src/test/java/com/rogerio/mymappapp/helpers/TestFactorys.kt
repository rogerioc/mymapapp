package com.rogerio.mymappapp.helpers

import com.rogerio.mymapapp.model.VehiclePositionViewEntity
import com.rogerio.mymapapp.services.models.Coordinate
import com.rogerio.mymapapp.services.models.PoiList
import com.rogerio.mymapapp.services.models.PoiListItem

object TestFactorys {
    fun getVehiclesList(): List<VehiclePositionViewEntity> {
        val list: MutableList<VehiclePositionViewEntity> = mutableListOf()
        list.add(getVehicle())
        return list
    }

    fun getVehiclesResponseList(): PoiList = PoiList(
            poiList = getPoiList()
        )

    private fun getPoiList(): List<PoiListItem>? {
        val list: MutableList<PoiListItem> = mutableListOf()
        list.add(getPoiItem())
        return list
    }

    private fun getPoiItem(): PoiListItem = PoiListItem(
        coordinate = getCoordinate(),
        heading = 1.0,
        fleetType = "",
        id = 1
    )

    private fun getCoordinate(): Coordinate = Coordinate(
        latitude = 0.0,
        longitude = 0.0
    )

    fun getVehicle() = VehiclePositionViewEntity(
        latitude = 0.0,
        longitude = 0.0,
        fleetType = "",
        heading = 0.0
    )
}