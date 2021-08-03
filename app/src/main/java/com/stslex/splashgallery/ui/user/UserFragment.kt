package com.stslex.splashgallery.ui.user

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.user.pager.collection.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager.likes.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.photos.UserPhotosFragment
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.appComponent
import com.stslex.splashgallery.utils.base.BaseFragment
import com.stslex.splashgallery.utils.setRoundImageWithRequest

class UserFragment : BaseFragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: UserSharedViewModel by activityViewModels()

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
        setListenersHead()
        setListeners()
    }

    private fun setListeners() {
        sharedViewModel.numPhotos.observe(viewLifecycleOwner) {
            viewModel.getUserContentPhotos(username, it)
        }
        viewModel.photos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    sharedViewModel.setPhotos(it.data)
                }
                is Result.Failure -> {
                    Snackbar.make(binding.root, it.exception, Snackbar.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                }
            }
        }
    }

    private fun setListenersHead() {
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
                    if (it.data.bio == null || it.data.bio == "") {
                        binding.contentUserContainer.userBio.visibility = View.GONE
                    } else binding.contentUserContainer.userBio.text = it.data.bio
                    setViewPager(it.data)
                }
                is Result.Failure -> {
                    Log.i("Failure", it.exception)
                }
                is Result.Loading -> {

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
        val fragmentMap: List<Fragment> = map.filter { it.key != 0 }.values.toList()
        binding.contentUserContainer.userViewPager.adapter = UserAdapter(this, fragmentMap)

        TabLayoutMediator(
            binding.contentUserContainer.userTabLayout,
            binding.contentUserContainer.userViewPager
        ) { tab, position ->
            when (fragmentMap[position]) {
                is UserPhotosFragment -> {
                    tab.text = "Photos"
                }
                is UserLikesFragment -> {
                    tab.text = "Likes"
                }
                is UserCollectionFragment -> {
                    tab.text = "Collection"
                }
            }
            binding.contentUserContainer.userViewPager.setCurrentItem(tab.position, true)
        }.attach()
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