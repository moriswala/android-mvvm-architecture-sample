package com.moriswala.booking.ui.newticket.flightselection

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moriswala.booking.R
import com.moriswala.booking.data.entities.Flight
import com.moriswala.booking.databinding.ItemBookingBinding
import com.moriswala.booking.databinding.ItemFlightBinding

class FlightsAdapter(private val listener: ItemListener) : RecyclerView.Adapter<FlightViewHolder>() {

    interface ItemListener {
        fun onFlightSelected(flight: Flight)
    }

    private val items = ArrayList<Flight>()

    fun setItems(items: ArrayList<Flight>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding: ItemFlightBinding = ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) = holder.bind(items[position])
}

class FlightViewHolder(private val itemBinding: ItemFlightBinding, private val listener: FlightsAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var flight: Flight
    var context :Context? = null
    init {
        itemBinding.root.setOnClickListener(this)
        context = itemBinding.root.context
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Flight) {
        this.flight = item

        itemBinding.name.text = item.owner
        itemBinding.fromTo.text = """${item.depart_city_id} - ${item.arrival_city_id}"""
        Glide.with(itemBinding.root)
            .load(item.logo)
            .into(itemBinding.image)
        itemBinding.departTime.text = "${item.depart_date_time}"
        itemBinding.priceText.text = "${context?.getString(R.string.currency)} ${flight.fare}"
    }

    override fun onClick(v: View?) {
        listener.onFlightSelected(flight)
    }
}

