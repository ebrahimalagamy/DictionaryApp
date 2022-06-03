package com.hema.dictionaryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hema.dictionaryapp.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    @Query("Delete from WordInfoEntity where word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    // search in database and return every word like my word
    @Query("Select * from wordinfoentity where word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>
}