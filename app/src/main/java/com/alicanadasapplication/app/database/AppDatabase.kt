package com.alicanadasapplication.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alicanadasapplication.app.database.dao.MuhendisDao
import com.alicanadasapplication.app.database.dao.ProjectDao
import com.alicanadasapplication.app.database.entities.Muhendisler
import com.alicanadasapplication.app.database.entities.Projects

@Database(entities = [Muhendisler::class, Projects::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun engineerDao(): MuhendisDao
    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "demo"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
