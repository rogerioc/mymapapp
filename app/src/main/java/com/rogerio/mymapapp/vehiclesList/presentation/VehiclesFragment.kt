package com.rogerio.mymapapp.vehiclesList.presentation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogerio.mymapapp.R
import com.rogerio.mymapapp.databinding.VehiclesFragmentBindingImpl
import com.rogerio.mymapapp.presentation.MapsActivity
import com.rogerio.mymapapp.presentation.MapsActivity.Companion.VEHICLE_DATA
import com.rogerio.mymapapp.vehiclesList.presentation.list.VehiclesListAdapter
import kotlinx.android.synthetic.main.vehicles_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehiclesFragment : Fragment() {

    companion object {
        fun newInstance() = VehiclesFragment()
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var binding: VehiclesFragmentBindingImpl
    private val viewModel by viewModel<VehiclesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.vehicles_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = VehiclesListAdapter()
        val layoutManager = LinearLayoutManager(context)
        list_vehicles.hasFixedSize()
        list_vehicles.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        list_vehicles.layoutManager = layoutManager
        list_vehicles.adapter = adapter
        adapter.selecteItem.observe(this, Observer {
            viewModel.selectedVehicle(it)
        })
        viewModel.selectedVehicle.observe(this, Observer {
            val intent = Intent(activity, MapsActivity::class.java)
            intent.putExtra(VEHICLE_DATA, it)
            startActivity(intent)
        })
    }

    override fun onStart() {
        super.onStart()

        if (
            activity?.let {
                checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            viewModel.start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                viewModel.start()
            }
        } else {
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                Toast.makeText(context, getString(R.string.text_permission), Toast.LENGTH_LONG).show()
            }
        }
    }

}
