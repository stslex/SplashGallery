package com.stslex.wallpape.ui.main_screen_pager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentMainPagerBinding
import com.stslex.splashgallery.ui.main_screen_pager.PagerSharedViewModel

class MainPagerFragment : Fragment() {

    private var _binding: FragmentMainPagerBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var adapter: MainPagerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPagerBinding.inflate(inflater, container, false)
        sharedViewModel.setPageNumber(pagesNum)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initScrollListener()
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_TOUCH_SCROLL) isScrolling = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (firstVisibleItemPosition + visibleItemCount) >= (totalItemCount - 6) && dy > 0) {
                    isScrolling = false
                    pagesNum++
                    sharedViewModel.setPageNumber(pagesNum)
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = MainPagerAdapter()
        recyclerView = binding.mainPagerRecycler
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        /*sharedViewModel.page.observe(viewLifecycleOwner) {
            adapter.addItemsOfPhoto(it.image)
        }*/
        sharedViewModel.collection.observe(viewLifecycleOwner) {
            Log.i("Collection::pf", it.toString())
            adapter.addItemsOfCollection(it.collections)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        sharedViewModel.page.removeObservers(viewLifecycleOwner)
        sharedViewModel.pageNumber.removeObservers(viewLifecycleOwner)
    }

    companion object {
        private var pagesNum = 1
    }

}