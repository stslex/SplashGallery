package com.stslex.splashgallery.ui.main_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stslex.splashgallery.ui.collections.CollectionsFragment
import com.stslex.splashgallery.ui.photos.AllPhotosFragment

class MainFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment =
        if (position == 0) AllPhotosFragment() else CollectionsFragment()
}