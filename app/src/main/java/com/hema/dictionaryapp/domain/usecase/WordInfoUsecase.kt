package com.hema.dictionaryapp.domain.usecase

import com.hema.dictionaryapp.domain.model.WordInfo
import com.hema.dictionaryapp.domain.repository.IWordInfoRepo
import com.hema.dictionaryapp.utlis.ObjectState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoUsecase(
    private val iWordInfoRepo: IWordInfoRepo
) {

    operator fun invoke(word: String): Flow<ObjectState<List<WordInfo>>> {
        // if no word don't make request
        if (word.isBlank()) {
            return flow { }
        }

        return iWordInfoRepo.getWordInfo(word)
    }
}