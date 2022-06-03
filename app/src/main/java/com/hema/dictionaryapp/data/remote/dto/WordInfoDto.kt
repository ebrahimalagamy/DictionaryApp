package com.hema.dictionaryapp.data.remote.dto

import com.hema.dictionaryapp.data.local.entity.WordInfoEntity
import com.hema.dictionaryapp.domain.model.WordInfo

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings.map { it.toMeaning() },
            phonetic?:"empty",
            word
        )
    }
}