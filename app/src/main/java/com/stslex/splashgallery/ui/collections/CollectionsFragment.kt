package com.stslex.splashgallery.ui.collections

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.collections.adapter.CollectionsAdapter
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.main_screen.MainSharedCollectionsViewModel
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager_view_models.UserCollectionSharedViewModel
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.base.BaseSharedCollectionsViewModel
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener
import com.stslex.splashgallery.utils.setImageWithRequest

class CollectionsFragment : Fragment() {

    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CollectionsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        numberOfCollections[requireParentFragment()] = 1
    }

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
        initFragment()
    }

    private fun initFragment() {
        when (requireParentFragment()) {
            is MainFragment -> {
                val viewModel: MainSharedCollectionsViewModel by activityViewModels()
                viewModel.initRecyclerView()
                viewModel.initScrollListener()
            }
            is UserCollectionFragment -> {
                val viewModel: UserCollectionSharedViewModel by activityViewModels()
                viewModel.initRecyclerView(isUser = true)
                viewModel.initScrollListener()
            }
        }
    }

    private fun BaseSharedCollectionsViewModel.initRecyclerView(isUser: Boolean = false) {
        setNumberCollections(numberOfCollections[requireParentFragment()] ?: 0)
        adapter = CollectionsAdapter(
            this@CollectionsFragment.clickListener,
            setImage = setImage,
            isUser = isUser
        )
        recyclerView = binding.fragmentCollectionsRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        collection.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
    }

    private fun BaseSharedCollectionsViewModel.initScrollListener() {
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
                    numberOfCollections[requireParentFragment()] =
                        numberOfCollections[requireParentFragment()] ?: 0 + 1
                    setNumberCollections(numberOfCollections[requireParentFragment()] ?: 0)
                }
            }
        })
    }

    private val Fragment.clickListener: CollectionClickListener
        get() = CollectionClickListener({ imageView, title ->
            val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavSingleCollection(
                    transitionName = imageView.transitionName,
                    title = title
                )
                is UserCollectionFragment -> UserFragmentDirections.actionNavUserToNavSingleCollection(
                    transitionName = imageView.transitionName,
                    title = title
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }
        }, { user ->
            val extras = FragmentNavigatorExtras(user to user.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(
                    username = user.transitionName
                )
                is UserCollectionFragment -> UserFragmentDirections.actionNavUserSelf(
                    username = user.transitionName
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }
        })

    private val setImage = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val numberOfCollections = mutableMapOf(
            MainFragment() to 1,
            UserCollectionFragment() to 1
        )
    }
}