package com.example.roomapp

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


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
                        "registers1.sqlite"
                    ).createFromAsset("registers1.sqlite").build()
                }

            }
            return INSTANCE
        }
    }

}