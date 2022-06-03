package com.hema.dictionaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.hema.dictionaryapp.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class WordDatabase:RoomDatabase() {
    abstract val dao: WordInfoDao

}