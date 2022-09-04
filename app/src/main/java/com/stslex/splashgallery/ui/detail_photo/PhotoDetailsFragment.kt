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
import com.stslex.core.Resource
import com.stslex.core_ui.BaseFragment
import com.stslex.splashgallery.R
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentPhotoDetailsBinding
import com.stslex.splashgallery.ui.model.DownloadModel
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.ui.utils.isNullCheck
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
class PhotoDetailsFragment : BaseFragment<FragmentPhotoDetailsBinding>(
    bindingInflater = FragmentPhotoDetailsBinding::inflate,
    hostFragmentId = R.id.nav_host_fragment
) {

    private val viewModel: PhotoDetailsViewModel by viewModels { viewModelFactory.get() }
    private var _url: String? = null
    private val url: String get() = checkNotNull(_url)

    private var _id: String? = null
    private val id: String get() = checkNotNull(_id)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras: PhotoDetailsFragmentArgs by navArgs()
        _url = extras.url
        _id = extras.id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageImageView.transitionName = id
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            setImage.setImage(url, binding.imageImageView, needCrop = true, false)
        }
        startPostponedEnterTransition()
        setToolbar()
        getImageJob.start()
        binding.imageImageView.setOnClickListener(imageClickListener)
        binding.userCardView.setOnClickListener(userClickListener)
        binding.singlePhotoDownload.setOnClickListener(downloadClickListener)
    }

    private val imageClickListener: View.OnClickListener = View.OnClickListener {
        val directions = PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            id = it.transitionName,
            url = url
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
            viewModel.getCurrentPhoto(id).collectLatest(::collected)
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
    private suspend fun Resource.Success<ImageModel>.result() =
        withContext(Dispatchers.Main) {
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
            viewModel.downloadImageUrl(id).collect(::collector)
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
        viewModel.downloadImage(data.url, id).collect(::collector)
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

    private fun setToolbar() = with(requireActivity() as AppCompatActivity) {
        setSupportActionBar(binding.singlePhotoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        downloadJob?.cancel()
        getImageJob.cancel()
    }
}