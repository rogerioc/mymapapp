package com.rogerio.mymapapp.presentation

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.rogerio.mymapapp.R
import com.rogerio.mymapapp.model.VehiclePositionViewEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var lastLocation: Location? = null

    private val viewModel by viewModel<MapsViewModel>()

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        const val VEHICLE_DATA = "VEHICLE_DATA"
    }


    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val vehiclePositionViewEntity: VehiclePositionViewEntity = intent.getParcelableExtra(VEHICLE_DATA)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.setVehicle(vehiclePositionViewEntity)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewModel.selectedVehicle.observe(this, Observer {
            val place = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(place).title(it.fleetType))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,15.0F))
            mMap.animateCamera(CameraUpdateFactory.zoomIn())
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0F), 2000, null)
        })
    }

    private fun setUpMap() {
//        if (checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//
//        }else {
//            mMap.isMyLocationEnabled = true
//            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
//                // Got last known location. In some rare situations this can be null.
//                // 3
//                if (location != null) {
//                    lastLocation = location
//                    val currentLatLng = LatLng(location.latitude, location.longitude)
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 8f))
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 8f))
//                    //viewModel.setPosition(currentLatLng)
//                }
//            }
//        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        mMap.setOnMapClickListener {
            //viewModel.goPosition(it)
            mMap.clear()
        }
        //mMap.addMarker(MarkerOptions().position(place).title(it.fleetType))
        setUpMap()
    }

    private fun placeMarkerOnMap(location: LatLng) {
        // 1
        val markerOptions = MarkerOptions().position(location)
        // 2
        mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }
}
