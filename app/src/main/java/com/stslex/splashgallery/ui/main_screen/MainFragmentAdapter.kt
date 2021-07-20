package com.stslex.wallpape.ui.main_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stslex.wallpape.ui.main_screen_pager.MainPagerFragment

class MainFragmentAdapter(fragment: Fragment, private val number: Int) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = number
    override fun createFragment(position: Int): Fragment = MainPagerFragment()
}