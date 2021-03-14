package com.baish.skyscanner.data.model.nasa.mars

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PageKeys (
    @PrimaryKey
    val id : Int,
    val prevKey : Int?,
    val nextKey : Int?

)