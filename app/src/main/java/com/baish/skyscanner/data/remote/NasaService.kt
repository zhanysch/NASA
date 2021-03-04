package com.baish.skyscanner.data.remote

import TechProjectModel
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaService {

    @GET("planetary/apod")
    suspend fun getImage(
        @Query("count") count : Int,
        @Query("thumbs") thumbs : Boolean,
        @Query("api_key")apiKey : String
    ) : Response<List<ImageOfTheDayModel>>

    // https://api.nasa.gov/planetary/apod?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb

    @GET("techport/api/projects/{id_parameter}")
    suspend fun getTechProject(
        @Path("id_parameter") id_parameter: Int,
        @Query("api_key") api_key : String
    ) : Response<TechProjectModel>

    //https://api.nasa.gov/techport/api/projects/17792?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb

}