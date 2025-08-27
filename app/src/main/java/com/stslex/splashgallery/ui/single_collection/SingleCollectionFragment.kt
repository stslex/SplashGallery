package com.stslex.splashgallery.ui.single_collection

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.stslex.splashgallery.core_ui.BaseFragment
import com.stslex.splashgallery.core_ui.SharedViewModel
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentSingleCollectionBinding

class SingleCollectionFragment : BaseFragment<FragmentSingleCollectionBinding>(
    bindingInflater = FragmentSingleCollectionBinding::inflate
) {

    private lateinit var titleExtra: String
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigationArgs()
        setToolbar()
    }

    private fun setToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.mainToolbar)
        activity.supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            this.title = titleExtra
        }
    }

    private fun getNavigationArgs() {
        val extras: SingleCollectionFragmentArgs by navArgs()
        extras.apply {
            binding.mainToolbar.transitionName = transitionName
            titleExtra = title
            viewModel.setId(transitionName)
        }
    }
}