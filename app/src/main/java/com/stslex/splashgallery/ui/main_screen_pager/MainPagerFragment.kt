package com.stslex.wallpape.ui.main_screen_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.FragmentMainPagerBinding
import com.stslex.splashgallery.ui.main_screen_pager.PagerSharedViewModel

class MainPagerFragment : Fragment() {

    private var _binding: FragmentMainPagerBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: PagerSharedViewModel by activityViewModels()

    private lateinit var adapter: MainPagerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutInflater: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MainPagerAdapter()
        recyclerView = binding.mainPagerRecycler
        layoutInflater = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutInflater
        recyclerView.adapter = adapter

        sharedViewModel.page.observe(viewLifecycleOwner){
            adapter.addItems(it.image)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}