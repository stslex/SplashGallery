package com.stslex.wallpape.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentMainBinding
import com.stslex.splashgallery.ui.main_screen.MainViewModel
import com.stslex.splashgallery.ui.main_screen_pager.PagerSharedViewModel
import com.stslex.splashgallery.utils.BaseFragment
import com.stslex.splashgallery.utils.Result


class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val sharedViewModel: PagerSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelListener()
        initPager()
        initPagerSwitcher()
    }

    private fun initPagerSwitcher() {
        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
        })
    }

    private fun setViewModelListener() {
        sharedViewModel.pageNumber.observe(viewLifecycleOwner) {
            viewModel.getAllPhotos(it)
        }
        viewModel.allPhotos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    sharedViewModel.setPage(it.data)
                }
                is Result.Failure -> {
                    Snackbar.make(binding.root, it.exception, Snackbar.LENGTH_SHORT).show()
                }
                is Result.Loading -> {

                }
            }
        }
    }

    private fun initPager() {
        binding.mainViewPager.adapter = MainFragmentAdapter(this)
        val listOfTabs = mapOf(
            0 to getString(R.string.label_tab_layout_all),
            1 to getString(R.string.label_tab_layout_collection)
        )
        TabLayoutMediator(
            binding.mainTabLayout,
            binding.mainViewPager
        ) { tab, position ->
            tab.text = listOfTabs[position]
            binding.mainViewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}