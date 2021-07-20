package com.stslex.wallpape.ui.main_screen_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding

class MainPagerAdapter : RecyclerView.Adapter<MainPagerViewHolder>() {

    private var listOfElements = mutableListOf<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerPagerMainBinding.inflate(inflater, parent, false)
        return MainPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPagerViewHolder, position: Int) {
        holder.bind(listOfElements[position])
    }

    override fun getItemCount(): Int = listOfElements.size

    fun addItems(listOfElements: List<ImageModel>) {
        val positionStart = this.listOfElements.size
        listOfElements.forEach {
            this.listOfElements.add(it)
        }
        notifyItemRangeChanged(positionStart, listOfElements.size)
    }
}