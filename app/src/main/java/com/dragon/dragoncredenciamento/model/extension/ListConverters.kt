package com.dragon.dragoncredenciamento.model.extension

import androidx.room.TypeConverter
import com.dragon.dragoncredenciamento.extension.deserializeList
import com.dragon.dragoncredenciamento.model.PrinterConfig
import com.google.gson.Gson
import java.util.*

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */

class ListConverters {

    private val gson = Gson()

    /* Config Option */

    @TypeConverter
    fun fromConfigOptionList(list: List<PrinterConfig.ConfigOption>?) : String? = gson.toJson(list)


    @TypeConverter
    fun toConfigOptionList(json: String?): List<PrinterConfig.ConfigOption>? = gson.deserializeList(json, Array<PrinterConfig.ConfigOption>::class.java)

    /* Date */

    @TypeConverter
    fun fromDateList(list: List<Date>?) : String = gson.toJson(list)

    @TypeConverter
    fun toDateList(json: String?): List<Date>? = gson.deserializeList(json, Array<Date>::class.java)

}