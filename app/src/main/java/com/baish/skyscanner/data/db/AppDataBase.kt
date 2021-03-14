package com.baish.skyscanner.data.db

import android.content.Context
import androidx.room.*
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel
import com.baish.skyscanner.data.model.nasa.mars.*


@Database(entities = [ImageOfTheDayModel :: class, MarsBaseModel::class,Photos::class,PageKeys::class,Camera::class,
                     Rover::class],version = 3,exportSchema = false)
@TypeConverters(value = [TypeConvertor::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun getContentDao(): NasaDao
    abstract fun getPagigngKeysDao(): PageKeysDao

    companion object{
        fun getInstanceDB(context: Context):AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java, "myDBeHOU").allowMainThreadQueries().build()
        }

    }
}