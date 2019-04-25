package com.rogerio.mymapapp.vehiclesList.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rogerio.mymapapp.R
import kotlinx.android.synthetic.main.vehicles_activity.*

class VehiclesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicles_activity)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VehiclesFragment.newInstance())
                .commitNow()
        }
    }

}
