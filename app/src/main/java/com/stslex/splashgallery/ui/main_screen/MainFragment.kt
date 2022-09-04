package com.stslex.splashgallery.ui.main_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.core_ui.BaseFragment
import com.stslex.splashgallery.R
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentMainBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel

class MainFragment : BaseFragment<FragmentMainBinding>(
    bindingInflater = FragmentMainBinding::inflate,
    hostFragmentId = R.id.nav_host_fragment
) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setId(INITIAL_VALUE)
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

    companion object {
        private const val INITIAL_VALUE: String = ""
    }
}