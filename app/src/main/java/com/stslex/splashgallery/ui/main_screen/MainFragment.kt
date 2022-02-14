package com.stslex.splashgallery.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentMainBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setId("")
        binding.mainViewPager.adapter = MainFragmentAdapter(this)
        setPagerAnimation()
        val listOfTabs = listOf(
            resources.getString(R.string.label_tab_layout_all),
            resources.getString(R.string.label_collections)
        )
        listOfTabs.tabLayoutMediator.attach()
    }

    private fun setPagerAnimation() {
        postponeEnterTransition()
        binding.mainViewPager.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private val List<String>.tabLayoutMediator: TabLayoutMediator
        get() = with(binding) {
            TabLayoutMediator(
                mainTabLayout,
                mainViewPager
            ) { tab, position ->
                tab.text = this@tabLayoutMediator[position]
                mainViewPager.setCurrentItem(tab.position, true)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}