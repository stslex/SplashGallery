package com.stslex.splashgallery.ui.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.core.Resource
import com.stslex.core_model.data.user.UserModel
import com.stslex.core_ui.BaseFragment
import com.stslex.core_ui.SharedViewModel
import com.stslex.core_ui.TextUtils.map
import com.stslex.feature_collections.data.QueryCollections
import com.stslex.feature_collections.ui.CollectionsFragment
import com.stslex.splashgallery.R
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentUserBinding
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserFragment : BaseFragment<FragmentUserBinding>(
    bindingInflater = FragmentUserBinding::inflate
) {

    private val viewModel: UserViewModel by viewModels { viewModelFactory.get() }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val extras: UserFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        startPostponedEnterTransition()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getUserInfo(extras.username).collect(::collector)
        }
    }

    private fun collector(response: Resource<UserModel>) = when (response) {
        is Resource.Success -> response.result()
        is Resource.Failure -> response.result()
        is Resource.Loading -> showProgress()
    }

    private fun Resource.Success<UserModel>.result() = with(data) {
        hideProgress()
        bindUserHeader()
        sharedViewModel.setId(username)
        setViewPager(listOfTabs)
    }

    private val UserModel.listOfTabs: List<Fragment>
        get() = mapOf(
            total_photos to UserPhotosFragment(),
            total_likes to UserLikesFragment(),
            total_collections to CollectionsFragment.instance(
                QueryCollections.UserCollections(
                    sharedViewModel.currentId.replayCache.last()
                )
            )
        ).filter { it.key != 0 }.values.toList()

    private fun Resource.Failure<UserModel>.result() {
        hideProgress()
        Log.e(TAG, exception.message, exception)
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun UserModel.bindUserHeader() {
        with(binding) {
            setImage.setImage(profile_image.large, avatarImageView, needCircleCrop = true)
            collectionsCountTextView.map(total_collections.toString())
            likesCountTextView.map(total_likes.toString())
            photoCountTextView.map(total_photos.toString())
            if (bio.isEmpty()) {
                bioTextView.isVisible = false
            } else {
                bioTextView.map(bio)
            }
        }
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

    companion object {
        private const val TAG = "user fragment"
    }
}