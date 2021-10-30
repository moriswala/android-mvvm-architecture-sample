package com.moriswala.booking.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.databinding.ItemCharacterBinding

class CharactersAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<Booking>()

    fun setItems(items: ArrayList<Booking>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: ItemCharacterBinding, private val listener: CharactersAdapter.CharacterItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: Booking

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Booking) {
        this.character = item
        itemBinding.name.text = item.owner
        itemBinding.fromTo.text = """${item.depart_city_id} - ${item.arrival_city_id}"""
//        Glide.with(itemBinding.root)
//            .load(item.image)
//            .transform(CircleCrop())
//            .into(itemBinding.image)
        itemBinding.departTime.text = "${item.depart_date_time}"
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(character.id)
    }
}

