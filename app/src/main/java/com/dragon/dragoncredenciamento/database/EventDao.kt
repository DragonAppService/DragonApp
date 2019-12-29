package com.dragon.dragoncredenciamento.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dragon.dragoncredenciamento.model.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun get(): Event?

    @Insert
    fun insertNewEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

}