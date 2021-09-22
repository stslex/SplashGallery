package com.stslex.splashgallery.ui.collections

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
import com.stslex.splashgallery.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.ui.collections.adapter.CollectionsAdapter
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CollectionsFragment : BaseFragment() {

    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }

    private lateinit var adapter: CollectionsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var isScrolling = false

    private val globalPage = MutableLiveData<Map<Int, Int>>()
    private var number = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater, container, false)
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
                is MainFragment -> {
                    viewModel.getAllCollections(page).collect { it.collector }
                }
                is UserCollectionFragment -> {
                    viewModel.getUserCollections(username, page).collect { it.collector }
                }
            }
        }

    private inline fun observer(crossinline function: (Int) -> Unit): Observer<Map<Int, Int>> =
        Observer {
            it[requireParentFragment().id]?.let { page ->
                function(page)
            }
        }

    private val CollectionUIResult.collector: Unit
        get() = when (this) {
            is CollectionUIResult.Success -> {
                adapter.addItems(data)
            }
            is CollectionUIResult.Failure -> {

            }
            is CollectionUIResult.Loading -> {
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

    private fun initRecyclerView() {
        val isUser = requireParentFragment() is UserPhotosFragment
        adapter = CollectionsAdapter(
            ClickUI(),
            setImage = setImage,
            isUser = isUser
        )
        recyclerView = binding.fragmentCollectionsRecyclerView
        layoutManager = LinearLayoutManager(requireContext())
        postponeEnterTransition()
        recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    private inner class ClickUI : ClickListener<CollectionUI> {
        override fun clickImage(item: CollectionUI) {
            item.openImage { card, title ->
                val extras = FragmentNavigatorExtras(card to card.transitionName)
                val directions: NavDirections? = when (requireParentFragment()) {
                    is MainFragment -> MainFragmentDirections.actionNavHomeToNavSingleCollection(
                        transitionName = card.transitionName,
                        title = title
                    )
                    is UserCollectionFragment -> UserFragmentDirections.actionNavUserToNavSingleCollection(
                        transitionName = card.transitionName,
                        title = title
                    )
                    else -> null
                }
                directions?.let {
                    findNavController().navigate(it, extras)
                }
            }
        }

        override fun clickUser(item: CollectionUI) {
            item.openUser { card ->
                val extras = FragmentNavigatorExtras(card to card.transitionName)
                val directions: NavDirections? = when (requireParentFragment()) {
                    is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(
                        username = card.transitionName
                    )
                    is UserCollectionFragment -> UserFragmentDirections.actionNavUserSelf(
                        username = card.transitionName
                    )
                    else -> null
                }
                directions?.let {
                    findNavController().navigate(it, extras)
                }
            }
        }

    }

    private val setImage = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}