package com.hema.dictionaryapp.domain.repository

import com.hema.dictionaryapp.domain.model.WordInfo
import com.hema.dictionaryapp.utlis.ObjectState
import kotlinx.coroutines.flow.Flow

interface IWordInfoRepo {


    // using flow to emit multiple values over a period of time
    fun getWordInfo(word: String): Flow<ObjectState<List<WordInfo>>>

}