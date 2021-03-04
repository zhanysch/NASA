package com.baish.skyscanner.data.remote

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun buildRetrofit(): NasaService{
      return Retrofit.Builder()
          .baseUrl("https://api.nasa.gov/")
          .addConverterFactory(GsonConverterFactory.create())
          .client(getClient())
          .build()
          .create(NasaService::class.java)

    // https://api.nasa.gov/planetary/apod?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb
    // https://api.nasa.gov/techport/api/projects/17792?api_key=EnKUTj7VDqVE0Cnu63SCYny69JzzTllJdhzVCmZb
    }


    private fun getClient(): OkHttpClient{
       return OkHttpClient.Builder()
           .connectTimeout(10,TimeUnit.SECONDS)
           .readTimeout(10,TimeUnit.SECONDS)
           .writeTimeout(10,TimeUnit.SECONDS)
           .addInterceptor(provideLoggingInterceptor())
           .build()
    }

   private fun provideLoggingInterceptor(): HttpLoggingInterceptor{
       val logger = HttpLoggingInterceptor()
       logger.level = HttpLoggingInterceptor.Level.BODY
       return logger
   }
}