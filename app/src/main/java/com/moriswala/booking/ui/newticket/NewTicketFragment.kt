package com.moriswala.booking.ui.newticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentNewTicketBinding
import com.moriswala.booking.utils.autoCleared

class NewTicketFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_new_ticket

    private var binding: FragmentNewTicketBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNewTicketBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarColor(R.color.colorPrimary, R.color.color_master_light)
        binding.searchBusesButton.setOnClickListener{
            findNavController().navigate(R.id.action_newTicketFramgnet_to_flightSelectionFragment)
        }
    }
}