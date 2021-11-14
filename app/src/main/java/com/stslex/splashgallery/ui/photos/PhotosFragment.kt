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
import androidx.paging.LoadState
import com.stslex.splashgallery.data.core.QueryPhotos
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference


@ExperimentalCoroutinesApi
class PhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotosViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _adapter: PhotosAdapter? = null
    private val adapter: PhotosAdapter
        get() = checkNotNull(_adapter)

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
        adapter.addLoadStateListener {
            with(binding) {
                photos.isVisible = it.refresh != LoadState.Loading
                progress.isVisible = it.refresh == LoadState.Loading
            }
        }
        collectionJob.start()
    }

    private val queryJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.currentId.collect {
                setAdapter(it)
                val query = when (requireParentFragment()) {
                    is MainFragment -> QueryPhotos.AllPhotos
                    is UserPhotosFragment -> QueryPhotos.UserPhotos(it)
                    is UserLikesFragment -> QueryPhotos.UserLikes(it)
                    is SingleCollectionFragment -> QueryPhotos.CollectionPhotos(it)
                    else -> QueryPhotos.EmptyQuery
                }
                launch(Dispatchers.IO) {
                    viewModel.setQuery(query)
                }
            }
        }
    }

    private val collectionJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photos.collectLatest(adapter::submitData)
            }
        }
    }

    private fun setAdapter(currentId: String) {
        _adapter = PhotosAdapter(
            clickListener = PhotosClickListener(WeakReference(requireParentFragment())),
            glide = glide,
            context = requireContext(),
            currentId = currentId
        )
    }

    private val glide = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        queryJob.cancel()
        collectionJob.cancel()
    }
}
