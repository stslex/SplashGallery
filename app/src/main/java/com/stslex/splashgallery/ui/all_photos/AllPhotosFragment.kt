package com.stslex.splashgallery.ui.all_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentAllPhotosBinding
import com.stslex.splashgallery.ui.all_photos.adapter.AllPhotosAdapter
import com.stslex.splashgallery.ui.main_screen.PagerSharedViewModel
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.setImageWithRequest
import com.stslex.wallpape.ui.main_screen.MainFragmentDirections

class AllPhotosFragment : Fragment() {

    private var _binding: FragmentAllPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var adapter: AllPhotosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPhotosBinding.inflate(inflater, container, false)
        viewModel.setPageNumberAppPhotos(pagesNumAllPhotos)
        initRecyclerView()
        initScrollListener()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = AllPhotosAdapter(clickListener, setImage = setImage)
        recyclerView = binding.fragmentAllPhotosRecycler.fragmentAllPhotosRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        viewModel.allPhotos.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }
        postponeEnterTransition()
        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
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
                    pagesNumAllPhotos++
                    viewModel.setPageNumberAppPhotos(pagesNumAllPhotos)
                }
            }
        })
    }

    private val clickListener = ImageClickListener({ imageView, id ->
        val directions = MainFragmentDirections.actionNavHomeToNavSinglePhoto(
            imageView.transitionName,
            id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, { user ->
        val directions = MainFragmentDirections.actionNavHomeToNavUser(
            user.transitionName
        )
        val extras = FragmentNavigatorExtras(user to user.transitionName)
        findNavController().navigate(directions, extras)
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