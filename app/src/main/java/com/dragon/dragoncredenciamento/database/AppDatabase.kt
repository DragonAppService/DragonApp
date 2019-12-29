package com.dragon.dragoncredenciamento.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dragon.dragoncredenciamento.model.*
import com.dragon.dragoncredenciamento.model.extension.ConfigOptionConverter
import com.dragon.dragoncredenciamento.model.extension.DateConverter
import com.dragon.dragoncredenciamento.model.extension.ListConverters
import com.dragon.dragoncredenciamento.model.extension.PrinterConfigConverter

object AppDatabase {

    @androidx.room.Database(
        entities = [Event::class, Participant::class,
            Background::class, Question::class, PrinterConfig::class,
            PrinterConfigDatabase::class, ChartData::class],
        version = 1
    )
    @TypeConverters(
        DateConverter::class,
        PrinterConfigConverter::class,
        ListConverters::class,
        ConfigOptionConverter::class
    )
    abstract class Database : RoomDatabase() {
        abstract fun eventDao(): EventDao
        abstract fun participantDao(): ParticipantDao
        abstract fun backgroundDao(): BackgroundDao
        abstract fun questionsDao(): QuestionsDao
        abstract fun printerConfigDatabaseDao(): PrinterConfigDatabaseDao
        abstract fun chartDataDao(): ChartDataDao
    }

}