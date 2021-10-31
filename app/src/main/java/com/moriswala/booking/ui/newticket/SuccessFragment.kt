package com.moriswala.booking.ui.newticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentSuccessBinding
import com.moriswala.booking.ui.auth.AuthViewModel
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_success
    private val viewModel: AuthViewModel by viewModels()
    private var binding: FragmentSuccessBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSuccessBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()
        var user = viewModel.auth().currentUser
        binding.greetingText.text = getString(R.string.greet_name, user?.email)
        navigationButtonClickListener = {
            findNavController().navigate(R.id.charactersFragment)
        }
        binding.doneButton.setOnClickListener{
            findNavController().navigate(R.id.charactersFragment)
        }
    }
}