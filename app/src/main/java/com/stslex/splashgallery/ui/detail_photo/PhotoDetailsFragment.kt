package com.stslex.splashgallery.ui.detail_photo

import android.app.DownloadManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentPhotoDetailsBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.core.CoilListener
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.utils.isNullCheck
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class PhotoDetailsFragment : BaseFragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoDetailsViewModel by viewModels { viewModelFactory.get() }

    private lateinit var id: String
    private lateinit var url: String

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
        binding.imageImageView.setOnClickListener { image ->
            val directions =
                PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavSingleImage(
                    id = image.transitionName,
                    url = url
                )
            val extras = FragmentNavigatorExtras(image to image.transitionName)
            findNavController().navigate(directions, extras)
        }

        binding.userCardView.setOnClickListener { card ->
            val directions =
                PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavUser(card.transitionName)
            val extras = FragmentNavigatorExtras(card to card.transitionName)
            findNavController().navigate(directions, extras)
        }
    }

    private fun setListener() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.getCurrentPhoto(id).collect {
            when (it) {
                is UIResult.Success -> {
                    with(it.data) {
                        binding.avatarImageView.load(user?.profile_image?.medium.toString()) {
                            placeholder(ColorDrawable(Color.GRAY))
                            transformations(RoundedCornersTransformation())
                            listener(CoilListener { startPostponedEnterTransition() })
                        }
                        binding.avatarImageView.load(user?.profile_image?.medium!!) {
                            placeholder(ColorDrawable(Color.GRAY))
                            transformations(CircleCropTransformation())
                        }
                        binding.userCardView.transitionName = user.username
                        binding.usernameTextView.text = user.username
                        binding.apertureTextView.text = exif?.aperture.isNullCheck()
                        binding.cameraTextView.text = exif?.make.isNullCheck()
                        binding.dimensionTextView.text = exif?.exposure_time.isNullCheck()
                        binding.focalTextView.text = exif?.focal_length.isNullCheck()
                    }

                    binding.singlePhotoDownload.setOnClickListener {
                        downloadPhoto(id)
                    }
                }
                is UIResult.Failure -> {
                }
                is UIResult.Loading -> {
                }
            }
        }
    }


    private fun downloadPhoto(id: String) = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.downloadPhoto(id).collect {
            when (it) {
                is UIResult.Success -> {
                    async {
                        download(it.data.url, id)
                    }.onAwait

                }
                is UIResult.Failure -> {

                }
                is UIResult.Loading -> {

                }
            }
        }
    }

    private suspend fun download(url: String, fileName: String) = withContext(
        viewLifecycleOwner
            .lifecycleScope
            .coroutineContext
    ) {
        val downloadManager =
            requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle("Downloading")
            .setDescription("Downloading image...")
            .setDestinationInExternalFilesDir(
                requireContext(),
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager.enqueue(request)
    }

    private fun getNavigationArgs() {
        val extras: PhotoDetailsFragmentArgs by navArgs()
        id = extras.id
        url = extras.url
        binding.imageImageView.transitionName = id
        binding.imageImageView.load(url) {
            placeholder(ColorDrawable(Color.GRAY))
            transformations(RoundedCornersTransformation())
            listener(CoilListener { startPostponedEnterTransition() })
        }
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
}