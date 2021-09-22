package com.stslex.splashgallery.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class AllPhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllPhotosViewModel by viewModels { viewModelFactory.get() }
    private lateinit var adapter: AllPhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    private val globalPage = MutableLiveData<Map<Int, Int>>()
    private var number = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        globalPage.value = mapOf(requireParentFragment().id to 1)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalPage.observe(viewLifecycleOwner, observer {
            startListening(currentId, it)
        })
        initRecyclerView()
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun startListening(username: String, page: Int) =
        viewLifecycleOwner.lifecycleScope.launch {
            when (requireParentFragment()) {
                is UserPhotosFragment -> {
                    viewModel.getUserPhotos(username, page).collect { it.collector }
                }
                is UserLikesFragment -> {
                    viewModel.getUserLikes(username, page).collect { it.collector }
                }
                is SingleCollectionFragment -> {
                    viewModel.getCollectionPhotos(username, page).collect { it.collector }
                }
                else -> {
                    viewModel.getAllPhotos(page).collect { it.collector }
                }
            }
        }


    private fun initRecyclerView() {
        val isNotUser = requireParentFragment() is UserPhotosFragment
        adapter = AllPhotosAdapter(
            PhotosClickListener(),
            setImageWithGlide,
            isUser = !isNotUser
        )
        recyclerView = binding.fragmentAllPhotosRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        postponeEnterTransition()
        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private inline fun observer(crossinline function: (Int) -> Unit): Observer<Map<Int, Int>> =
        Observer {
            it[requireParentFragment().id]?.let { page ->
                function(page)
            }
        }

    private val PhotosUIResult.collector: Unit
        get() = when (this) {
            is PhotosUIResult.Success -> {
                adapter.addItems(data)
            }
            is PhotosUIResult.Failure -> {

            }
            is PhotosUIResult.Loading -> {
            }
        }

    private val scrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
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
                    number++
                    globalPage.value = mapOf(requireParentFragment().id to number)
                }
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
