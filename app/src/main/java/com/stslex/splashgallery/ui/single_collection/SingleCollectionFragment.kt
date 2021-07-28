package com.stslex.splashgallery.ui.single_collection

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.FragmentSingleCollectionBinding
import com.stslex.splashgallery.ui.PagerSharedViewModel
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.appComponent
import com.stslex.splashgallery.utils.base.BaseFragment

class SingleCollectionFragment : BaseFragment() {

    private var _binding: FragmentSingleCollectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SingleCollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var collection: CollectionModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationContext.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialContainerTransform(requireContext(), true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        initViewModelListening()
    }

    private fun initViewModelListening() {
        sharedViewModel.pageNumberAllPhotos.observe(viewLifecycleOwner) {
            viewModel.getAllPhotos(collection.id, it)
        }

        viewModel.allPhotos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    sharedViewModel.setAllPhotos(it.data)
                }
                is Result.Failure -> {
                    Log.i("SingleCollection", it.exception)
                }
                is Result.Loading -> {

                }
            }
        }
    }

    private fun getNavigationArgs() {
        val extras: SingleCollectionFragmentArgs by navArgs()
        collection = extras.collectionModel
        val transitionName = extras.transitionName
        binding.collectionFragment.transitionName = transitionName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}