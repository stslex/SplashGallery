package com.stslex.splashgallery.ui.single_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.databinding.FragmentSingleImageBinding
import com.stslex.splashgallery.utils.setImageWithRequest


class SingleImageFragment : Fragment() {
    private var _binding: FragmentSingleImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialContainerTransform(requireContext(), true)
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
        val url = extras.url
        val transitionName = extras.transitionName
        binding.fragmentSingleImageImage.transitionName = transitionName
        setImageWithRequest(url, binding.fragmentSingleImageImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}