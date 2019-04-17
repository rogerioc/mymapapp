package com.rogerio.mymappapp

import androidx.multidex.MultiDexApplication
import com.rogerio.mymappapp.di.myMappModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMapApplication: MultiDexApplication() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MyMapApplication)
            // modules
            modules(myMappModule)
        }
    }
}