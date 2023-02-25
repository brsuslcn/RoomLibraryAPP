package com.example.roomapp

import androidx.room.*

@Dao
interface PersonsDao {
    @Query("SELECT *FROM persons")
    suspend fun allPersons() : List<Persons>

    @Insert
    suspend fun addPerson(person : Persons)

    @Query("SELECT *FROM persons WHERE person_id=:person_id")
    suspend fun getPersonInfo(person_id : Int) : Persons

    @Delete
    suspend fun deletePerson(person:Persons)

    @Update
    suspend fun updatePerson(person:Persons)


}