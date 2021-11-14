package com.stslex.splashgallery.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.splashgallery.R
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.activity.SharedViewModel
import com.stslex.splashgallery.ui.collections.CollectionsFragment
import com.stslex.splashgallery.ui.core.BaseFragment
import com.stslex.splashgallery.ui.model.user.UserModel
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
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val extras: UserFragmentArgs by navArgs()

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
        setToolbar()
        setListenersHead()
    }

    private fun setListenersHead() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.getUserInfo(extras.username).collect { user ->
            when (user) {
                is Resource.Success -> user.result()
                is Resource.Failure -> user.result()
                is Resource.Loading -> loading()
            }
        }
    }

    private fun Resource.Success<UserModel>.result() = with(data) {
        bindUserHeader()
        sharedViewModel.setId(username)
        val listOfTabs = mapOf(
            total_photos to UserPhotosFragment(),
            total_likes to UserLikesFragment(),
            total_collections to CollectionsFragment()
        ).filter { it.key != 0 }.values.toList()
        setViewPager(listOfTabs)
    }

    private fun Resource.Failure<UserModel>.result() {
        Log.i("Failure", exception.toString())
    }

    private fun loading() {
        /*Loading*/
    }

    private fun UserModel.bindUserHeader() = with(binding) {
        glide.setImage(
            profile_image?.medium.toString(),
            avatarImageView.getImage(),
            needCrop = false,
            needCircleCrop = true
        )
        collectionsCountTextView.map(total_collections.toString())
        likesCountTextView.map(total_likes.toString())
        photoCountTextView.map(total_photos.toString())
        if (bio.isEmpty()) {
            bioTextView.hide()
        } else {
            bioTextView.show()
            bioTextView.map(bio)
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
        fragmentMap.tabLayoutMediator.attach()
    }

    private val List<Fragment>.tabLayoutMediator: TabLayoutMediator
        get() = TabLayoutMediator(
            binding.userTabLayout,
            binding.userViewPager
        ) { tab, position ->
            when (this[position]) {
                is UserPhotosFragment -> tab.text = getString(R.string.label_photos)
                is UserLikesFragment -> tab.text = getString(R.string.label_likes)
                is CollectionsFragment -> tab.text = getString(R.string.label_collections)
            }
            binding.userViewPager.setCurrentItem(tab.position, true)
        }

    private fun setToolbar() = with(requireActivity() as AppCompatActivity) {
        binding.userProfileToolbar.transitionName = extras.username
        setSupportActionBar(binding.userProfileToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = extras.username
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}