package com.stslex.splashgallery.ui.collections

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.data.collections.QueryCollections
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photos.loader_adapter.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.user.UserFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class CollectionsFragment :
    BaseFragment<FragmentCollectionsBinding>(FragmentCollectionsBinding::inflate) {

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: CollectionsAdapter by lazy {
        CollectionsAdapter(
            clickListener = CollectionClickListener(WeakReference(requireParentFragment())),
            glide = setImage,
            isUser = requireParentFragment() is UserFragment
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryJob.start()
        binding.collections.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PhotosLoaderStateAdapter(),
            footer = PhotosLoaderStateAdapter()
        )
        adapter.addLoadStateListener(::loadStateListener)
        collectionJob.start()
    }

    private fun loadStateListener(loadState: CombinedLoadStates) {
        with(binding) {
            collections.isVisible = loadState.refresh != LoadState.Loading
            progress.isVisible = loadState.refresh == LoadState.Loading
            progress.setProgress(100, true)
        }
    }

    private val queryJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.currentId.collect(::collectorId)
        }
    }

    private fun collectorId(id: String) {
        val query = when (requireParentFragment()) {
            is MainFragment -> QueryCollections.AllCollections
            is UserFragment -> QueryCollections.UserCollections(id)
            else -> QueryCollections.EmptyQuery
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.setQuery(query)
        }
    }

    private val collectionJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.collections.collectLatest(adapter::submitData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        queryJob.cancel()
        collectionJob.cancel()
    }
}