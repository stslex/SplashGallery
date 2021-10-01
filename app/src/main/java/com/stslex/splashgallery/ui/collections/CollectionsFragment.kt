package com.stslex.splashgallery.ui.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.core.CoilListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.photos.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.user.UserFragment
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.UserSharedViewModel
import com.stslex.splashgallery.utils.GET_COLLECTIONS
import com.stslex.splashgallery.utils.GET_USERS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CollectionsFragment : BaseFragment() {

    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: UserSharedViewModel by activityViewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CollectionsAdapter(
            clickListener = ClickListener(),
            context = requireContext(),
            coilListener = CoilListener { startPostponedEnterTransition() }
        )
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

        binding.collections.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PhotosLoaderStateAdapter(),
            footer = PhotosLoaderStateAdapter()
        )

        adapter.addLoadStateListener {
            with(binding) {
                collections.isVisible = it.refresh != LoadState.Loading
                progress.isVisible = it.refresh == LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.currentId.collect {
                val query = when (requireParentFragment()) {
                    is MainFragment -> listOf(GET_COLLECTIONS)
                    is UserFragment -> listOf(GET_USERS, it, GET_COLLECTIONS)
                    else -> emptyList()
                }
                viewModel.setQuery(query)
            }
        }

        addRepeatingJob(
            Lifecycle.State.STARTED,
            viewLifecycleOwner.lifecycleScope.coroutineContext,
        ) {
            viewModel.collections.collectLatest(adapter::submitData)
        }
    }

    private inner class ClickListener :
        com.stslex.splashgallery.ui.core.ClickListener {
        override fun clickImage(view: View, url: String) {
            val extras = FragmentNavigatorExtras(view to view.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavSingleCollection(
                    transitionName = view.transitionName,
                    title = url
                )
                is UserFragment -> UserFragmentDirections.actionNavUserToNavSingleCollection(
                    transitionName = view.transitionName,
                    title = url
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }
        }

        override fun clickUser(view: View) {
            val extras = FragmentNavigatorExtras(view to view.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(
                    username = view.transitionName
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}