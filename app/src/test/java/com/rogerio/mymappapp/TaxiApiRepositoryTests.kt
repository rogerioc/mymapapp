package com.rogerio.mymappapp

import com.nhaarman.mockitokotlin2.verify
import com.rogerio.mymappapp.helpers.RxImmediateSchedulerRule
import com.rogerio.mymapapp.services.ITaxiApi
import com.rogerio.mymapapp.services.TaxiApi
import com.rogerio.mymapapp.services.repository.TaxisApiRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TaxiApiRepositoryTests {

    @Rule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    private lateinit var service: ITaxiApi

    private lateinit var taxiApiRepository: TaxisApiRepository

    @Before
    fun setUp() {
        taxiApiRepository = TaxisApiRepository(service)
    }

    @Test
    fun getTaxis() {
        taxiApiRepository.getTaxis(0.0,0.0,0.0,0.0)
        verify(service).getTaxis(0.0,0.0,0.0,0.0)
    }
}
