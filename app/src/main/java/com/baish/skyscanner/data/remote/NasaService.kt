package com.baish.skyscanner.data.remote

import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.MarsBaseModel
import com.baish.skyscanner.data.model.nasa.nasaimage.NasaImageandVideo
import com.baish.skyscanner.data.model.nasa.tech.TechModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface NasaService {


    @GET
   suspend fun getImagesandVideo(
        @Url url: String?,
    @Query("q")title: String
    ): Response<NasaImageandVideo>


    @GET("planetary/apod")
    suspend fun getImage(
        @Query("count") count: Int,
        @Query("thumbs") thumbs: Boolean,
        @Query("api_key") apiKey: String,
    ): Response<List<ImageOfTheDayModel>>



    @GET("techport/api/projects/{id_parameter}")
    suspend fun getTechProject(
        @Path("id_parameter") id_parameter: Int,
        @Query("api_key") api_key: String,
    ): Response<TechModel>



    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMars(
        @Query("sol") sol: Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ) : MarsBaseModel



}