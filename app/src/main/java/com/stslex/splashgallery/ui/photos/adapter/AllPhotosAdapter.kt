package com.stslex.splashgallery.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.utils.ImageDiffUtilCallback

class AllPhotosAdapter(
    private val clickListener: ClickListener<PhotosUI>,
    private val isUser: Boolean = false,
) : RecyclerView.Adapter<AllPhotosViewHolder>() {

    private var list = mutableListOf<PhotosUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerAllPhotosBinding.inflate(inflater, parent, false)
        return AllPhotosViewHolder.Base(binding, clickListener)
    }

    override fun onBindViewHolder(holder: AllPhotosViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(data: List<PhotosUI>) {
        val result = DiffUtil.calculateDiff(ImageDiffUtilCallback(list, data))
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

}