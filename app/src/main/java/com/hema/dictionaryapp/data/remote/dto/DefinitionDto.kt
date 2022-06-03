package com.hema.dictionaryapp.data.remote.dto

import com.hema.dictionaryapp.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val synonyms: List<Any>
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            synonyms = synonyms
        )
    }
}