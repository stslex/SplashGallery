package com.stslex.splashgallery.ui.user

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.appComponent
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.setRoundImageWithRequest

class UserFragment : BaseFragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }

    private lateinit var username: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().applicationContext.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 700.toLong()
            scrimColor = Color.TRANSPARENT
            isHoldAtEndEnabled = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        setToolbar()
        setListeners()
    }

    private fun setListeners() {
        viewModel.getUserInfo(username)
        viewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    setRoundImageWithRequest(
                        it.data.profile_image!!.large,
                        binding.userProfileImage
                    )
                    binding.userHead.userProfileHeadCollectionsCount.text =
                        it.data.total_collections.toString()
                    binding.userHead.userProfileHeadLikesCount.text = it.data.total_likes.toString()
                    binding.userHead.userProfileHeadPhotoCount.text =
                        it.data.total_photos.toString()
                }
                is Result.Failure -> {

                }
                is Result.Loading -> {

                }
            }
        }
    }

    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.userProfileToolbar)
        activity.supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = username
        }
    }

    private fun getNavigationArgs() {
        val extras: UserFragmentArgs by navArgs()
        username = extras.username
        binding.userCardView.transitionName = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}