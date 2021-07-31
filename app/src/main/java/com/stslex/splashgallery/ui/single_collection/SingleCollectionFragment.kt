package com.stslex.splashgallery.ui.single_collection

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.databinding.FragmentSingleCollectionBinding
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.appComponent
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener

class SingleCollectionFragment : BaseFragment() {

    private var _binding: FragmentSingleCollectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SingleCollectionViewModel by viewModels { viewModelFactory.get() }

    private lateinit var id: String
    private var pagesImage = MutableLiveData<Int>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllPhotosAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationContext.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagesImage.value = pageNum
        enterTransition = MaterialContainerTransform(requireContext(), false).apply {
            duration = 1000L
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCollectionBinding.inflate(inflater, container, false)
        getNavigationArgs()
        initRecyclerView()
        initViewModelListening()
        initScrollListener()
        return binding.root
    }

    private fun initScrollListener() {
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
                    pageNum++
                    pagesImage.value = pageNum
                }
            }
        })
    }

    private fun initRecyclerView() {
        pagesImage.observe(viewLifecycleOwner) {
            viewModel.getAllPhotos(id, it)
        }
        recyclerView = binding.fragmentCollectionRecyclerView.fragmentAllPhotosRecyclerView
        adapter = AllPhotosAdapter(clickListener)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    private fun initViewModelListening() {
        viewModel.allPhotos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    adapter.addItems(it.data.image)
                }
                is Result.Failure -> {
                    Log.i("SingleCollection", it.exception)
                }
                is Result.Loading -> {

                }
            }
        }
    }

    private val clickListener = ImageClickListener({ imageView, id ->
        val directions =
            SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                imageView.transitionName,
                id
            )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, { user ->
        val directions =
            SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(user.transitionName)
        val extras = FragmentNavigatorExtras(user to user.transitionName)
        findNavController().navigate(directions, extras)
    })

    private fun getNavigationArgs() {
        val extras: SingleCollectionFragmentArgs by navArgs()
        id = extras.transitionName
        binding.fragmentCollectionRecyclerView.fragmentAllPhotosRecyclerView.transitionName = id
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var pageNum = 1
    }

}