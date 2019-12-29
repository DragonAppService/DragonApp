package com.dragon.dragoncredenciamento.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dragon.dragoncredenciamento.model.Question

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
@Dao
interface QuestionsDao {

    @Query("SELECT * FROM question")
    fun getQuestions(): MutableList<Question>?

    @Query("DELETE FROM question")
    fun deleteAll()

    @Insert
    fun insert(question: Question)

    @Update
    fun update(question: Question)

}