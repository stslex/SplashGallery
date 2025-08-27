package com.stslex.splashgallery.ui.main_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.core_ui.BaseFragment
import com.stslex.splashgallery.core_ui.R
import com.stslex.splashgallery.core_ui.SharedViewModel
import com.stslex.splashgallery.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(
    bindingInflater = FragmentMainBinding::inflate
) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setId(INITIAL_VALUE)
        binding.setUI()
    }

    private fun FragmentMainBinding.setUI() {
        mainViewPager.adapter = MainFragmentAdapter(this@MainFragment)
        postponeEnterTransition()
        mainViewPager.doOnPreDraw(action = doOnPreDrawAction)
        TabLayoutMediator(mainTabLayout, mainViewPager, confStrategy).attach()
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

    companion object {
        private const val INITIAL_VALUE: String = ""
    }
}