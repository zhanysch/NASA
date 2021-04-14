package com.baish.skyscanner.ui.main.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.db.ObjectWraper
import com.baish.skyscanner.data.di.dbModule
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.repository.NasaRepository
import com.baish.skyscanner.data.repository.NasaRepositoryImpl

class MarsViewModel(private val repository : NasaRepository, private val db : AppDataBase):ViewModel() {

    @ExperimentalPagingApi
    fun getPagindMarsData(): LiveData<PagingData<Photos>>{
        return repository.getPagingResult()
    }



    fun update(item : Photos){
        if (item.isChecked){
            db.getContentDao().insertFavourite(ObjectWraper.photosToFavouritePhotos(item))
        } else{
            db.getContentDao().deleteALLFavourite()
        }

    }
}