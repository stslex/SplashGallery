package com.stslex.splashgallery.ui.single_photo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentSingleImageBinding
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class SingleImageFragment : BaseFragment() {
    private var _binding: FragmentSingleImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = getString(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        binding.root.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getNavigationArgs() {
        postponeEnterTransition()
        val extras: SingleImageFragmentArgs by navArgs()
        binding.fragmentSingleImageImage.transitionName = extras.url
        setImageWithRequest(extras.url, binding.fragmentSingleImageImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}