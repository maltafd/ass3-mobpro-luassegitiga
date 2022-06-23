package org.d3if2134.luas_segitiga.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SegitigaEntity::class], version = 1, exportSchema = false)
abstract class SegitigaDb : RoomDatabase() {
    abstract val dao: SegitigaDao
    companion object {
        @Volatile
        private var INSTANCE: SegitigaDb? = null
        fun getInstance(context: Context): SegitigaDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SegitigaDb::class.java,
                        "segitiga.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}