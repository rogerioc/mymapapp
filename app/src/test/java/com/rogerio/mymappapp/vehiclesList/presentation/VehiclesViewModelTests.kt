package com.rogerio.mymappapp.vehiclesList.presentation

import android.view.View
import com.nhaarman.mockitokotlin2.whenever
import com.rogerio.mymapapp.presentation.MyTaxisInteractor
import com.rogerio.mymapapp.services.models.PoiList
import com.rogerio.mymapapp.services.repository.TaxisApiDataSource
import com.rogerio.mymapapp.vehiclesList.presentation.VehiclesViewModel
import com.rogerio.mymappapp.helpers.RxImmediateSchedulerRule
import com.rogerio.mymappapp.helpers.TestFactorys.getVehiclesResponseList
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class VehiclesViewModelTests {

    @Mock
    private lateinit var repository: TaxisApiDataSource

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var interactor: MyTaxisInteractor

    private lateinit var viewModel: VehiclesViewModel

    private val p1LAt = 53.694865
    private val p2LAt = 53.394655
    private val p1Lon = 9.757589
    private val p2Lon = 10.099891
    @Before
    fun setTup() {

        interactor = MyTaxisInteractor(repository)
        viewModel = VehiclesViewModel(interactor)
    }

    @Test
    fun shouldStartOk() {
        val list = getVehiclesResponseList()
        whenever(
            repository.getTaxis(
                p1LAt,
                p1Lon,
                p2LAt,
                p2Lon
            )
        ).thenReturn(
            Single.just(list)
        )
        viewModel.start()


        assertEquals(viewModel.vehicleList.size, 1)
        assertEquals(viewModel.showLoading.get(), View.GONE)
    }


    @Test
    fun shouldStartError() {

        whenever(
            repository.getTaxis(
                p1LAt,
                p1Lon,
                p2LAt,
                p2Lon
            )
        ).thenReturn(
            Single.error<PoiList>(Exception())
        )
        viewModel.start()

        assertTrue(viewModel.vehicleList.isEmpty())
        assertEquals(viewModel.showLoading.get(), View.GONE)
    }
}