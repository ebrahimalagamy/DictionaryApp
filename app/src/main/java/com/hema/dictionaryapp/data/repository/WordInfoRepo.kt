package com.hema.dictionaryapp.data.repository

import com.hema.dictionaryapp.data.local.WordInfoDao
import com.hema.dictionaryapp.data.remote.DictionaryApi
import com.hema.dictionaryapp.domain.model.WordInfo
import com.hema.dictionaryapp.domain.repository.IWordInfoRepo
import com.hema.dictionaryapp.utlis.ObjectState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepo(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : IWordInfoRepo {
    // single source of truth
    override fun getWordInfo(word: String): Flow<ObjectState<List<WordInfo>>> = flow {
        // first emit loading because usually should happen when we request and start display progress bar
        emit(ObjectState.Loading())

        // next get the data from database if the data was cached
        // this will display even if still loading and waiting for the update from api
        val wordInfoFromDatabase = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(ObjectState.Loading(data = wordInfoFromDatabase))

        // get word info from api and delete the old one and insert the new one
        try {
            val wordInfoFromApi = api.getWordInFo(word)
            dao.deleteWordInfos(wordInfoFromApi.map { it.word })
            dao.insertWordInfos(wordInfoFromApi.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                ObjectState.Error(
                    message = "Oops, something went wrong !??",
                    data = wordInfoFromDatabase
                )
            )

        } catch (e: IOException) {
            emit(
                ObjectState.Error(
                    message = "Can't reach server, check yout internet connection",
                    data = wordInfoFromDatabase
                )
            )

        }

        // get new word info from api and cash it into database and emit it
        val newWordInfoFromDatabase = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(ObjectState.Success(newWordInfoFromDatabase))

    }
}