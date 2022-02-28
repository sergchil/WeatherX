package com.chilisoft.weatherx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chilisoft.weatherx.databinding.RvItemHourlyForecastBinding

class WeaklyForecastAdapter(private val dataSet: MutableList<HourlyForecastDto>) : RecyclerView.Adapter<WeaklyForecastAdapter.ViewHolder>() {

    public fun updateDataSet(newDatSet: MutableList<HourlyForecastDto>) {
        dataSet.clear()
        dataSet.addAll(newDatSet)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RvItemHourlyForecastBinding) : RecyclerView.ViewHolder(binding.root) {
        public fun bind(item: HourlyForecastDto) {
            binding.weakDay.text = item.weakDay
//            binding.icon.text = item.icon
            binding.temperature.text = item.temperature
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemHourlyForecastBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
