package com.isaquecoelho.simplemovieapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isaquecoelho.simplemovieapp.model.MovieItem
import com.isaquecoelho.simplemovieapp.repository.local.dao.MovieDao
import com.isaquecoelho.simplemovieapp.util.ConstantUtils

@Database(entities = [MovieItem::class], version = ConstantUtils.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        internal fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java,
                    ConstantUtils.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}