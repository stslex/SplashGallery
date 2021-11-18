package com.stslex.splashgallery.ui.single_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stslex.splashgallery.databinding.FragmentSingleImageBinding
import com.stslex.splashgallery.ui.core.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.ref.WeakReference


@ExperimentalCoroutinesApi
class SingleImageFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentSingleImageBinding? = null
    private val binding: FragmentSingleImageBinding
        get() = checkNotNull(_binding)

    private val extras: SingleImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSingleImageImage.transitionName = extras.id
        val imageSetter = imageSetter.get()
        imageSetter.setImage(WeakReference(this), extras.url, binding.fragmentSingleImageImage)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}