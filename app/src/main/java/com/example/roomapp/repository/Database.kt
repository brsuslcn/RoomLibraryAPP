package com.example.roomapp.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.Persons
import com.example.roomapp.data.PersonsDao


@androidx.room.Database(entities = [Persons::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getPersonsDao(): PersonsDao

    companion object {
        var INSTANCE: Database? = null

        fun databaseAccess(context: Context): Database? {
            if (INSTANCE == null) {
                synchronized(Database::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "pregisters.sqlite"
                    ).createFromAsset("pregisters.sqlite").build()
                }

            }
            return INSTANCE
        }
    }

}