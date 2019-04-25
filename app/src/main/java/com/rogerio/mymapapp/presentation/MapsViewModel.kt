package com.rogerio.mymapapp.presentation

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.rogerio.mymapapp.core.BaseViewModel
import com.rogerio.mymapapp.model.VehiclePositionViewEntity


class MapsViewModel(
    private val interactor: MyTaxisInteractor
) : BaseViewModel() {
    private lateinit var currentLatLng: LatLng
    private lateinit var finalPosition: LatLng
    val showLoading = ObservableInt(View.GONE)
    private val _selectedVehicle = MutableLiveData<VehiclePositionViewEntity>()
    val selectedVehicle: LiveData<VehiclePositionViewEntity>
        get() = _selectedVehicle


    fun start() {
        //fetchData()
    }

    fun setPosition(currentLatLng: LatLng) {

    }

    fun goPosition(position: LatLng?) {
    }

    fun setVehicle(vehiclePositionViewEntity: VehiclePositionViewEntity) {
        _selectedVehicle.postValue(vehiclePositionViewEntity)
        showLoading.set(View.GONE)
    }
}