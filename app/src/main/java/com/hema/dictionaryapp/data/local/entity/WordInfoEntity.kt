package com.hema.dictionaryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hema.dictionaryapp.data.local.Converters
import com.hema.dictionaryapp.domain.model.Meaning
import com.hema.dictionaryapp.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings=meanings,
            phonetic=phonetic,
            word=word
        )
    }
}
