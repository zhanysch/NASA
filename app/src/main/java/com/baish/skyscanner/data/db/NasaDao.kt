package com.baish.skyscanner.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel


@Dao
interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContent(user: List<ImageOfTheDayModel>)

    @Query("SELECT * FROM ImageOfTheDayModel")
   fun getContent(): LiveData<ImageOfTheDayModel>
}