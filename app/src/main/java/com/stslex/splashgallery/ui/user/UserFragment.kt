package com.stslex.splashgallery.ui.user

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.user.pager.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserFragment : BaseFragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }

    private var _username: String? = null
    private val username: String get() = _username!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = getString(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
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
        setListenersHead()
    }

    private fun setListenersHead() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.getUserInfo(username).collect {
            when (it) {
                is UserUIResult.Success -> {
                    with(binding) {
                        it.data.bind(
                            glide = glide,
                            profileImageView = avatarImageView,
                            totalCollectionsTextView = collectionsCountTextView,
                            totalLikesTextView = likesCountTextView,
                            totalPhotosTextView = photoCountTextView,
                            bioTextView = bioTextView
                        )
                    }
                    setViewPager(it.data.getListOfTabs())
                }
                is UserUIResult.Failure -> {
                    Log.i("Failure", it.exception)
                }
                is UserUIResult.Loading -> {

                }
            }
        }
    }

    private val glide = SetImageWithGlide { url, imageView, needCrop, needCircleCrop ->
        setImageWithRequest(url, imageView, needCrop, needCircleCrop)
    }

    private fun setViewPager(fragmentMap: List<Fragment>) {
        binding.userViewPager.adapter = UserAdapter(this, fragmentMap)
        postponeEnterTransition()
        binding.userViewPager.doOnPreDraw {
            startPostponedEnterTransition()
        }
        TabLayoutMediator(
            binding.userTabLayout,
            binding.userViewPager
        ) { tab, position ->
            when (fragmentMap[position]) {
                is UserPhotosFragment -> {
                    tab.text = getString(R.string.label_photos)
                }
                is UserLikesFragment -> {
                    tab.text = getString(R.string.label_likes)
                }
                is UserCollectionFragment -> {
                    tab.text = getString(R.string.label_collections)
                }
            }
            binding.userViewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.userProfileToolbar)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = username
        }
    }

    private fun getNavigationArgs() {
        val extras: UserFragmentArgs by navArgs()
        _username = extras.username
        binding.userProfileToolbar.transitionName = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}