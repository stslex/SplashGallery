package com.stslex.splashgallery.ui.user.pager.photos

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
import com.stslex.splashgallery.databinding.FragmentUserPhotosBinding
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.UserSharedViewModel
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener

class UserPhotosFragment : Fragment() {

    private var _binding: FragmentUserPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserSharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllPhotosAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPhotosBinding.inflate(inflater, container, false)
        viewModel.setNumPhotos(pageNumPhotos)
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
                    pageNumPhotos++
                    viewModel.setNumPhotos(pageNumPhotos)
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView = binding.userPhotosRecycler
        adapter = AllPhotosAdapter(clickListener)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
    }

    private val clickListener = ImageClickListener({ imageView, id ->
        val directions = UserFragmentDirections.actionNavUserToNavSinglePhoto(
            imageView.transitionName,
            id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, {
        val directions = UserFragmentDirections.actionNavUserSelf(
            it.transitionName
        )
        val extras = FragmentNavigatorExtras(it to it.transitionName)
        findNavController().navigate(directions, extras)
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var pageNumPhotos = 1
    }
}