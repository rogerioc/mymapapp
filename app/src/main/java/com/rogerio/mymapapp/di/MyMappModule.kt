package com.rogerio.mymapapp.di

import com.rogerio.mymapapp.BuildConfig
import com.rogerio.mymapapp.presentation.MapsViewModel
import com.rogerio.mymapapp.presentation.MyTaxisInteractor
import com.rogerio.mymapapp.services.TaxiApi.createService
import com.rogerio.mymapapp.services.repository.TaxisApiDataSource
import com.rogerio.mymapapp.services.repository.TaxisApiRepository
import com.rogerio.mymapapp.vehiclesList.presentation.VehiclesViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

const val OK_HTTP_CLIENT = "okHttpClient"
const val MOSHI = "moshi"

const val TAXIS_SCOPE = "taxisScope"

@JvmField
val myMappModule = module {
    // -- Service --//
    single {
        createService(
            get(named(OK_HTTP_CLIENT)),
            get(named(MOSHI)),
            BuildConfig.BASE_URL
        )
    }
    single(named(MOSHI)) {
        getMoshi()
    }

    single(named(OK_HTTP_CLIENT)) {
        getOkHttpClient()
    }

    factory<TaxisApiDataSource> {
        TaxisApiRepository(get())
    }

    // -- Presentations --//
    factory {
        MyTaxisInteractor(get())
    }

    factory {
        MapsViewModel()
    }

    factory {
        VehiclesViewModel(get())
    }
}

private fun getMoshi(): Moshi {
    return Moshi.Builder()
        .build()
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {

        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(
                logging
            )
        }

    }.build()
}