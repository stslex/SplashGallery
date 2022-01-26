package com.stslex.splashgallery.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.stslex.splashgallery.data.photos.QueryPhotos
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photos.adapter.PhotosAdapter
import com.stslex.splashgallery.ui.photos.loader_adapter.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.user.UserFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import java.lang.ref.WeakReference


@ExperimentalCoroutinesApi
class PhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel: PhotosViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(
            clickListener = PhotosClickListener(WeakReference(requireParentFragment())),
            glide = setImage,
            isUser = requireParentFragment().requireParentFragment() is UserFragment
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        return binding.root
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

    private fun collector(response: String) = viewLifecycleOwner.lifecycleScope.launch(
        context = Dispatchers.IO,
        start = CoroutineStart.LAZY,
        block = jobBlockQueryPhotos(response.photosQueryResponse)
    )

    private fun jobBlockQueryPhotos(queryPhotos: QueryPhotos): suspend CoroutineScope.() -> Unit = {
        viewModel.setQuery(queryPhotos)
    }

    private val String.photosQueryResponse: QueryPhotos
        get() = when (requireParentFragment()) {
            is MainFragment -> QueryPhotos.AllPhotos
            is UserPhotosFragment -> QueryPhotos.UserPhotos(this)
            is UserLikesFragment -> QueryPhotos.UserLikes(this)
            is SingleCollectionFragment -> QueryPhotos.CollectionPhotos(this)
            else -> QueryPhotos.EmptyQuery
        }

    private val collectionJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photos.collectLatest(adapter::submitData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        queryJob.cancel()
        collectionJob.cancel()
    }
}
