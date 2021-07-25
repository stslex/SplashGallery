package com.stslex.wallpape.ui.main_screen_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding

class MainPagerAdapter : RecyclerView.Adapter<MainPagerViewHolder>() {

    private var listOfAllImages = mutableListOf<ImageModel>()
    private var listOfCollection = mutableListOf<CollectionModel>()
    private var flag: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerPagerMainBinding.inflate(inflater, parent, false)
        return MainPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPagerViewHolder, position: Int) {
        if (flag) holder.bind(listOfAllImages[position])
        else holder.bind(listOfCollection[position])
    }

    override fun getItemCount(): Int = if (flag) {
        listOfAllImages.size
    } else listOfCollection.size

    fun isAllPhotos(flag: Boolean) {
        this.flag = flag
    }

    fun addItemsOfPhoto(listOfAllImages: List<ImageModel>) {
        val positionStart = listOfAllImages.size
        listOfAllImages.forEach {
            this.listOfAllImages.add(it)
        }
        notifyItemRangeChanged(positionStart, this.listOfAllImages.size)
    }

    fun addItemsOfCollection(listOfCollection: List<CollectionModel>) {
        val positionStart = listOfCollection.size
        listOfCollection.forEach {
            this.listOfCollection.add(it)
        }
        notifyItemRangeChanged(positionStart, this.listOfCollection.size)
    }

}