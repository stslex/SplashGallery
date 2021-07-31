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
            duration = 300.toLong()
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
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentPhoto(id)
        setListener()
        binding.singlePhotoImage.setOnClickListener(this)
        binding.singlePhotoProfileContainer.setOnClickListener(this)
    }

    private fun setListener() {
        viewModel.currentPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    it.data.run {
                        setImageWithRequest(url = urls.regular, binding.singlePhotoImage, true)
                        binding.singlePhotoProfileImage.downloadAndSetSmallRound(user?.profile_image!!.medium)
                        binding.singlePhotoProfileUsername.text = user.username
                        binding.singlePhotoAperture.text = exif?.aperture
                        binding.singlePhotoCamera.text = exif?.model
                        binding.singlePhotoDimension.text = exif?.exposure_time
                        binding.singlePhotoFocal.text = exif?.focal_length
                        binding.singlePhotoProfileContainer.transitionName = user.id
                    }
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

    private val clickListener = ImageClickListener({ imageView, id ->
        val directions = SinglePhotoFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            transitionName = imageView.transitionName,
            id = id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, { user ->
        val directions = SinglePhotoFragmentDirections.actionNavSinglePhotoToNavUser(
            user.transitionName
        )
        val extras = FragmentNavigatorExtras(user to user.transitionName)
        findNavController().navigate(directions, extras)
    })

    override fun onClick(p0: View?) {
        when (p0) {
            binding.singlePhotoImage -> {
                clickListener.onImageClick(binding.singlePhotoImage, id)
            }
            binding.singlePhotoProfileContainer -> {
                clickListener.onUserCLick(binding.singlePhotoProfileContainer)
            }
        }
    }
}