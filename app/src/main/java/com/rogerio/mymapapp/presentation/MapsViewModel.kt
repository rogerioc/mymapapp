package com.rogerio.mymapapp.presentation

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.rogerio.mymapapp.core.BaseViewModel
import com.rogerio.mymapapp.core.addTo
import com.rogerio.mymapapp.model.CarPositionViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber


class MapsViewModel(
    private val interactor: MyTaxisInteractor
) : BaseViewModel() {
    private lateinit var currentLatLng: LatLng
    private lateinit var finalPosition: LatLng
    val showLoading = ObservableInt(View.GONE)
    private val _closeCars = MutableLiveData<List<CarPositionViewEntity>>()
    val closesCars: LiveData<List<CarPositionViewEntity>>
        get() = _closeCars

    private fun fetchData() {
        showLoading.set(View.VISIBLE)
        interactor.getTaxis(
            currentLatLng.latitude,
            currentLatLng.longitude,
            finalPosition.latitude,
            finalPosition.longitude
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString(), "List")
                _closeCars.postValue(it)
                showLoading.set(View.GONE)
            }, {
                Timber.e(it.toString(), " List error")
                showLoading.set(View.GONE)
            }).addTo(this)
    }

    fun start() {
        //fetchData()
    }

    fun setPosition(currentLatLng: LatLng) {
        this.currentLatLng = currentLatLng
    }

    fun goPosition(position: LatLng?) {
        this.finalPosition = position ?: currentLatLng
        fetchData()
    }
}