package com.moriswala.booking.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentLoginBinding
import com.moriswala.booking.databinding.FragmentResetPasswordBinding
import com.moriswala.booking.databinding.FragmentSuccessBinding
import com.moriswala.booking.utils.autoCleared

class ResetPasswordFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_reset_password

    private var binding: FragmentResetPasswordBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentResetPasswordBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()
    }
}