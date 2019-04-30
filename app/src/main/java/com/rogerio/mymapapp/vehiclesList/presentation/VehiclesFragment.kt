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
        viewModel.start()
    }

}
