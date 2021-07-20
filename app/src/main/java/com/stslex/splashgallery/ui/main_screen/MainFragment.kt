package com.stslex.wallpape.ui.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.data.model.title.TopicsModel
import com.stslex.splashgallery.databinding.FragmentMainBinding
import com.stslex.splashgallery.ui.main_screen.MainViewModel
import com.stslex.splashgallery.ui.main_screen_pager.PagerSharedViewModel
import com.stslex.splashgallery.utils.BaseFragment
import com.stslex.splashgallery.utils.Result

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

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
        viewModel.singleTopic.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    sharedViewModel.page.postValue(it.data)
                }
                is Result.Failure -> {
                    Snackbar.make(binding.root, it.exception, Snackbar.LENGTH_SHORT).show()
                }
                is Result.Loading -> {

                }
            }
        }
        initPager()
    }

    private fun initPager() {
        viewModel.getTopics()
        viewModel.allTopics.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    val list = result.data
                    binding.mainViewPager.adapter = MainFragmentAdapter(this, list.size)
                    val mapOfList = mutableMapOf<Int, TopicsModel>()
                    for (i in list.indices) {
                        mapOfList[i] = list[i]
                        Log.i("Retrofitt", mapOfList[i].toString())
                    }
                    binding.mainTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

                    TabLayoutMediator(
                        binding.mainTabLayout,
                        binding.mainViewPager
                    ) { tab, position ->
                        tab.text = mapOfList[position]?.title
                        binding.mainViewPager.setCurrentItem(tab.position, true)
                    }.attach()

                    binding.mainViewPager.registerOnPageChangeCallback(object :
                        ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            viewModel.getSingleTopic(mapOfList[position]!!.id, 1)
                        }
                    })

                }
                is Result.Failure -> {
                    Snackbar.make(binding.root, result.exception, Snackbar.LENGTH_SHORT).show()
                }
                is Result.Loading -> {

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}