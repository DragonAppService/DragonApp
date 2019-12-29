package com.dragon.dragoncredenciamento.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dragon.dragoncredenciamento.model.Participant

@Dao
interface ParticipantDao {

    @Query("SELECT * FROM participant")
    fun getAll(): MutableList<Participant>?

    @Query("DELETE FROM participant WHERE 0 == 0")
    fun deleteAll()

    @Insert
    fun insert(participant: Participant)

    @Delete
    fun delete(participant: Participant)

}