package com.stslex.splashgallery.ui.single_photo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stslex.splashgallery.appComponent
import com.stslex.splashgallery.databinding.FragmentSingleImageBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class SingleImageFragment :
    BaseFragment<FragmentSingleImageBinding>(FragmentSingleImageBinding::inflate),
    View.OnClickListener {

    private val extras: SingleImageFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSingleImageImage.transitionName = extras.id
        setImage.setImage(extras.url, binding.fragmentSingleImageImage)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        findNavController().popBackStack()
    }
}