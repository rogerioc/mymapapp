package com.rogerio.mymapapp.presentation

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rogerio.mymapapp.core.BaseViewModel
import com.rogerio.mymapapp.model.VehiclePositionViewEntity


class MapsViewModel : BaseViewModel() {
    val showLoading = ObservableInt(View.GONE)
    private val _selectedVehicle = MutableLiveData<VehiclePositionViewEntity>()
    val selectedVehicle: LiveData<VehiclePositionViewEntity>
        get() = _selectedVehicle

    fun setVehicle(vehiclePositionViewEntity: VehiclePositionViewEntity) {
        _selectedVehicle.postValue(vehiclePositionViewEntity)
        showLoading.set(View.GONE)
    }
}