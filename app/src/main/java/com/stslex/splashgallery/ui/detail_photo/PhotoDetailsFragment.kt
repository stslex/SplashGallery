package com.stslex.splashgallery.ui.detail_photo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.databinding.FragmentPhotoDetailsBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.model.DownloadModel
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.utils.isNullCheck
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import java.lang.ref.WeakReference

@ExperimentalCoroutinesApi
class PhotoDetailsFragment : BaseFragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoDetailsViewModel by viewModels { viewModelFactory.get() }
    private val extras: PhotoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageImageView.transitionName = extras.id
        setImage.setImage(extras.url, binding.imageImageView, needCrop = true, false)
        setToolbar()
        getImageJob.start()
        binding.imageImageView.setOnClickListener(imageClickListener)
        binding.userCardView.setOnClickListener(userClickListener)
        binding.singlePhotoDownload.setOnClickListener(downloadClickListener)
    }

    private val imageClickListener: View.OnClickListener = View.OnClickListener {
        val directions = PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            id = it.transitionName,
            url = extras.url
        )
        val extras = FragmentNavigatorExtras(it to it.transitionName)
        findNavController().navigate(directions, extras)
    }

    private val userClickListener: View.OnClickListener = View.OnClickListener {
        val directions =
            PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavUser(it.transitionName)
        val extras = FragmentNavigatorExtras(it to it.transitionName)
        findNavController().navigate(directions, extras)
    }

    private val getImageJob: Job
        get() = viewLifecycleOwner.lifecycleScope.launch(
            context = Dispatchers.IO, start = CoroutineStart.LAZY
        ) {
            viewModel.getCurrentPhoto(extras.id).collectLatest(::collected)
        }


    @JvmName("resultImageModel")
    private suspend fun collected(response: Resource<ImageModel>) =
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            when (response) {
                is Resource.Success -> response.result()
                is Resource.Failure -> response.result()
                is Resource.Loading -> loading()
            }
        }

    @JvmName("resultImageModel")
    private suspend fun Resource.Success<ImageModel>.result() = withContext(Dispatchers.Main) {
        with(binding) {
            with(data) {
                setImage.setImage(
                    url = user?.profile_image?.medium.toString(),
                    imageView = avatarImageView,
                    needCrop = true,
                    needCircleCrop = true
                )
                userCardView.transitionName = user?.username
                usernameTextView.text = user?.username
                apertureTextView.text = exif?.aperture.isNullCheck()
                cameraTextView.text = exif?.make.isNullCheck()
                dimensionTextView.text = exif?.exposure_time.isNullCheck()
                focalTextView.text = exif?.focal_length.isNullCheck()
            }
        }
    }

    private var downloadJob: Job? = null
    private val downloadClickListener: View.OnClickListener = View.OnClickListener {
        downloadJob?.cancel()
        downloadJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.downloadImageUrl(extras.id).collect(::collected)
        }
    }

    @JvmName("resultDownloadModel")
    private suspend fun collected(response: Resource<DownloadModel>) = when (response) {
        is Resource.Success -> response.result()
        is Resource.Failure -> response.result()
        is Resource.Loading -> loading()
    }

    @JvmName("resultDownloadModel")
    private suspend fun Resource.Success<DownloadModel>.result() {
        viewModel.downloadImage(data.url, extras.id).collect(::collected)
    }

    @JvmName("resultDownload")
    private fun collected(response: Resource<Nothing?>) = when (response) {
        is Resource.Success -> Unit
        is Resource.Failure -> response.result()
        is Resource.Loading -> loading()
    }


    private fun loading() = Unit
    private fun <T> Resource.Failure<T>.result() {
        Log.e(TAG, exception.message, exception)
    }

    private val setImage by lazy {
        setImageWithGlide.create { url, imageView, crop, circleCrop ->
            imageSetter.get().setImage(WeakReference(this), url, imageView, crop, circleCrop)
        }
    }

    private fun setToolbar() = with(requireActivity() as AppCompatActivity) {
        setSupportActionBar(binding.singlePhotoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        downloadJob?.cancel()
        getImageJob.cancel()
    }

    companion object {
        private const val TAG = "PhotoDetailsFragment"
    }
}