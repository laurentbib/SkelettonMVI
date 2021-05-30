package com.biblublab.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsDto::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
abstract fun newsDao() : NewsDao
}
