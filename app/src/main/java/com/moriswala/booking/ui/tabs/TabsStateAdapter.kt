package com.moriswala.booking.ui.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class TabsStateAdapter(fragment: Fragment, private val fragments: ArrayList<Fragment>) :
    FragmentStateAdapter(fragment.childFragmentManager, fragment.lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}

