package com.stslex.splashgallery.ui.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener

class CollectionsAdapter(private val clickListener: CollectionClickListener) :
    RecyclerView.Adapter<CollectionsViewHolder>() {

    private var list = mutableListOf<CollectionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerCollectionsBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.setClickListener(clickListener)
    }

    override fun getItemCount(): Int = list.size

    fun addItems(list: List<CollectionModel>) {
        val startItems = this.list.size
        this.list.addAll(list)
        notifyItemRangeChanged(startItems, this.list.size)
    }

}