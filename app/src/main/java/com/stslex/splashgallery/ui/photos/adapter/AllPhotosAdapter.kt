package com.stslex.splashgallery.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.utils.ImageDiffUtilCallback
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener

class AllPhotosAdapter(
    private val clickListener: ImageClickListener,
    private val isUser: Boolean = false,
    private val setImage: SetImageWithGlide
) : RecyclerView.Adapter<AllPhotosViewHolder>() {

    private var list = mutableListOf<PhotosUI.Base>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerAllPhotosBinding.inflate(inflater, parent, false)
        return AllPhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllPhotosViewHolder, position: Int) {
        holder.bind(list[position], isUser, setImage)
        holder.setClickListener(clickListener)
    }

    override fun getItemCount(): Int = list.size

    fun addItems(data: List<PhotosUI.Base>) {
        val result = DiffUtil.calculateDiff(ImageDiffUtilCallback(list, data))
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

}