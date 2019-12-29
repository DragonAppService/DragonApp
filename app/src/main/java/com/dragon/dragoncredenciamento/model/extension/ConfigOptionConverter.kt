package com.dragon.dragoncredenciamento.model.extension

import androidx.room.TypeConverter
import com.dragon.dragoncredenciamento.model.PrinterConfig
import com.google.gson.Gson

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */

class ConfigOptionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromConfigOption(configOption: PrinterConfig.ConfigOption?): String? = gson.toJson(configOption)

    @TypeConverter
    fun toConfigOption(json: String?): PrinterConfig.ConfigOption? = gson.fromJson(json, PrinterConfig.ConfigOption::class.java)

}