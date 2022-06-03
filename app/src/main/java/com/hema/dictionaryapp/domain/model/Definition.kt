package com.hema.dictionaryapp.domain.model

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val synonyms: List<Any>
)
