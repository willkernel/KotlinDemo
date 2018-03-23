package com.willkernel.app.kotlindemo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.willkernel.app.kotlindemo.R
import com.willkernel.app.kotlindemo.model.Forecast
import com.willkernel.app.kotlindemo.model.ForecastList
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by willkernel
 * on 2018/3/23.
 */
class ForecastListAdapter(val items: ForecastList, val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return VH(view, itemClick)
    }

    //    val size: Int get() = dailyForecast.size
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindForecast(position, holder)
    }

    //    operator fun get(position: Int) = dailyForecast[position]
    private fun bindForecast(position: Int, holder: VH) {
        with(items[position]) {
            // with(items.dailyForecast[position]) {
//            holder.itemView.date.text = "$date - $description - $high - $low"
            holder.bindForecast(this)
        }
    }

    class VH(itemView: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(itemView) {
//        private val iconView: ImageView
//        private val dateView: TextView
//        private val descriptionView: TextView
//        private val maxTemperatureView: TextView
//        private val minTemperatureView: TextView

//        init {
//            iconView = itemView.findViewById(R.id.icon)
//            dateView = itemView.findViewById(R.id.date)
//            descriptionView = itemView.findViewById(R.id.description)
//            maxTemperatureView = itemView.findViewById(R.id.maxTemperature)
//            minTemperatureView = itemView.findViewById(R.id.minTemperature)
//        }

//        fun bindForecast(forecast: Forecast) {
//            with(forecast) {
//                Picasso.with(itemView.context).load(iconUrl).into(iconView)
//                dateView.text = date
//                descriptionView.text = description
//                maxTemperatureView.text = high.toString()
//                minTemperatureView.text = low.toString()
//                itemView.setOnClickListener { itemClick(this) }
//            }
//        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = high.toString()
                itemView.minTemperature.text = low.toString()
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    public interface OnItemClickListener {
        //对应的操作符为 itemClick(forecast)
        operator fun invoke(forecast: Forecast)
    }
}