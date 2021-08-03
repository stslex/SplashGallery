package com.stslex.splashgallery.ui.user.pager.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stslex.splashgallery.databinding.FragmentUserLikesBinding
import com.stslex.splashgallery.ui.user.UserSharedViewModel

class UserLikesFragment : Fragment() {

    private var _binding: FragmentUserLikesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLikesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}