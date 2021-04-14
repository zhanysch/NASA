package com.baish.skyscanner.data.db

import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.model.nasa.mars.PhotosFavourite

object ObjectWraper {

    fun photosToFavouritePhotos(item: Photos): PhotosFavourite {
        return PhotosFavourite(id = item.id,
        sol = item.sol,
            camera=item.camera,
        img_src = item.img_src,
        earth_date = item.earth_date,
        rover = item.rover,
        isChecked = item.isChecked)
    }

    fun favouriteToPhotos(item : PhotosFavourite): Photos {
        return Photos(
            id = item.id,
            sol = item.sol,
            camera=item.camera,
            img_src = item.img_src,
            earth_date = item.earth_date,
            rover = item.rover,
            isChecked = item.isChecked)
    }
}