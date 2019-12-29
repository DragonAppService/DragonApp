package com.dragon.dragoncredenciamento.database

import androidx.room.*
import com.dragon.dragoncredenciamento.model.PrinterConfigDatabase

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */
@Dao
interface PrinterConfigDatabaseDao {

    @Query("SELECT * FROM printerconfigdatabase")
    fun getAll(): MutableList<PrinterConfigDatabase>?

    @Query("DELETE FROM printerconfigdatabase WHERE 0 == 0")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(printerConfigDatabase: PrinterConfigDatabase)

    @Delete
    fun delete(printerConfigDatabase: PrinterConfigDatabase)

}