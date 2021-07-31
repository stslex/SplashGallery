package com.stslex.splashgallery.ui.user

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var username: String

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