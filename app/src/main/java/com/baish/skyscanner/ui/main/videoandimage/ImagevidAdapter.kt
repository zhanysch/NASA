package com.baish.skyscanner.ui.main.videoandimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.nasaimage.Items
import com.baish.skyscanner.databinding.ImageitemLayoutBinding
import com.baish.skyscanner.utils.setCornerRadius
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ImagevidAdapter(private val listener: (item: Items, image: ShapeableImageView) -> Unit) :
    ListAdapter<Items, ImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.href == newItem.href &&
                        oldItem.links == newItem.links &&
                        oldItem.data == newItem.data
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }

        }
    }


}

class ImageViewHolder(
    private val binding: ImageitemLayoutBinding,
    private val listener: (item: Items, image: ShapeableImageView) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Items?, position: Int) {

        binding.imageShow.transitionName =
            itemView.context.resources.getString(R.string.image_transition, position)

        item?.id = position

        val radius = itemView.context.resources.getDimension(R.dimen.imageCutted)
        binding.imageShow.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomLeft = radius,
            bottomRight = radius
        )
        itemView.setOnClickListener {
            item.let { it1 -> it1?.let { it2 -> listener.invoke(it2, binding.imageShow) } }
        }
        val image = item?.links?.first()?.href

        if (item != null){
            Picasso.get().load(image).placeholder(R.drawable.nasa_place).into(binding.imageShow)
        }


        val itemTwo = item?.data?.first()?.title
        if (itemTwo !=null){
            binding.titlefMovie.text = itemTwo
        }


        val itemThree = item?.data?.first()?.location
        if (itemThree !=null){
            binding.location.text = itemThree
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            listener: (item: Items, image: ShapeableImageView) -> Unit,
        ): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.imageitem_layout, parent, false)
            val binding = ImageitemLayoutBinding.bind(view)
            return ImageViewHolder(binding, listener)
        }
    }

}