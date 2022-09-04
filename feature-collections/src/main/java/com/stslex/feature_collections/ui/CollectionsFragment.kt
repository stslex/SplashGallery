package com.stslex.feature_collections.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.stslex.core_ui.BaseFragment
import com.stslex.core_ui.SharedViewModel
import com.stslex.feature_collections.data.QueryCollections
import com.stslex.feature_collections.databinding.FragmentCollectionsBinding
import com.stslex.feature_collections.di.component.DaggerCollectionsComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class CollectionsFragment : BaseFragment<FragmentCollectionsBinding>(
    bindingInflater = FragmentCollectionsBinding::inflate
) {

    private val viewModel: CollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val adapter: CollectionsAdapter by lazy {
        CollectionsAdapter(
            clickListener = CollectionClickListener(WeakReference(requireParentFragment())),
            glide = setImage,
            isUser = isUser
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCollectionsComponent.builder().context(requireContext()).create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryJob.start()
        adapter.addLoadStateListener(::loadStateListener)
        viewModel.collections.launchWhenStarted(adapter::submitData)
    }

    private fun loadStateListener(loadState: CombinedLoadStates) {
        with(binding) {
            collections.isVisible = loadState.refresh != LoadState.Loading
            progress.isVisible = loadState.refresh == LoadState.Loading
            progress.setProgress(100, true)
        }
    }

    private val queryJob: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            sharedViewModel.currentId.collect(::collectorId)
        }
    }

    private fun collectorId(id: String) {
        /*val query = when (requireParentFragment()) {
            is MainFragment -> QueryCollections.AllCollections
            is UserFragment -> QueryCollections.UserCollections(id)
            else -> QueryCollections.EmptyQuery
        }*/
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.setQuery(queryCollections)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        queryJob.cancel()
    }

    companion object {
        private var isUser: Boolean = false
        private var queryCollections: QueryCollections = QueryCollections.EmptyQuery

        val instance: (isUser: Boolean, query: QueryCollections) -> CollectionsFragment
            get() = { isUser, query ->
                this.isUser = isUser
                this.queryCollections = query
                CollectionsFragment()
            }
    }
}