package com.hema.dictionaryapp.presentation

import com.hema.dictionaryapp.domain.model.WordInfo


// using this wrapper class in viewModel to provide some state for ui
data class WordInfoState(
    val wordInfoItem: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
