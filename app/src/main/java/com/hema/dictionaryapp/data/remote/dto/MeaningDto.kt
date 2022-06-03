package com.hema.dictionaryapp.data.remote.dto

import com.hema.dictionaryapp.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<Any>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )

    }
}