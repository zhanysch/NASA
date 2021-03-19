package com.baish.skyscanner.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.baish.skyscanner.data.PagingMediator
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.db.NasaDao
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.Photos
import com.baish.skyscanner.data.remote.NasaService
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Flow

interface NasaRepository{
    suspend fun loadImagesOfDayDB(count : Int, thumbs : Boolean)
    fun getImageOfTheDayDb(): LiveData<List<ImageOfTheDayModel>>
    @ExperimentalPagingApi
    fun getPagingResult(): LiveData<PagingData<Photos>>
    fun getFavouriteMars(): LiveData<List<Photos>>
   /* suspend fun getImageandVideoLibrary(title:String)*/


}

class NasaRepositoryImpl(private val network : NasaService, private val db : AppDataBase): NasaRepository {
    override suspend fun loadImagesOfDayDB(count: Int, thumbs: Boolean, ) {
        val result = network.getImage(count=count,thumbs = thumbs,apiKey = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")

        try {
            result.body()?.let { db.getContentDao().saveContent(it) }
        }catch (e: Exception){
            Log.d("Asdadasd", e.message?:"")
            e.printStackTrace()
        }
    }

    override fun getImageOfTheDayDb() = db.getContentDao().getContent()
    override fun getFavouriteMars(): LiveData<List<Photos>> =  db.getContentDao().getFavorite()

    @ExperimentalPagingApi
    override fun getPagingResult(): LiveData<PagingData<Photos>> {
        val pagingSourceFactory = {db.getContentDao().getAll()}
        return  Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator =  PagingMediator(network,db),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }



   /* override suspend fun getImageandVideoLibrary(title: String) {
        val resultt = network.getImagesandVideo()
    }*/


    companion object{
        const val  PAGE_SIZE = 20
    }
}