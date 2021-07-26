package com.stslex.wallpape.ui.main_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stslex.splashgallery.ui.main_screen_pager.all_photos.AllPhotosFragment
import com.stslex.splashgallery.ui.main_screen_pager.collections.CollectionsFragment

class MainFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment =
        if (position == 0) AllPhotosFragment() else CollectionsFragment()

}