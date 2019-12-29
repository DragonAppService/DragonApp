package com.dragon.dragoncredenciamento.model.extension

import androidx.room.TypeConverter
import com.dragon.dragoncredenciamento.model.PrinterConfig

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */
class PrinterConfigConverter {

    @TypeConverter
    fun toConfigType(type: String): PrinterConfig.ConfigType? {
        return when (type) {
            PrinterConfig.ConfigType.DEVICE.name -> PrinterConfig.ConfigType.DEVICE
            PrinterConfig.ConfigType.PRINTER_MODEL.name -> PrinterConfig.ConfigType.PRINTER_MODEL
            PrinterConfig.ConfigType.LABEL.name -> PrinterConfig.ConfigType.LABEL
            PrinterConfig.ConfigType.ACCESS_TYPE.name -> PrinterConfig.ConfigType.ACCESS_TYPE
            else -> null
        }
    }

    @TypeConverter
    fun toString(type: PrinterConfig.ConfigType): String {
        return type.name
    }

}