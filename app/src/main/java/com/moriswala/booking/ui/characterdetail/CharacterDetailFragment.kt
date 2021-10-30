package com.moriswala.booking.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.databinding.CharacterDetailFragmentBinding
import com.moriswala.booking.utils.Resource
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.character_detail_fragment

    private var binding: CharacterDetailFragmentBinding by autoCleared()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = CharacterDetailFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
        setTitle(R.string.flightDetails)
    }

    private fun setupObservers() {
        viewModel.booking.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(booking: Booking) {
        binding.name.text = booking.owner
        binding.fromCity.text = booking.depart_city_id
        binding.status.text = booking.arrival_city_id
        binding.departTime.text = booking.depart_date_time
        binding.arrivalTime.text = booking.arrival_date_time
        binding.noOfStopsValue.text = booking.stops.toString()
        binding.status.text = if(booking.status==1) "APPROVED" else "PENDING"
//        Glide.with(binding.root)
//            .load(character.image)
//            .transform(CircleCrop())
//            .into(binding.image)
    }
}
