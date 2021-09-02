package com.stslex.splashgallery.ui.all_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllPhotosFragment : BaseFragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllPhotosViewModel by viewModels { viewModelFactory.get() }
    private lateinit var adapter: AllPhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    private val numberOfPhotos = MutableLiveData(1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberOfPhotos.observe(viewLifecycleOwner, numberOfPhotosObserver)
        initRecyclerView()
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun initRecyclerView() {
        val isUser = requireParentFragment() is UserPhotosFragment
        adapter = AllPhotosAdapter(
            this@AllPhotosFragment.clickListener,
            setImage = setImage,
            isUser = isUser
        )
        recyclerView = binding.fragmentAllPhotosRecycler.fragmentAllPhotosRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        postponeEnterTransition()
        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private val numberOfPhotosObserver: Observer<Int> = Observer {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllPhotos(it).collect {
                when (it) {
                    is Result.Success -> {
                        adapter.addItems(it.data)
                    }
                    is Result.Failure -> {

                    }
                    is Result.Loading -> {
                    }
                }
            }
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
                    numberOfPhotos.value = numberOfPhotos.value?.plus(1)
                }
            }
        }

    private val Fragment.clickListener: ImageClickListener
        get() = ImageClickListener({ imageView, id ->
            val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
            val directions: NavDirections? = when (requireParentFragment()) {
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
            val directions: NavDirections? = when (requireParentFragment()) {
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

}