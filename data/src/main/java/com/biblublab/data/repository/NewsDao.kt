package com.biblublab.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface NewsDao {

    @Query("SELECT * from news_table")
     suspend fun getAllNews(): List<NewsDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsDto: NewsDto)

    @Query("DELETE FROM news_table")
    suspend fun deleteAll()
}