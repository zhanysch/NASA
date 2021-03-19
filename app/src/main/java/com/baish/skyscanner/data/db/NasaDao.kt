package com.baish.skyscanner.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.Photos


@Dao
interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContent(user: List<ImageOfTheDayModel>)

    @Query("SELECT * FROM ImageOfTheDayModel")
    fun getContent(): LiveData<List<ImageOfTheDayModel>>

    @Insert
    fun insert(data: List<Photos>)

    @Query("SELECT * FROM Photos")
    fun getAll(): PagingSource<Int, Photos>

    @Update
    fun update(item : Photos)

    @Query("DELETE FROM Photos")
    suspend fun deleteALL()

    @Query("SELECT ALL * FROM  Photos WHERE isChecked")
    fun getFavorite(): LiveData<List<Photos>>

}