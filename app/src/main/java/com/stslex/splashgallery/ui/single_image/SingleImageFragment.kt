package com.stslex.splashgallery.ui.single_image

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentSingleImageBinding
import com.stslex.splashgallery.utils.setImageWithRequest


class SingleImageFragment : Fragment() {
    private var _binding: FragmentSingleImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 700.toLong()
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
        val url = extras.transitionName
        binding.fragmentSingleImageImage.transitionName = url
        setImageWithRequest(url, binding.fragmentSingleImageImage, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}