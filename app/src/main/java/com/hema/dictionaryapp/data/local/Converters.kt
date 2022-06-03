package com.hema.dictionaryapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.hema.dictionaryapp.data.util.JsonParser
import com.hema.dictionaryapp.domain.model.Meaning

/**
use ProvidedTypeConverter because we use instance of type converter in constructor
because by default type converter  can't have constructor arguments like we use
 **/
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    // to convert from meaning list to json string
    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.formJson<ArrayList<Meaning>>(
            json,
            //Can't specify type of meaningList
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList() // if return null
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}