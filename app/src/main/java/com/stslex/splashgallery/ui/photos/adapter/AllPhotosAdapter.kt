package com.stslex.splashgallery.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.utils.SetImageWithGlide

class AllPhotosAdapter(
    private val clickListener: ClickListener<PhotosUI>,
    private val setImageWithGlide: SetImageWithGlide,
    private val isUser: Boolean = false,
) : RecyclerView.Adapter<AllPhotosViewHolder>() {

    private var list = mutableListOf<PhotosUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerAllPhotosBinding.inflate(inflater, parent, false)
        return AllPhotosViewHolder.Base(binding, clickListener, setImageWithGlide, isUser)
    }

    override fun onBindViewHolder(holder: AllPhotosViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(data: List<PhotosUI>) {
        val startItems = list.size
        list.addAll(data)
        notifyItemRangeChanged(startItems, list.size)
    }

}