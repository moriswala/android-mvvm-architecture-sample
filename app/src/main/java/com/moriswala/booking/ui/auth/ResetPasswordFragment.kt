package com.moriswala.booking.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentResetPasswordBinding
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_reset_password

    private val viewModel: AuthViewModel by viewModels()
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

        binding.btnBack.setOnClickListener(View.OnClickListener { getNavController().popBackStack() })

        binding.btnResetPassword.setOnClickListener(View.OnClickListener {
            val email: String = binding.email.text.toString().trim()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    requireContext(),
                    "Enter your registered email id",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            viewModel.auth().sendPasswordResetEmail(email)
                .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "We have sent you instructions to reset your password!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to send reset email!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    binding.progressBar.visibility = View.GONE
                })
        })
    }
}