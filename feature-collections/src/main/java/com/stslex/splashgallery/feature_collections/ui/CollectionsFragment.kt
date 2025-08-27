package com.stslex.splashgallery.feature_collections.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.stslex.splashgallery.core_ui.BaseFragment
import com.stslex.splashgallery.core_ui.SharedViewModel
import com.stslex.splashgallery.feature_collections.data.QueryCollections
import com.stslex.splashgallery.feature_collections.databinding.FragmentCollectionsBinding
import com.stslex.splashgallery.feature_collections.di.component.DaggerCollectionsComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CollectionsFragment private constructor() : BaseFragment<FragmentCollectionsBinding>(
    bindingInflater = FragmentCollectionsBinding::inflate
) {

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val adapter: CollectionsAdapter by lazy {
        CollectionsAdapter(
            clickListener = CollectionClickListener(),
            glide = setImage,
            isUser = query is QueryCollections.UserCollections
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCollectionsComponent.builder().context(requireContext()).create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        adapter.addLoadStateListener(::loadStateListener)
        binding.collections.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.collections.launchWhenStarted(adapter::submitData)

        sharedViewModel.currentId.onEach {
            viewModel.setQuery(query)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun loadStateListener(loadState: CombinedLoadStates) {
        with(binding) {
            collections.isVisible = loadState.refresh != LoadState.Loading
            progress.isVisible = loadState.refresh == LoadState.Loading
            progress.setProgress(100, true)
        }
    }

    companion object {
        private var query: QueryCollections = QueryCollections.EmptyQuery

        val instance: (query: QueryCollections) -> CollectionsFragment
            get() = { query ->
                this.query = query
                CollectionsFragment()
            }
    }
}