package com.rogerio.mymapapp.vehiclesList.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.rogerio.mymapapp.R
import com.rogerio.mymapapp.databinding.RowVehicleBinding
import com.rogerio.mymapapp.model.VehiclePositionViewEntity

class VehiclesListAdapter: RecyclerView.Adapter<VehiclesListAdapter.ViewHolder>() {
    private lateinit var items: List<VehiclePositionViewEntity>
    private val _selectedItem = MutableLiveData<VehiclePositionViewEntity>()
    val selecteItem: LiveData<VehiclePositionViewEntity>
        get() = _selectedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        VehicleViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is VehicleViewHolder && items.size > position) {
            holder.bind(VehicleViewModel(
                items[position]
            ))

            holder.itemView.setOnClickListener{
                _selectedItem.postValue(items[position])
            }

        }
    }

    private fun update(items: List<VehiclePositionViewEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<VehiclePositionViewEntity>?) {
            items?.let {
                val adapter = adapter as VehiclesListAdapter
                adapter.update(items)
            }
        }
    }

    class VehicleViewHolder(
        private val parent: ViewGroup,
        private val binding: RowVehicleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_vehicle,
            parent,
            false
        )
    ) : ViewHolder(binding.root) {
        fun bind(item: VehicleViewModel) {
            binding.viewModel = item
        }
    }
}