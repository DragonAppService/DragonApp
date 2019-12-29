package com.dragon.dragoncredenciamento.database

import androidx.room.*
import com.dragon.dragoncredenciamento.model.ChartData

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */
@Dao
interface ChartDataDao {

    @Query("SELECT * FROM chartdata")
    fun getAll(): MutableList<ChartData>?

    @Query("DELETE FROM chartdata WHERE 0 == 0")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chartData: ChartData)

    @Delete
    fun delete(chartData: ChartData)

}