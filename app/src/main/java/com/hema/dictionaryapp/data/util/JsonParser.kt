package com.hema.dictionaryapp.data.util

import java.lang.reflect.Type


/**
 use this interface when we decide to parse Json
  with any library and switch with any library
 **/
interface JsonParser {
    fun <T> formJson(json: String, type: Type): T?

    fun <T> toJson(obj: T, type: Type): String?


}