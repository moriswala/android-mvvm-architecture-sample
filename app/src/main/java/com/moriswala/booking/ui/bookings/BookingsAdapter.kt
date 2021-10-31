package com.moriswala.booking.ui.bookings

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.databinding.ItemBookingBinding

class BookingsAdapter(private val listener: ItemListener) : RecyclerView.Adapter<BookingViewHolder>() {

    interface ItemListener {
        fun onBookingClicked(bookingId: Int)
    }

    private val items = ArrayList<Booking>()

    fun setItems(items: ArrayList<Booking>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding: ItemBookingBinding = ItemBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) = holder.bind(items[position])
}

class BookingViewHolder(private val itemBinding: ItemBookingBinding, private val listener: BookingsAdapter.ItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var booking: Booking

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Booking) {
        this.booking = item
        itemBinding.name.text = item.owner
        itemBinding.fromTo.text = """${item.depart_city_id} - ${item.arrival_city_id}"""
        Glide.with(itemBinding.root)
            .load(item.logo)
            .into(itemBinding.image)
        itemBinding.departTime.text = "${item.depart_date_time}"
    }

    override fun onClick(v: View?) {
        listener.onBookingClicked(booking.id)
    }
}

