package com.hema.dictionaryapp.domain.model

import com.hema.dictionaryapp.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
