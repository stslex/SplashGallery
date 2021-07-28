package com.stslex.splashgallery.ui.all_photos

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
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.PagerSharedViewModel
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.wallpape.ui.main_screen.MainFragment
import com.stslex.wallpape.ui.main_screen.MainFragmentDirections

class AllPhotosFragment : Fragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var adapter: AllPhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        viewModel.setPageNumberAppPhotos(pagesNumAllPhotos)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initScrollListener()
    }

    private fun initRecyclerView() {
        adapter = AllPhotosAdapter(clickListener)
        recyclerView = binding.fragmentAllPhotosRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        viewModel.allPhotos.observe(viewLifecycleOwner) {
            adapter.addItems(it.image)
        }
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    isScrolling = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (firstVisibleItemPosition + visibleItemCount) >= (totalItemCount - 6) && dy > 0) {
                    isScrolling = false
                    pagesNumAllPhotos++
                    viewModel.setPageNumberAppPhotos(pagesNumAllPhotos)
                }
            }
        })
    }

    private val clickListener = ImageClickListener { imageModel, imageView ->
        val directions = when (parentFragment) {
            is MainFragment -> {
                MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                    imageModel,
                    imageView.transitionName
                )
            }
            is SingleCollectionFragment -> {
                SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                    imageModel,
                    imageView.transitionName
                )
            }
            else -> {
                null
            }
        }
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        directions?.let { findNavController().navigate(it, extras) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private var pagesNumAllPhotos = 1
    }

}