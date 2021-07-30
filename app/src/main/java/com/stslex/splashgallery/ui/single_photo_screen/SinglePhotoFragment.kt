package com.stslex.splashgallery.ui.single_photo_screen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
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

    private lateinit var id: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.applicationContext.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 700.toLong()
            scrimColor = Color.TRANSPARENT
        }
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
        binding.singlePhotoImage.setOnClickListener(this)
    }

    private fun setListener() {
        viewModel.getCurrentPhoto(id)
        viewModel.currentPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    setImageWithRequest(url = it.data.urls.regular, binding.singlePhotoImage, true)
                    binding.singlePhotoProfileImage.downloadAndSetSmallRound(it.data.user?.profile_image!!.medium)
                    binding.singlePhotoProfileUsername.text = it.data.user.username
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

    private fun getNavigationArgs() {
        postponeEnterTransition()
        val extras: SinglePhotoFragmentArgs by navArgs()
        id = extras.id
        binding.singlePhotoImage.transitionName = extras.transitionName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val clickListener = ImageClickListener { imageView, id ->
        val directions = SinglePhotoFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            transitionName = imageView.transitionName,
            id = id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.singlePhotoImage -> {
                clickListener.onClick(binding.singlePhotoImage, id)
            }
        }
    }
}