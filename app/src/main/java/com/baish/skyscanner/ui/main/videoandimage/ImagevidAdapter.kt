package com.baish.skyscanner.ui.main.videoandimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.nasaimage.Items
import com.baish.skyscanner.databinding.ImageitemLayoutBinding
import com.squareup.picasso.Picasso

class ImagevidAdapter: ListAdapter<Items,ImageViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.href  == newItem.href  &&
                        oldItem.links == newItem.links &&
                        oldItem.data == newItem.data
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }

        }
    }


}

class ImageViewHolder(private val binding : ImageitemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Items) {
        val image = item.links.first().href
        Picasso.get().load(image).placeholder(R.drawable.nasa_place).into(binding.imageShow)

        val itemTwo = item.data.first().title
        binding.titlefMovie.text = itemTwo

        val itemThree = item.data.first().location
        binding.location.text = itemThree

    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.imageitem_layout, parent, false)
            val binding = ImageitemLayoutBinding.bind(view)
            return ImageViewHolder(binding)
        }
    }

}