package com.stslex.splashgallery.ui.user

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex.splashgallery.R
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.collections.CollectionsFragment
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Resources
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserFragment : BaseFragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: UserSharedViewModel by activityViewModels()

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
        viewModel.getUserInfo(username).collect { user ->
            when (user) {
                is UIResult.Success -> {
                    with(binding) {
                        avatarImageView.load(user.data.profile_image?.medium.toString()) {
                            placeholder(ColorDrawable(Color.GRAY))
                            transformations(CircleCropTransformation())
                        }
                        Resources.currentId = username
                        collectionsCountTextView.text = user.data.total_collections.toString()
                        likesCountTextView.text = user.data.total_likes.toString()
                        photoCountTextView.text = user.data.total_photos.toString()
                        if (user.data.bio.isEmpty()) {
                            bioTextView.visibility = View.VISIBLE
                        } else {
                            bioTextView.visibility = View.INVISIBLE
                            bioTextView.text = user.data.bio
                        }
                    }
                    setId(user.data.username).start()
                    val listOfTabs = mapOf(
                        user.data.total_photos to UserPhotosFragment(),
                        user.data.total_likes to UserLikesFragment(),
                        user.data.total_collections to CollectionsFragment()
                    ).filter { it.key != 0 }.values.toList()

                    setViewPager(listOfTabs)
                }
                is UIResult.Failure -> {
                    Log.i("Failure", user.exception.toString())
                }
                is UIResult.Loading -> {

                }
            }
        }
    }

    private fun setViewPager(fragmentMap: List<Fragment>) {
        binding.userViewPager.adapter = UserAdapter(this, fragmentMap)

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
                is CollectionsFragment -> {
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

    private fun setId(id: String) = viewLifecycleOwner.lifecycleScope.launch {
        sharedViewModel.setId(id)
    }

    private fun getNavigationArgs() {
        val extras: UserFragmentArgs by navArgs()
        _username = extras.username
        binding.userProfileToolbar.transitionName = username
    }

    override fun onStop() {
        super.onStop()
        setId("").cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}