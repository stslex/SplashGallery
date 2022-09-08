package com.stslex.splashgallery.ui.detail_photo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.stslex.core.Resource
import com.stslex.core_model.data.DownloadModel
import com.stslex.core_model.data.image.ImageDataModel
import com.stslex.core_ui.BaseFragment
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentPhotoDetailsBinding
import com.stslex.splashgallery.ui.utils.isNullCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class PhotoDetailsFragment : BaseFragment<FragmentPhotoDetailsBinding>(
    bindingInflater = FragmentPhotoDetailsBinding::inflate
) {

    private val viewModel: PhotoDetailsViewModel by viewModels { viewModelFactory.get() }
    private val extras: PhotoDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageImageView.transitionName = extras.id
        startPostponedEnterTransition()
        observe()
        binding.bindUI()
    }

    private fun observe() {
        viewModel.getCurrentPhoto(extras.id).launchWhenStarted { response ->
            when (response) {
                is Resource.Success -> response.result()
                is Resource.Failure -> response.result()
                is Resource.Loading -> loading()
            }
        }
    }

    private fun FragmentPhotoDetailsBinding.bindUI() {
        setToolbar()
        imageImageView.setOnClickListener(imageClickListener)
        userCardView.setOnClickListener(userClickListener)
        singlePhotoDownload.setOnClickListener(downloadClickListener)
        Glide.with(imageImageView)
            .load(extras.url)
            .centerCrop()
            .into(imageImageView)
    }

    private val imageClickListener: View.OnClickListener by lazy {
        DetailPhotoClickListener(extras.url)
    }

    private val userClickListener: View.OnClickListener = View.OnClickListener {
        val directions =
            PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavUser(it.transitionName)
        val extras = FragmentNavigatorExtras(it to it.transitionName)
        findNavController().navigate(directions, extras)
    }

    @JvmName("resultImageModel")
    private fun Resource.Success<ImageDataModel>.result() {
        with(binding) {
            with(data) {
                setImage.setImage(
                    url = user.profile_image.medium,
                    imageView = avatarImageView,
                    needCrop = true,
                    needCircleCrop = true
                )
                userCardView.transitionName = user.username
                usernameTextView.text = user.username
                apertureTextView.text = exif.aperture.isNullCheck()
                cameraTextView.text = exif.make.isNullCheck()
                dimensionTextView.text = exif.exposure_time.isNullCheck()
                focalTextView.text = exif.focal_length.isNullCheck()
            }
        }
    }

    private var downloadJob: Job? = null
    private val downloadClickListener: View.OnClickListener = View.OnClickListener {
        downloadJob?.cancel()
        downloadJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.downloadImageUrl(extras.id).collect(::collector)
        }
    }

    @JvmName("resultDownloadModel")
    private suspend fun collector(response: Resource<DownloadModel>) = when (response) {
        is Resource.Success -> response.result()
        is Resource.Failure -> response.result()
        is Resource.Loading -> loading()
    }

    @JvmName("resultDownloadModel")
    private suspend fun Resource.Success<DownloadModel>.result() {
        viewModel.downloadImage(data.url, extras.id).collect(::collector)
    }

    @JvmName("resultDownload")
    private fun collector(response: Resource<Nothing?>) = when (response) {
        is Resource.Success -> Unit
        is Resource.Failure -> response.result()
        is Resource.Loading -> loading()
    }


    private fun loading() = Unit
    private fun <T> Resource.Failure<T>.result() {
        exception.printStackTrace()
    }

    private fun setToolbar() {
        with(requireActivity() as AppCompatActivity) {
            setSupportActionBar(binding.singlePhotoToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        downloadJob?.cancel()
    }
}