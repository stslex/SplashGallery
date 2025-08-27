package com.stslex.splashgallery.ui.photos

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
import com.stslex.splashgallery.core_ui.BaseFragment
import com.stslex.splashgallery.core_ui.SharedViewModel
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.data.photos.QueryPhotos
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photos.adapter.PhotosAdapter
import com.stslex.splashgallery.ui.photos.loader_adapter.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.user.UserFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotosFragment : BaseFragment<FragmentAllPhotosBinding>(
    bindingInflater = FragmentAllPhotosBinding::inflate
) {

    private val viewModel: PhotosViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(
            clickListener = PhotosClickListener(),
            glide = setImage,
            isUser = requireParentFragment().requireParentFragment() is UserFragment
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryJob.start()
        binding.photos.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PhotosLoaderStateAdapter(),
            footer = PhotosLoaderStateAdapter()
        )
        adapter.addLoadStateListener(::loadStateListener)
        collectionJob.start()
    }

    private fun loadStateListener(loadState: CombinedLoadStates): Unit = with(binding) {
        photos.isVisible = loadState.refresh != LoadState.Loading
        progress.isVisible = loadState.refresh == LoadState.Loading
        progress.setProgress(100, true)
    }

    private val queryJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.currentId.collect(::collector)
        }
    }

    private fun collector(response: String) {
        val queryPhotos = when (requireParentFragment()) {
            is MainFragment -> QueryPhotos.AllPhotos
            is UserPhotosFragment -> QueryPhotos.UserPhotos(response)
            is UserLikesFragment -> QueryPhotos.UserLikes(response)
            is SingleCollectionFragment -> QueryPhotos.CollectionPhotos(response)
            else -> QueryPhotos.EmptyQuery
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.setQuery(queryPhotos)
        }
    }

    private val collectionJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photos.collectLatest(adapter::submitData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        queryJob.cancel()
        collectionJob.cancel()
    }
}
