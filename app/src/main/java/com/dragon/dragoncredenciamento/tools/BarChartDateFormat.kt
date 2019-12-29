package com.dragon.dragoncredenciamento.tools

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

/**
 * @Author Guilherme
 * @Date 18/08/2019
 */
class BarChartDateFormat(private val xValues: ArrayList<String>) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return SimpleDateFormat("dd/MM/yyyy").format(Date(xValues[value.roundToInt()].toLong()))
    }
}