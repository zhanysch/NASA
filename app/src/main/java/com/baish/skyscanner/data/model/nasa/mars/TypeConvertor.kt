package com.baish.skyscanner.data.model.nasa.mars

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConvertor {

   /* @JvmStatic
    @TypeConverter                                           ////////?????????????????????
    fun marsBasString(model: MarsBaseModel<Photos>): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun marsBaseObject(text: String): MarsBaseModel<Photos>? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, MarsBaseModel<Photos>::class.java)
    }
*/
    @JvmStatic
    @TypeConverter
    fun photosToString(model: List<Photos>): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun photosToObject(text: String?): List<Photos>? {
        if (text == null) return mutableListOf()
        val typeToken = object : TypeToken<List<Photos>>() {}.type
        return Gson().fromJson(text, typeToken)
    }

    @JvmStatic
    @TypeConverter
    fun cameratoString(model: Camera): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun cameraObject(text: String): Camera? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, Camera::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun rovertoString(model: Rover): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun rovertoObject(text: String): Rover? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, Rover::class.java)
    }
}