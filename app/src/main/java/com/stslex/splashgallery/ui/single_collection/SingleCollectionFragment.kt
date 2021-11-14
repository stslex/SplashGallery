package com.stslex.splashgallery.ui.single_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.stslex.splashgallery.databinding.FragmentSingleCollectionBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.core.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SingleCollectionFragment : BaseFragment() {

    private var _binding: FragmentSingleCollectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var titleExtra: String
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCollectionBinding.inflate(inflater, container, false)
        getNavigationArgs()
        setToolbar()
        return binding.root
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
        extras.apply {
            binding.mainToolbar.transitionName = transitionName
            titleExtra = title
            viewModel.setId(transitionName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}