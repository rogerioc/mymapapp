package com.rogerio.mymappapp.services

import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object TaxiApi {
    inline fun createService(okHttpClient: OkHttpClient, moshi: Moshi, baseUrl: String): ITaxiApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(ITaxiApi::class.java)
    }
}