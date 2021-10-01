package com.stslex.splashgallery.ui.photos

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
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.UserSharedViewModel
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest


@ExperimentalCoroutinesApi
class PhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotosViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: UserSharedViewModel by activityViewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotosAdapter(
            clickListener = ClickListener(),
            glide = glide,
            context = requireContext()
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

        binding.photos.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PhotosLoaderStateAdapter(),
            footer = PhotosLoaderStateAdapter()
        )

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.currentId.collect {
                val query = when (requireParentFragment()) {
                    is MainFragment -> listOf(GET_PHOTOS)
                    is UserPhotosFragment -> listOf(GET_USERS, it, GET_PHOTOS)
                    is UserLikesFragment -> listOf(GET_USERS, it, GET_LIKES)
                    is SingleCollectionFragment -> listOf(
                        GET_COLLECTIONS,
                        Resources.currentId,
                        GET_PHOTOS
                    )
                    else -> emptyList()
                }

                viewModel.setQuery(query)
            }
        }

        adapter.addLoadStateListener {
            with(binding) {
                photos.isVisible = it.refresh != LoadState.Loading
                progress.isVisible = it.refresh == LoadState.Loading
            }
        }

        addRepeatingJob(
            Lifecycle.State.STARTED,
            viewLifecycleOwner.lifecycleScope.coroutineContext,
        ) {
            viewModel.photos.collectLatest(adapter::submitData)
        }
    }


    private inner class ClickListener : OnClickListener {
        override fun clickImage(view: View, url: String) {
            val extras = FragmentNavigatorExtras(view to view.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                    id = view.transitionName,
                    url = url
                )
                is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserToNavSinglePhoto(
                    id = view.transitionName,
                    url = url
                )
                is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                    id = view.transitionName,
                    url = url
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
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(view.transitionName)
                is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserSelf(
                    username = view.transitionName
                )
                is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(
                    username = view.transitionName
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }
        }

    }

    private val glide = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
