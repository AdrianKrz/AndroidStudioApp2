package com.example.prm4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prm4.data.model.ThingsEntity

@Database(
    entities = [ThingsEntity::class],
    version = 1
)
abstract class ThingsDatabase : RoomDatabase() {
    abstract val produkty: ThingsDao

    companion object {
        fun open(context: Context): ThingsDatabase = Room.databaseBuilder(
            context, ThingsDatabase::class.java, "kierowcy.db"
        ).build()
    }
}