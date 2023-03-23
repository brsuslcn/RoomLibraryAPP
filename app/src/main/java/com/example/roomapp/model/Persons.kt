package com.example.roomapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "persons")
data class Persons(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "person_id") @NotNull var person_id : Int,
                   @ColumnInfo(name = "person_name") @NotNull var person_name : String,
                   @ColumnInfo(name = "person_surname") @NotNull var person_surname : String,
                   @ColumnInfo(name = "person_age") @NotNull var person_yas : Int) {
}