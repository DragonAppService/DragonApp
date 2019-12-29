package com.dragon.dragoncredenciamento.database

import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dragon.dragoncredenciamento.model.Participant
import com.dragon.dragoncredenciamento.model.Question
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.concurrent.Executors

object DbHelper {

    private const val DATABASE_NAME = "dragon_db"
    private const val CHILD_CSV_DIRECTORY_NAME = "dragonCredential/"
    private const val CSV_FILE_NAME = "dragonDatabase.csv"

    private var dbInstance: AppDatabase.Database? = null

    interface OnExportDbListener {

        fun onDbExported()

        fun onEmptyParticipants()

        fun onDbExportError()

    }

    interface OnImportDbListener {

        fun onDbImported()

        fun onUnsupportedFormat()

        fun onImportError()

    }

    fun getDatabase(context: Context): AppDatabase.Database {
        return dbInstance ?: Room.databaseBuilder(
            context, AppDatabase.Database::class.java, DATABASE_NAME
        ).allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    initializeDatabase(context)
                }
            })
            .build()
    }

    fun exportDb(context: Context, listener: OnExportDbListener) {
        val exportDir = File(Environment.getExternalStorageDirectory(), CHILD_CSV_DIRECTORY_NAME)

        if (!exportDir.exists()) {
            exportDir.mkdirs()
        }

        val csvFile = File(exportDir, CSV_FILE_NAME)

        try {
            csvFile.createNewFile()
            val writer = CSVWriter(FileWriter(csvFile))

            val readableDb = getDatabase(context).openHelper.readableDatabase
            val cursor: Cursor = readableDb.query("SELECT * FROM participant")

            writer.writeNext(cursor.columnNames)
            Log.i("ExportDb", "Writing columns = ${cursor.columnNames}")

            if (cursor.count == 0) {
                Log.e("ExportDb", "Cursor.count is empty or 0")
                listener.onEmptyParticipants()
                return
            }

            while (cursor.moveToNext()) {
                val arrStr = arrayOf<String>(cursor.getString(0), cursor.getString(1))
                writer.writeNext(arrStr)
                Log.i("ExportDb", "Writing = $arrStr")
            }

            Log.i("ExportDb", "Export complete")
            writer.close()
            cursor.close()
            listener.onDbExported()
        } catch (e: Exception) {
            listener.onDbExportError()
            Log.e("DbExport", e.message)
        }

    }

    fun importDb(context: Context, filePath: String, listener: OnImportDbListener) {
        val database = getDatabase(context)

        try {
            val reader = CSVReader(FileReader(filePath))

            database.participantDao().deleteAll()

            reader.readAll().forEach {
                if (!it[0].contains("cpf", true)) {
                    database.participantDao().insert(
                        Participant(it[0], it[1])
                    )
                    Log.i("DbImport", "Participant inserted")
                }
            }

            Log.i("DbImport", "Import complete")
            reader.close()
            listener.onDbImported()
        } catch (e: Exception) {
            listener.onImportError()
            Log.e("DbImport", e.message)
        }

    }

    private fun initializeDatabase(context: Context) {
        Log.i("Initializing Database", "Setting custom form data")
        val questions = mutableListOf<Question>()

        for (i in 1..30) {
            questions.add(Question(i.toLong(), true))
        }

        Executors.newSingleThreadExecutor().execute {
            questions.forEach {
                getDatabase(context).questionsDao().insert(it)
            }
        }
    }

}