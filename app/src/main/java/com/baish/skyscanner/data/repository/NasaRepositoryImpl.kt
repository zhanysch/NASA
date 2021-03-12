package com.baish.skyscanner.data.repository

import androidx.lifecycle.LiveData
import com.baish.skyscanner.data.db.AppDataBase
import com.baish.skyscanner.data.db.NasaDao
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.remote.NasaService
import retrofit2.Response

interface NasaRepository{
    suspend fun loadImagesOfDayDB(count : Int, thumbs : Boolean)
    fun getImageOfTheDayDb(): LiveData<ImageOfTheDayModel>


}

class NasaRepositoryImpl(private val network : NasaService, private val db : AppDataBase): NasaRepository {
    override suspend fun loadImagesOfDayDB(count: Int, thumbs: Boolean, ) {
        val result = network.getImage(count=count,thumbs = thumbs,apiKey = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")
        result.body()?.let { db.getContentDao().saveContent(it) }
    }

    override fun getImageOfTheDayDb() = db.getContentDao().getContent()
}