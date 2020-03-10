package com.sample.newslistsample.database

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nitish.newsapp.model.Source

class Converters {

    @TypeConverter
    fun fromSource(value: String): Source? {
        val listType = object : TypeToken<Source>() {}.type
        return Gson().fromJson<Source>(value, listType)
    }

    @TypeConverter
    fun toSource(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}