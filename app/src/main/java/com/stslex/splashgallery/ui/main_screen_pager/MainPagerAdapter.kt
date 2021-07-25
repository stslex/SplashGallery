package com.stslex.wallpape.ui.main_screen_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding

class MainPagerAdapter : RecyclerView.Adapter<MainPagerViewHolder>() {

    private var listOfElements = mutableListOf<ImageModel>()
    private var listOfCollection = mutableListOf<CollectionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerPagerMainBinding.inflate(inflater, parent, false)
        return MainPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPagerViewHolder, position: Int) {
        if (listOfCollection.size != 0) holder.bind(listOfCollection[position])
    }

    override fun getItemCount(): Int = listOfCollection.size

    fun addItemsOfPhoto(listOfElements: List<ImageModel>) {
        val positionStart = this.listOfElements.size
        listOfElements.forEach {
            this.listOfElements.add(it)
        }
        notifyItemRangeChanged(positionStart, this.listOfElements.size)
    }

    fun addItemsOfCollection(listOfCollection: List<CollectionModel>) {
        val positionStart = this.listOfCollection.size
        listOfCollection.forEach {
            this.listOfCollection.add(it)
        }
        notifyItemRangeChanged(positionStart, this.listOfCollection.size)
    }

}