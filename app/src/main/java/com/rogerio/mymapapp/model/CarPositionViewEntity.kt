package com.rogerio.mymapapp.model

data class CarPositionViewEntity(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val fleetType: String = "",
    val heading: Double = 0.0
)
