package com.rogerio.mymapapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VehiclePositionViewEntity(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val fleetType: String = "",
    val heading: Double = 0.0
): Parcelable
