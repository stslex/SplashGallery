package com.stslex.splashgallery.ui.collections

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
import com.stslex.splashgallery.data.core.QueryCollections
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photos.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.user.UserFragment
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
class CollectionsFragment : BaseFragment() {

    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter: CollectionsAdapter by lazy {
        CollectionsAdapter(
            clickListener = CollectionClickListener(WeakReference(requireParentFragment())),
            glide = glide,
            context = requireContext(),
            isUser = requireParentFragment() is UserFragment
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
        }
    }

    private val queryJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.currentId.collect {
                val query = when (requireParentFragment()) {
                    is MainFragment -> QueryCollections.AllCollections
                    is UserFragment -> QueryCollections.UserCollections(it)
                    else -> QueryCollections.EmptyQuery
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
                viewModel.collections.collectLatest(adapter::submitData)
            }
        }
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