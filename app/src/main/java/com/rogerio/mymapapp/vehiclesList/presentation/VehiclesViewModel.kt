package com.rogerio.mymapapp.vehiclesList.presentation

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rogerio.mymapapp.core.BaseViewModel
import com.rogerio.mymapapp.core.addTo
import com.rogerio.mymapapp.model.VehiclePositionViewEntity
import com.rogerio.mymapapp.presentation.MyTaxisInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class VehiclesViewModel(
    private val interactor: MyTaxisInteractor
) : BaseViewModel() {

    val showLoading = ObservableInt(View.GONE)
    private val _closeCars = MutableLiveData<List<VehiclePositionViewEntity>>()
    val closesCars: LiveData<List<VehiclePositionViewEntity>>
        get() = _closeCars
    private val _selectedVehicle = MutableLiveData<VehiclePositionViewEntity>()
    val selectedVehicle: LiveData<VehiclePositionViewEntity>
        get() = _selectedVehicle

    val vehicleList: ArrayList<VehiclePositionViewEntity> = ArrayList()

    init {

    }

    private fun fetch() {

        showLoading.set(View.VISIBLE)
        interactor.getTaxis(
            53.694865,
            9.757589,
            53.394655,
            10.099891
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString(), "List")
                vehicleList.addAll(it)
                _closeCars.postValue(it)
                showLoading.set(View.GONE)
                notifyChange()
            }, {
                Timber.e(it.toString(), " List error")
                showLoading.set(View.GONE)
            }).addTo(this)
    }

    fun start() {
        fetch()
    }


    fun selectedVehicle(vehiclePositionViewEntity: VehiclePositionViewEntity?) {
        _selectedVehicle.postValue(vehiclePositionViewEntity)
    }

}
