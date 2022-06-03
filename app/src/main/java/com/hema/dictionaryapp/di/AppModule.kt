package com.hema.dictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.hema.dictionaryapp.data.local.Converters
import com.hema.dictionaryapp.data.local.WordDatabase
import com.hema.dictionaryapp.data.remote.DictionaryApi
import com.hema.dictionaryapp.data.repository.WordInfoRepo
import com.hema.dictionaryapp.data.util.GsonParser
import com.hema.dictionaryapp.domain.repository.IWordInfoRepo
import com.hema.dictionaryapp.domain.usecase.WordInfoUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWordInfoUsecase(repository: IWordInfoRepo): WordInfoUsecase {
        return WordInfoUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepo(
        db: WordDatabase,
        api: DictionaryApi
    ): IWordInfoRepo {
        return WordInfoRepo(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordDatabase {
        return Room.databaseBuilder(
            app, WordDatabase::class.java, "word.db"
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun provideApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }


}