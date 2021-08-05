package com.stslex.splashgallery.ui.photo_details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentPhotoDetailsBinding
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.setImageWithRequest

class PhotoDetailsFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoDetailsViewModel by viewModels { viewModelFactory.get() }

    private lateinit var id: String

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
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        setToolbar()
        setListener()
        binding.singlePhotoImage.setOnClickListener(this)
    }

    private fun setListener() {
        viewModel.getCurrentPhoto(id)
        viewModel.currentPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    it.data.run {
                        setImageWithRequest(
                            user?.profile_image!!.medium,
                            binding.singlePhotoProfileImage,
                            needCircleCrop = true
                        )
                        binding.singlePhotoProfileUsername.text = user.username
                        binding.singlePhotoAperture.text = exif?.aperture
                        binding.singlePhotoCamera.text = exif?.model
                        binding.singlePhotoDimension.text = exif?.exposure_time
                        binding.singlePhotoFocal.text = exif?.focal_length
                        binding.singlePhotoProfileUsername.transitionName = user.username
                        binding.singlePhotoProfileContainer.setOnClickListener {
                            clickListener.onUserCLick(binding.singlePhotoProfileUsername)
                        }
                        binding.singlePhotoDownload.setOnClickListener {

                        }
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
        val extras: PhotoDetailsFragmentArgs by navArgs()
        id = extras.id
        binding.singlePhotoImage.transitionName = extras.transitionName
        setImageWithRequest(url = extras.transitionName, binding.singlePhotoImage, true)
    }

    private fun setToolbar() {
        (requireActivity() as AppCompatActivity).run {
            setSupportActionBar(binding.singlePhotoToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val clickListener = ImageClickListener({ imageView, id ->
        val directions = PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            transitionName = imageView.transitionName,
            id = id
        )
        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
        findNavController().navigate(directions, extras)
    }, { user ->
        val directions = PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavUser(
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
        }
    }
}