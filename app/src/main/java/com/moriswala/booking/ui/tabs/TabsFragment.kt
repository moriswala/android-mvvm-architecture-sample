package com.moriswala.booking.ui.tabs

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentTabsBinding
import com.moriswala.booking.ui.characters.CharactersFragment
import com.moriswala.booking.utils.autoCleared
import com.google.android.material.tabs.TabLayoutMediator

class TabsFragment : Fragment() {

    private var binding: FragmentTabsBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun intiFragmentsList(): ArrayList<Fragment> {

        var marketFragments = ArrayList<Fragment>()
        marketFragments.add(CharactersFragment())
        marketFragments.add(CharactersFragment())
        return marketFragments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = TabsStateAdapter(this, intiFragmentsList())
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.isUserInputEnabled = false;
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = if (position == 0) {
                prepareTabView(getString(R.string.request_ticket))
            } else {
                prepareTabView(getString(R.string.pending_requests))
            }
        }.attach()
    }

    private fun prepareTabView(title: String): View? {
        val view = layoutInflater.inflate(R.layout.layout_item_tab, null)
        val tabTitleText = view.findViewById<TextView>(R.id.tabTitle)
        tabTitleText.setTextColor(getStoreTabTextColorSelector(getSpecialGreenColor(requireContext())))
        tabTitleText.text = title.toUpperCase()
        return view
    }

    fun getStoreTabTextColorSelector(@ColorInt selectedColor: Int): ColorStateList {
        val states = arrayOf(intArrayOf(android.R.attr.state_activated), intArrayOf())
        val colors = intArrayOf(
            selectedColor,
                    ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        )
        return ColorStateList(states, colors)
    }

    fun getSpecialGreenColor(context: Context): Int {
        val typedValue = TypedValue()
        val theme: Resources.Theme = context.theme
        theme.resolveAttribute(R.attr.specialGreenColor, typedValue, true)
        return typedValue.data
    }
}