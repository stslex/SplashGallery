package com.stslex.splashgallery.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.photos.adapter.PhotosAdapter
import com.stslex.splashgallery.ui.photos.adapter.PhotosLoaderStateAdapter
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest


@ExperimentalCoroutinesApi
class AllPhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllPhotosViewModel by viewModels { viewModelFactory.get() }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotosAdapter(glide = setImageWithGlide, context = this.requireContext())
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

        adapter.addLoadStateListener {
            with(binding) {
                photos.isVisible = it.refresh != LoadState.Loading
                progress.isVisible = it.refresh == LoadState.Loading
            }
        }

        val query = when (requireParentFragment()) {
            is UserLikesFragment -> listOf(GET_USERS, Resources.currentId, GET_LIKES)
            is UserPhotosFragment -> listOf(GET_USERS, Resources.currentId, GET_PHOTOS)
            is SingleCollectionFragment -> listOf(GET_COLLECTIONS, Resources.currentId, GET_PHOTOS)
            else -> listOf(GET_PHOTOS)
        }

        viewModel.setQuery(query)

        addRepeatingJob(
            Lifecycle.State.STARTED,
            viewLifecycleOwner.lifecycleScope.coroutineContext,
        ) {
            viewModel.photos.collectLatest(adapter::submitData)
        }
    }


    private inner class PhotosClickListener : ClickListener<PhotosUI> {
        override fun clickImage(item: PhotosUI) {
            item.openImage { imageCard, url ->
                val extras = FragmentNavigatorExtras(imageCard to imageCard.transitionName)
                val directions: NavDirections? = when (requireParentFragment()) {
                    is MainFragment -> MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                        id = imageCard.transitionName,
                        url = url
                    )
                    is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserToNavSinglePhoto(
                        id = imageCard.transitionName,
                        url = url
                    )
                    is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                        id = imageCard.transitionName,
                        url = url
                    )
                    else -> null
                }
                directions?.let {
                    findNavController().navigate(it, extras)
                }
            }
        }

        override fun clickUser(item: PhotosUI) {
            item.openUser { userCard ->
                val extras = FragmentNavigatorExtras(userCard to userCard.transitionName)
                val directions: NavDirections? = when (requireParentFragment()) {
                    is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(userCard.transitionName)
                    is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserSelf(
                        username = userCard.transitionName
                    )
                    is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(
                        username = userCard.transitionName
                    )
                    else -> null
                }
                directions?.let {
                    findNavController().navigate(it, extras)
                }
            }
        }
    }

    private val setImageWithGlide = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
