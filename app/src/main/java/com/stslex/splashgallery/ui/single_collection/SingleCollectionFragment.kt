package com.stslex.splashgallery.ui.single_collection

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentSingleCollectionBinding
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.base.BaseFragment

class SingleCollectionFragment : BaseFragment() {

    private var _binding: FragmentSingleCollectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SingleCollectionViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SingleCollectionSharedViewModel by activityViewModels()
    private lateinit var id: String
    private lateinit var titleExtra: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = getString(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCollectionBinding.inflate(inflater, container, false)
        getNavigationArgs()
        setToolbar()
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        sharedViewModel.numberPhotos.observe(viewLifecycleOwner) {
            viewModel.getAllPhotos(id, it)
        }
        viewModel.allPhotos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    sharedViewModel.setPhotos(it.data)
                }
                is Result.Failure -> {
                    Log.i("Failure: $this", it.exception)
                }
                is Result.Loading -> {

                }
            }
        }
    }

    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.mainToolbar)
        activity.supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            this.title = titleExtra
        }
    }

    private fun getNavigationArgs() {
        val extras: SingleCollectionFragmentArgs by navArgs()
        id = extras.transitionName
        binding.mainToolbar.transitionName = id
        titleExtra = extras.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}