package com.stslex.splashgallery.ui.user.pager.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stslex.splashgallery.databinding.FragmentUserCollectionBinding
import com.stslex.splashgallery.ui.user.UserSharedViewModel

class UserCollectionFragment : Fragment() {

    private var _binding: FragmentUserCollectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}