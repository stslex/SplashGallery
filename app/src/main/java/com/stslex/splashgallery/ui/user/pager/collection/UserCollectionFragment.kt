package com.stslex.splashgallery.ui.user.pager.collection

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
import com.stslex.splashgallery.databinding.FragmentUserCollectionBinding
import com.stslex.splashgallery.ui.collections.adapter.CollectionsAdapter
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.UserSharedViewModel
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener

class UserCollectionFragment : Fragment() {

    private var _binding: FragmentUserCollectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserSharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CollectionsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCollectionBinding.inflate(inflater, container, false)
        viewModel.setNumCollections(numPageCollection)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initScrollListener()
    }

    private fun initRecyclerView() {
        recyclerView = binding.userCollectionRecycler
        adapter = CollectionsAdapter(clickListener, isUser = true)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        viewModel.collections.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
    }

    private val clickListener = CollectionClickListener({ imageView, id ->
        val directions = UserFragmentDirections.actionNavUserToNavSingleCollection(
            imageView.transitionName,
            id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, {
        val directions = UserFragmentDirections.actionNavUserToNavSingleCollection(
            it.transitionName,
            it.transitionName
        )
        val extras = FragmentNavigatorExtras(it to it.transitionName)
        findNavController().navigate(directions, extras)
    })

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
                    numPageCollection++
                    viewModel.setNumCollections(numPageCollection)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var numPageCollection = 1
    }
}