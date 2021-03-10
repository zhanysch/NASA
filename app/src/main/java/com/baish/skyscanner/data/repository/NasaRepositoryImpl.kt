package com.baish.skyscanner.data.repository

import androidx.lifecycle.LiveData
import com.baish.skyscanner.data.db.NasaDao
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.remote.NasaService
import retrofit2.Response

interface NasaRepository{
    suspend fun loadImagesOfDayDB(count : Int, thumbs : Boolean) : Response<List<ImageOfTheDayModel>>
    fun getImageOfTheDayDb(): LiveData<ImageOfTheDayModel>


}

class NasaRepositoryImpl(private val network : NasaService, private val db : NasaDao): NasaRepository {
    override suspend fun loadImagesOfDayDB(count: Int, thumbs: Boolean, ): Response<List<ImageOfTheDayModel>> {
        val result = network.getImage(count=count,thumbs = thumbs,apiKey = "EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb")
        result.body()?.let { db.saveContent(it) }
        return result
    }

    override fun getImageOfTheDayDb() = db.getContent()
}