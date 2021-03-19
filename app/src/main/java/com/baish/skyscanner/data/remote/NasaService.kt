package com.baish.skyscanner.data.remote

import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.MarsBaseModel
import com.baish.skyscanner.data.model.nasa.nasaimage.NasaImageandVideo
import com.baish.skyscanner.data.model.nasa.techproject.TechProjectModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface NasaService {


    @GET
   suspend fun getImagesandVideo(
        @Url url: String?,
    @Query("s")title: String
    ): Response<NasaImageandVideo>


    @GET("planetary/apod")
    suspend fun getImage(
        @Query("count") count: Int,
        @Query("thumbs") thumbs: Boolean,
        @Query("api_key") apiKey: String,
    ): Response<List<ImageOfTheDayModel>>

    // https://api.nasa.gov/planetary/apod?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb

    @GET("techport/api/projects/{id_parameter}")
    suspend fun getTechProject(
        @Path("id_parameter") id_parameter: Int,
        @Query("api_key") api_key: String,
    ): Response<TechProjectModel>

    //https://api.nasa.gov/techport/api/projects/17792?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMars(
        @Query("sol") sol: Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ) : MarsBaseModel

    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=1&api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb

}