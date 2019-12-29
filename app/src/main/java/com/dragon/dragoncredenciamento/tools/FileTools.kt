package com.dragon.dragoncredenciamento.tools

import android.os.Environment
import android.util.Log
import java.io.File

/**
 * @Author Guilherme
 * @Date 12/06/2019
 */
class FileTools {

    companion object {
        private const val DEFAULT_BACKGROUND_DIR = "dragonCredential/background/"
        private const val BG_FILE_NAME = "dragonCurrentBg.png"
    }

    /**
     * Creates a new image file based on the path of another.
     *
     * @param path the given image to create a file from.
     * @return a string with the created file path, or null if an error occurs.
     */
    fun createImageFile(path: String): String {
        val dir = File(Environment.getExternalStorageDirectory(), DEFAULT_BACKGROUND_DIR)
        val currentFile = File(path)

        if (!dir.exists()) {
            dir.mkdirs()
        }

        if (currentFile.isFile.not() && currentFile.exists().not()) {
            return "null"
        }

        val bgFile = File(dir, BG_FILE_NAME)

        return try {
            if (bgFile.exists().not()) {
                bgFile.createNewFile()
            }

            bgFile.writeBytes(currentFile.readBytes())

            bgFile.absolutePath
        } catch (e: Exception) {
            Log.e("Create Image File", e.message)
            "null"
        }
    }

}