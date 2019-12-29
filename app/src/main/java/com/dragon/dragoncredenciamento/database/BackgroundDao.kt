package com.dragon.dragoncredenciamento.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dragon.dragoncredenciamento.model.Background

/**
 * @Author Guilherme
 * @Date 15/06/2019
 */

@Dao
interface BackgroundDao {

    @Query("SELECT * FROM background")
    fun getBackground(): Background?

    @Query("DELETE FROM background")
    fun deleteAll()

    @Insert
    fun insert(background: Background)

}