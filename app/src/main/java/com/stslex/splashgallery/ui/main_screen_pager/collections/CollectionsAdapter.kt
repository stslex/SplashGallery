package com.stslex.splashgallery.ui.main_screen_pager.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding

class CollectionsAdapter : RecyclerView.Adapter<CollectionsViewHolder>() {

    private var list = mutableListOf<CollectionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerCollectionsBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(list: List<CollectionModel>) {
        val startItems = this.list.size
        this.list.addAll(list)
        notifyItemRangeChanged(startItems, this.list.size)
    }
}