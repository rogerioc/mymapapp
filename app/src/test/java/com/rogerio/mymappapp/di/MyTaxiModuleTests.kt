package com.rogerio.mymappapp.di

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import com.rogerio.mymapapp.di.myMappModule
import com.rogerio.mymapapp.presentation.MapsViewModel
import com.rogerio.mymapapp.presentation.MyTaxisInteractor
import com.rogerio.mymapapp.services.ITaxiApi
import com.rogerio.mymapapp.services.TaxiApi
import com.rogerio.mymapapp.services.repository.TaxisApiDataSource
import com.rogerio.mymapapp.services.repository.TaxisApiRepository
import com.rogerio.mymapapp.vehiclesList.presentation.VehiclesViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyTaxiModuleTests: KoinTest {

    @Before
    fun setUp() {
        val app = mock<Application>()
        startKoin {
            androidContext(app)
            modules(myMappModule)
        }
    }

    @Test
    fun shouldCreateService() {
        val service: ITaxiApi = get()
        Assert.assertNotNull(service)
    }

    @Test
    fun shouldCreateRepository() {
        val repository: TaxisApiDataSource = get()
        Assert.assertNotNull(repository)
    }

    @Test
    fun shouldCreateInteractor() {
        val interactor: MyTaxisInteractor = get()
        Assert.assertNotNull(interactor)
    }


    @Test
    fun shouldCreateMapsViewModel() {
        val viewModel: MapsViewModel = get()
        Assert.assertNotNull(viewModel)
    }

    @Test
    fun shouldCreateVehiclesViewModel() {
        val viewModel: VehiclesViewModel = get()
        Assert.assertNotNull(viewModel)
    }

    @After
    fun stop(){
        stopKoin()
    }
}