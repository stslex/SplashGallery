package com.stslex.splashgallery.ui.user

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class UserAdapter(
    fragment: Fragment,
    private val elements: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = elements.size
    override fun createFragment(position: Int): Fragment = elements[position]
}

