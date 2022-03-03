package com.chilisoft.weatherx.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chilisoft.weatherx.databinding.RvItemHourlyForecastBinding
import com.chilisoft.weatherx.domain.model.HourlyForecast

class HourlyForecastAdapter(private val dataSet: MutableList<HourlyForecast>) : RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    fun updateDataSet(newDatSet: MutableList<HourlyForecast>?) {
        if (newDatSet == null) return
        dataSet.clear()
        dataSet.addAll(newDatSet)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RvItemHourlyForecastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HourlyForecast) {
            binding.weakDay.text = item.weakDay
            binding.icon.load(item.icon) { crossfade(true) }
            binding.temperature.text = item.temperature
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemHourlyForecastBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
