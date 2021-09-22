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
import com.stslex.splashgallery.core.model.domain.user.UserModel
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.user.pager.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.setImageWithRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserFragment : BaseFragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }
    private lateinit var fragmentMap: List<Fragment>
    private lateinit var username: String

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
                    currentId = it.data.username.toString()
                    setImageWithRequest(
                        it.data.profile_image!!.large,
                        binding.userProfileImage,
                        needCircleCrop = true
                    )
                    binding.userProfileHeadCollectionsCount.text =
                        it.data.total_collections.toString()
                    binding.userProfileHeadLikesCount.text = it.data.total_likes.toString()
                    binding.userProfileHeadPhotoCount.text =
                        it.data.total_photos.toString()
                    if (it.data.bio == null || it.data.bio == "") {
                        binding.userBio.visibility = View.GONE
                    } else binding.userBio.text = it.data.bio
                    setViewPager(it.data)
                }
                is UserUIResult.Failure -> {
                    Log.i("Failure", it.exception)
                }
                is UserUIResult.Loading -> {

                }
            }
        }
    }

    private fun setViewPager(data: UserModel) {
        val map = mapOf(
            (data.total_photos ?: 0) to UserPhotosFragment(),
            (data.total_likes ?: 0) to UserLikesFragment(),
            (data.total_collections) to UserCollectionFragment()
        )
        fragmentMap = map.filter { it.key != 0 }.values.toList()

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
        username = extras.username
        binding.userProfileToolbar.transitionName = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}