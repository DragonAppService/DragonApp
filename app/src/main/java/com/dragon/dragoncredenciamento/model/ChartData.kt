package com.dragon.dragoncredenciamento.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dragon.dragoncredenciamento.model.extension.ListConverters
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */
@Entity
@TypeConverters(ListConverters::class)
data class ChartData(

    @PrimaryKey var id: Int,
    var registerDates: List<Date>

) : Serializable {

    companion object {
        const val NO_CHART_DATA_ID = -1
    }

    fun getRegisterByHour(): List<Pair<String, Int>> {
        var totalRegistersByHour = mutableListOf<Pair<String, Int>>()
        val availableRanges = arrayListOf(
            "00 às 02",
            "02 às 04",
            "04 às 06",
            "06 às 08",
            "08 às 10",
            "10 às 12",
            "12 às 14",
            "14 às 16",
            "16 às 18",
            "18 às 20",
            "20 às 22",
            "22 às 00"
        )

        val sortedList = registerDates.sortedDescending().reversed()
        val currentRanges = arrayListOf<String>()

        //RangesNumbers
        var firstRange = 0
        var secondRange = 0
        var thirdRange = 0
        var fourthRange = 0
        var fifthRange = 0
        var sixthRange = 0
        var seventhRange = 0
        var eighthRange = 0
        var ninthRange = 0
        var tenthRange = 0
        var eleventhRange = 0
        var twelfthRange = 0


        sortedList.forEach { date ->
            val currentDateRange = when (date.hours) {
                in 0..2 -> availableRanges[0].apply {
                    firstRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, firstRange))
                }
                in 3..4 -> availableRanges[1].apply {
                    secondRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, secondRange))
                }
                in 5..6 -> availableRanges[2].apply {
                    thirdRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, thirdRange))
                }
                in 7..8 -> availableRanges[3].apply {
                    fourthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, fourthRange))
                }
                in 9..10 -> availableRanges[4].apply {
                    fifthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, fifthRange))
                }
                in 11..12 -> availableRanges[5].apply {
                    sixthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, sixthRange))
                }
                in 13..14 -> availableRanges[6].apply {
                    seventhRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, seventhRange))
                }
                in 15..16 -> availableRanges[7].apply {
                    eighthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, eighthRange))
                }
                in 17..18 -> availableRanges[8].apply {
                    ninthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, ninthRange))
                }
                in 19..20 -> availableRanges[9].apply {
                    tenthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, tenthRange))
                }
                in 21..22 -> availableRanges[10].apply {
                    eleventhRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, eleventhRange))
                }
                in 23..24 -> availableRanges[11].apply {
                    twelfthRange++
                    removeDuplicates(totalRegistersByHour)
                    totalRegistersByHour.add(totalRegistersByHour.size, Pair(this, twelfthRange))
                }
                else -> "hora inválida"
            }

            currentRanges.add(currentDateRange)
        }

        totalRegistersByHour = totalRegistersByHour.distinct().toMutableList()

        return totalRegistersByHour.toList()
    }

    private fun String.removeDuplicates(totalRegistersByHour: MutableList<Pair<String, Int>>) {
        totalRegistersByHour.removeAll {
            it.first == this
        }
    }

    fun getRegistersByDay(): List<Pair<Date, Int>> {
        val totalRegistersByDay = mutableListOf<Pair<Date, Int>>()

        val sortedDateList = registerDates.sortedDescending().reversed()
            .toMutableList() // All dates are here (even the duplicated
        val simpleDateList = mutableListOf<Date>()


        //Formats all dates to a simple date so we can remove then
        sortedDateList.forEach {
            val simplifiedDate = Date(SimpleDateFormat("dd/MM/yyyy").format(it))

            simpleDateList.add(simplifiedDate)
        }

        val numbersOfDays = simpleDateList.distinct().size
        val distinctDates = simpleDateList.distinct()

        var numberOfRegisters = 0

        //Finds the number o registers per day and add to the list
        for (day in 1..numbersOfDays) {
            // Add the date to the pair
            var registersByDay = Pair(distinctDates[day - 1], 0)

            //Fetch all dates and if
            simpleDateList.forEach {
                if (it == distinctDates[day - 1]) {
                    numberOfRegisters++
                }

                if (simpleDateList.last() == it) {
                    registersByDay = Pair(registersByDay.first, numberOfRegisters)
                    totalRegistersByDay.add(registersByDay)
                    numberOfRegisters = 0
                }

            }
        }

        return totalRegistersByDay
    }

}