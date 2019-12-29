package com.dragon.dragoncredenciamento.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @Author Guilherme
 * @Date 18/08/2019
 */
class ChartDataTest {

    private lateinit var dates: List<Date>

    @Before
    fun init() {
        dates = listOf(
            Date(655527600000), //10/10/1990
            Date(655563660000), //10/10/1990 10:01
            Date(655563720000), //10/10/1990 10:02
            Date(655563780000), //10/10/1990 10:03
            Date(971179380000), //10/10/2000 10:03
            Date(1286802180000), //10/11/2010 10:03
            Date(1286888580000), //10/12/2010 10:03
            Date(1286802180000), //10/11/2010 10:03
            Date(1570712580000), //10/10/2019 10:03
            Date(1192021380000), //10/10/2007 10:03
            Date(340030980000), //10/10/1980 10:03
            Date(24411780000), //10/10/1970 10:03
            Date(497797380000)  //10/10/1985 10:03
        )
    }

    @Test
    fun getRegisterByDay_aLotOfDates_returnsCorrectDates() {
        val chartData = ChartData(1, dates)

        val sortedData = chartData.getRegistersByDay()
        Assert.assertNotNull(sortedData)
        Assert.assertNotEquals(dates, sortedData)
    }

}