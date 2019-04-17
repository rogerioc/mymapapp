package com.rogerio.mymappapp.presentation

import com.rogerio.mymappapp.core.BaseViewModel
import com.rogerio.mymappapp.core.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class MapsViewModel(
    private val interactor: MyTaxisInteractor
): BaseViewModel() {


    private fun fetchData() {
        interactor.getTaxis(-19.9310423, -43.9572016, -19.9280467, -43.9503995)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString(), "List")

            }, {
                Timber.e(it.toString(), " List error")

            }).addTo(this)
    }

    fun start() {
        fetchData()
    }
}