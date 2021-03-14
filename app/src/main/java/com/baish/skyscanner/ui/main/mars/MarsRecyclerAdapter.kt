package com.baish.skyscanner.ui.main.mars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baish.skyscanner.R
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.databinding.MarsRecycleritemBinding
import com.squareup.picasso.Picasso

class MarsRecyclerAdapter : PagingDataAdapter<Photos,MarsViewHolder>(photos_diff) {

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
       return MarsViewHolder.create(parent)
    }


    companion object{
        val photos_diff = object : DiffUtil.ItemCallback<Photos>(){
            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                return oldItem.img_src == newItem.img_src &&
                        oldItem.earth_date == newItem.earth_date &&
                        oldItem.camera == newItem.camera
            }

            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class MarsViewHolder(private val binding: MarsRecycleritemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Photos?) {
        binding.marsTitle.text = item?.sol.toString()
        Picasso.get().load(item?.img_src).placeholder(R.drawable.nasa_place).into(binding.marsitemImage)

    }
    companion object {
        fun create(parent: ViewGroup) : MarsViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.mars_recycleritem,parent,false)
            val binding = MarsRecycleritemBinding.bind(view)
            return  MarsViewHolder(binding)
        }
    }
}