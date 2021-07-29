package com.stslex.splashgallery.ui.single_photo_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.FragmentSinglePhotoBinding
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.appComponent
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.downloadAndSetSmallRound
import com.stslex.splashgallery.utils.setImageWithRequest

class SinglePhotoFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentSinglePhotoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SinglePhotoViewModel by viewModels { viewModelFactory.get() }

    private lateinit var image: ImageModel

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
        _binding = FragmentSinglePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        setListener()
        bindImage()
        binding.singlePhotoImage.setOnClickListener(this)
    }

    private fun setListener() {
        viewModel.getCurrentPhoto(image.id)
        viewModel.currentPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    binding.singlePhotoAperture.text = it.data.exif?.aperture
                    binding.singlePhotoCamera.text = it.data.exif?.model
                    binding.singlePhotoDimension.text = it.data.exif?.exposure_time
                    binding.singlePhotoFocal.text = it.data.exif?.focal_length
                }
                is Result.Failure -> {

                }
                is Result.Loading -> {

                }
            }
        }
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
        binding.singlePhotoImage.transitionName = transitionName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val clickListener = ImageClickListener { imageModel, imageView ->
        val directions = SinglePhotoFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            imageModel.urls.regular,
            imageView.transitionName
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.singlePhotoImage -> {
                clickListener.onClick(image, binding.singlePhotoImage)
            }
        }
    }
}