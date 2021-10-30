package com.moriswala.booking.ui.auth.registeration

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentRegisterationBinding
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_registeration

    private var binding: FragmentRegisterationBinding by autoCleared()
    private val viewModel: RegisterationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRegisterationBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()
        binding.signInButton.setOnClickListener(View.OnClickListener {
            getNavController().popBackStack()
        })

        binding.signUpButton.setOnClickListener(View.OnClickListener {
            val email: String = binding.email.text.toString().trim()
            val password: String = binding.password.text.toString().trim()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(requireContext(), "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(requireContext(), "Enter password!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(
                    requireContext(),
                    "Password too short, enter minimum 6 characters!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (binding.password.text.toString() != binding.confirmPassword.text.toString()) {
                Toast.makeText(
                    requireContext(),
                    "Confirm password are not matching with your password!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            //create user
            viewModel.auth().createUserWithEmailAndPassword(email, password)
                    OnCompleteListener<AuthResult?> { task ->
                        Toast.makeText(
                            requireContext(),
                            "createUserWithEmail:onComplete:" + task.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.visibility = View.GONE
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful) {
                            Toast.makeText(
                                requireContext(), "Authentication failed." + task.exception,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            gotoHomeFragment()
                        }
                    }
        })
    }

    private fun gotoHomeFragment() {
        getNavController().navigate(R.id.action_loginFragment_to_charactersFragment)
    }
}