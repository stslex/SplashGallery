package com.stslex.splashgallery.ui.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.collections.CollectionUI
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.utils.SetImageWithGlide

class CollectionsAdapter(
    private val clickListener: ClickListener<CollectionUI>,
    private val isUser: Boolean = false,
    private val setImage: SetImageWithGlide
) :
    RecyclerView.Adapter<CollectionsViewHolder>() {

    private var list = mutableListOf<CollectionUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerCollectionsBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder.Base(binding, clickListener, setImage, isUser)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(list: List<CollectionUI>) {
        val startItems = this.list.size
        this.list.addAll(list)
        notifyItemRangeChanged(startItems, this.list.size)
    }

}