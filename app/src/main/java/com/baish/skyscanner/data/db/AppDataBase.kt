package com.baish.skyscanner.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baish.skyscanner.data.model.nasa.imageofday.ImageOfTheDayModel


@Database(entities = [ImageOfTheDayModel :: class],version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getContentDao(): NasaDao

    companion object{
        fun getInstanceDB(context: Context):AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java, "myDBeHOU").allowMainThreadQueries().build()
        }
    }
}