package com.moriswala.booking.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.BuildConfig
import com.moriswala.booking.databinding.FragmentBaseToolbarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_base_toolbar.view.*

abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    private lateinit var binding: FragmentBaseToolbarBinding
    var navigationButtonClickListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBaseToolbarBinding.inflate(inflater, container, false)
        inflater.inflate(getLayoutResId(), binding.contentFrame, true)
        showNavigationIcon(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (BuildConfig.DEBUG) {
            Log.d("BASEFRAGMENT", "onViewCreated: " + this.javaClass.simpleName)
        }

        binding.navigationButton.setOnClickListener { onBackPressed() }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
    }

    private fun onBackPressed() {
        if (navigationButtonClickListener == null) {
            getNavController().popBackStack()
        } else {
            navigationButtonClickListener?.invoke()
        }
    }

    fun showNavigationIcon(show: Boolean) {
        binding.navigationButton.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun setTitle(@StringRes titleRes: Int) {
        binding.navigationTitle.setText(titleRes)
    }

    fun setTitle(titleString: String) {
        binding.navigationTitle.text = titleString
    }

    fun hideToolbar() {
        binding.toolbar.isVisible = false
    }

    fun setToolbarColor(@ColorRes id : Int, @ColorRes iconColor: Int){
        binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), id))
        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.toolbar.navigationButton.drawable),
            ContextCompat.getColor(requireContext(), iconColor)
        )
    }

    fun getNavController(): NavController = findNavController()
}