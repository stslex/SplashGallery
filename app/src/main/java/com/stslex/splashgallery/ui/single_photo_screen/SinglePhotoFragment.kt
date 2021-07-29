package com.stslex.splashgallery.ui.single_photo_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.FragmentSinglePhotoBinding
import com.stslex.splashgallery.utils.downloadAndSetSmallRound
import com.stslex.splashgallery.utils.setImageWithRequest

class SinglePhotoFragment : Fragment() {

    private var _binding: FragmentSinglePhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var image: ImageModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialContainerTransform(requireContext(), true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinglePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        bindImage()
    }

    private fun bindImage() {
        setImageWithRequest(url = image.urls.regular, binding.singlePhotoImage)
        binding.singlePhotoProfileImage.downloadAndSetSmallRound(image.user?.profile_image!!.medium)
        binding.singlePhotoProfileUsername.text = image.user!!.username
    }

    private fun getNavigationArgs() {
        postponeEnterTransition()
        val extras: SinglePhotoFragmentArgs by navArgs()
        image = extras.imageModel
        val transitionName = extras.transitionName
        binding.root.transitionName = transitionName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}