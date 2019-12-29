package com.dragon.dragoncredenciamento.model.extension

import androidx.room.TypeConverter
import java.util.*

/**
 * @Author Guilherme
 * @Date 30/06/2019
 */
class DateConverter {

    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return if (value != null) Date(value) else null
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }

}