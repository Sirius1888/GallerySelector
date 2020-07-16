package com.example.gallerypicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(var list: MutableList<GalleryImage>, var selectedImages: MutableList<GalleryImage>, var listener: GalleryListener):
        RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        var image = list[position]
        holder.onBind(image)

        if (image.isSelected) {
            holder.itemView.selected.visibility = View.VISIBLE
        } else {
            holder.itemView.selected.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            image.isSelected = !image.isSelected
            listener.onItemClick(image)
            notifyDataSetChanged()
        }
    }

    class GalleryViewHolder(view: View, var context: Context) : RecyclerView.ViewHolder(view) {
        fun onBind(item: GalleryImage) {
            itemView.image.loadImage(context, item.imagePath)
        }
    }

    interface GalleryListener {
        fun onItemClick(image: GalleryImage)
    }

}