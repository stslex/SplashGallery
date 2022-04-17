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
    private val binding: FragmentMainBinding
        get() = checkNotNull(_binding)

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        sharedViewModel.setId(INITIAL_VALUE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainViewPager.adapter = MainFragmentAdapter(this@MainFragment)
            postponeEnterTransition()
            mainViewPager.doOnPreDraw(action = doOnPreDrawAction)
            TabLayoutMediator(mainTabLayout, mainViewPager, confStrategy).attach()
        }
    }

    private val doOnPreDrawAction: (View) -> Unit
        get() = {
            startPostponedEnterTransition()
        }

    private val FragmentMainBinding.confStrategy: TabLayoutMediator.TabConfigurationStrategy
        get() = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = listOfTabs[position]
            mainViewPager.setCurrentItem(tab.position, true)
        }

    private val listOfTabs by lazy {
        listOf(
            resources.getString(R.string.label_tab_layout_all),
            resources.getString(R.string.label_collections)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val INITIAL_VALUE: String = ""
    }
}