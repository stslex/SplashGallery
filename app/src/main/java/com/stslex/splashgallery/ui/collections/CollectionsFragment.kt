package com.stslex.splashgallery.ui.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.PagerSharedViewModel
import com.stslex.splashgallery.ui.collections.adapter.CollectionsAdapter
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener
import com.stslex.wallpape.ui.main_screen.MainFragmentDirections

class CollectionsFragment : Fragment() {

    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var adapter: CollectionsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setPageNumberCollections(pagesNumCollections)
        initRecyclerView()
        initScrollListener()
    }

    private fun initRecyclerView() {
        adapter = CollectionsAdapter(clickListener)
        recyclerView = binding.fragmentCollectionsRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        viewModel.collection.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) isScrolling =
                    true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (firstVisibleItemPosition + visibleItemCount) >= (totalItemCount - 6) && dy > 0) {
                    isScrolling = false
                    pagesNumCollections++
                    viewModel.setPageNumberCollections(pagesNumCollections)
                }
            }
        })
    }

    private val clickListener = CollectionClickListener({ imageView, title ->
        val directions = MainFragmentDirections.actionNavHomeToNavSingleCollection(
            transitionName = imageView.transitionName,
            title = title
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, { user ->
        val directions = MainFragmentDirections.actionNavHomeToNavUser(
            user.transitionName
        )
        val extras = FragmentNavigatorExtras(user to user.transitionName)
        findNavController().navigate(directions, extras)
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var pagesNumCollections = 1
    }
}