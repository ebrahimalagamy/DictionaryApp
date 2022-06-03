package com.hema.dictionaryapp.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)
