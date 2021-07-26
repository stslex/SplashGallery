package com.stslex.splashgallery.ui.main_screen_pager.all_photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding

class AllPhotosAdapter : RecyclerView.Adapter<AllPhotosViewHolder>() {

    private var list = mutableListOf<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerPagerMainBinding.inflate(inflater, parent, false)
        return AllPhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllPhotosViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(list: List<ImageModel>) {
        val startItems = this.list.size
        this.list.addAll(list)
        notifyItemRangeChanged(startItems, this.list.size)
    }
}