package com.moriswala.booking.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentLoginBinding
import com.moriswala.booking.ui.auth.AuthViewModel
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_login
    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()

        binding.checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, value ->
            if (value) {
                // Show Password
                binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // Hide Password
                binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        })

        binding.btnSignup.setOnClickListener(View.OnClickListener {
            getNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        })

        binding.btnResetPassword.setOnClickListener(View.OnClickListener {
            getNavController().navigate(R.id.action_loginFragment_to_resetPsswordFragment)
        })

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val email: String = binding.email.text.toString()
            val password: String = binding.password.text.toString()
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
            binding.progressBar.visibility = View.VISIBLE

            //authenticate user
            viewModel.auth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult?> { task ->
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        binding.progressBar.visibility = View.GONE
                        if (!task.isSuccessful) {
                            // there was an error
                            if (password.length < 6) {
                                binding.password.error = getString(R.string.minimum_password)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.auth_failed),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            gotoHomeFragment()
                        }
                    })
        })
    }

    private fun gotoHomeFragment() {
        getNavController().navigate(R.id.action_loginFragment_to_charactersFragment)
    }
}