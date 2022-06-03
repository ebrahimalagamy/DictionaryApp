package com.hema.dictionaryapp.data.remote

import com.hema.dictionaryapp.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInFo(@Path("word") word: String): List<WordInfoDto>
}