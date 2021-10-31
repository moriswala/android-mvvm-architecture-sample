package com.moriswala.booking.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.BookingsFragmentBinding
import com.moriswala.booking.utils.Resource
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingsFragment : BaseFragment(), BookingsAdapter.ItemListener {

    override fun getLayoutResId(): Int = R.layout.bookings_fragment

    private var binding: BookingsFragmentBinding by autoCleared()
    private val viewModel: BookingsViewModel by viewModels()
    private lateinit var adapter: BookingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = BookingsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        hideToolbar()
        binding.btnLogout.setOnClickListener{
            viewModel.auth().signOut()
            getNavController().navigate(R.id.action_charactersFragment_to_loginFragment)
        }
        binding.issueNewTicketFab.setOnClickListener{
            getNavController().navigate(R.id.action_charactersFragment_to_newTicketFramgnet)
        }
    }

    private fun setupRecyclerView() {
        adapter = BookingsAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.bookings.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onBookingClicked(bookingId: Int) {
        getNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to bookingId)
        )
    }
}
