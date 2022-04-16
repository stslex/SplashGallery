package com.stslex.splashgallery.ui.main_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stslex.splashgallery.ui.collections.CollectionsFragment
import com.stslex.splashgallery.ui.photos.PhotosFragment

class MainFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList: List<Fragment> by lazy {
        listOf(PhotosFragment(), CollectionsFragment())
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}