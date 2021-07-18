package com.stslex.wallpape.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
    }

    private fun initPager() {
        binding.mainViewPager.adapter = MainFragmentAdapter(this)
        val mapOfTabs = mapOf(
            0 to getString(R.string.label_tab_layout_all),
            1 to getString(R.string.label_tab_layout_saved)
        )
        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = mapOfTabs[position]
            binding.mainViewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}