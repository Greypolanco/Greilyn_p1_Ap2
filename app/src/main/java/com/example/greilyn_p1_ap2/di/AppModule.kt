package com.example.greilyn_p1_ap2.di

import android.content.Context

import androidx.room.Room
import com.example.greilyn_p1_ap2.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun appContext(@ApplicationContext appContextM : Context) : DataBase =
        Room.databaseBuilder(
            appContextM,
            DataBase::class.java,
            "DataBase.db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesDivisor(db : DataBase) = db.divisorDao()
}