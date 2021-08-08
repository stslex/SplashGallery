package com.stslex.splashgallery.ui.all_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.main_screen.MainSharedPhotosViewModel
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.single_collection.SingleCollectionSharedViewModel
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.ui.user.pager_view_models.UserLikesSharedViewModel
import com.stslex.splashgallery.ui.user.pager_view_models.UserPhotosSharedViewModel
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.base.BaseSharedPhotosViewModel
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.setImageWithRequest

class AllPhotosFragment : Fragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AllPhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        initFragment()
        return binding.root
    }

    private fun initFragment() {
        when (parentFragment) {
            is MainFragment -> {
                val viewModel: MainSharedPhotosViewModel by activityViewModels()
                viewModel.initRecyclerView()
                viewModel.initScrollListener()
            }
            is UserPhotosFragment -> {
                val viewModel: UserPhotosSharedViewModel by activityViewModels()
                viewModel.initRecyclerView(true)
                viewModel.initScrollListener()
            }
            is UserLikesFragment -> {
                val viewModel: UserLikesSharedViewModel by activityViewModels()
                viewModel.initRecyclerView()
                viewModel.initScrollListener()
            }
            is SingleCollectionFragment -> {
                val viewModel: SingleCollectionSharedViewModel by activityViewModels()
                viewModel.initRecyclerView()
                viewModel.initScrollListener()
            }
        }
    }

    private fun BaseSharedPhotosViewModel.initRecyclerView(isUser: Boolean = false) {
        setNumberPhotos(pagesNumAllPhotos)
        adapter = AllPhotosAdapter(
            this@AllPhotosFragment.clickListener,
            setImage = setImage,
            isUser = isUser
        )
        recyclerView = binding.fragmentAllPhotosRecycler.fragmentAllPhotosRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        photos.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
        postponeEnterTransition()
        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun BaseSharedPhotosViewModel.initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                    pagesNumAllPhotos++
                    setNumberPhotos(pagesNumAllPhotos)
                }
            }
        })
    }


    private val Fragment.clickListener: ImageClickListener
        get() = ImageClickListener({ imageView, id ->
            val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
            val directions: NavDirections? = when (parentFragment) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                    imageView.transitionName,
                    id
                )
                is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserToNavSinglePhoto(
                    imageView.transitionName,
                    id
                )
                is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                    imageView.transitionName,
                    id
                )
                else -> null
            }
            directions?.let {
                findNavController().navigate(it, extras)
            }

        }, { user ->
            val extras = FragmentNavigatorExtras(user to user.transitionName)
            val directions: NavDirections? = when (parentFragment) {
                is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(user.transitionName)
                is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserSelf(
                    user.transitionName
                )
                is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(
                    user.transitionName
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
        private var pagesNumAllPhotos = 1
    }

}