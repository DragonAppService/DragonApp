package com.dragon.dragoncredenciamento.extension

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import java.net.URISyntaxException


@Throws(URISyntaxException::class)
fun Context.getPath(uri: Uri): String? {
    var mUri = uri
    val needToCheckUri = Build.VERSION.SDK_INT >= 19
    var selection: String? = null
    var selectionArgs: Array<String>? = null
    // Uri is different in versions after KITKAT (Android 4.4), we need to
    // deal with different Uris.
    if (needToCheckUri && DocumentsContract.isDocumentUri(applicationContext, mUri)) {
        when {
            isExternalStorageDocument(mUri) -> {
                val docId = DocumentsContract.getDocumentId(mUri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                return Environment.getExternalStorageDirectory().absolutePath + "/" + split[1]
            }
            isDownloadsDocument(mUri) -> {
                val id = DocumentsContract.getDocumentId(mUri)
                mUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
            }
            isMediaDocument(mUri) -> {
                val docId = DocumentsContract.getDocumentId(mUri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                when (split[0]) {
                    "image" -> mUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> mUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> mUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(split[1])
            }
        }
    }
    if ("content".equals(mUri.scheme, ignoreCase = true)) {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor?
        try {
            cursor = contentResolver.query(mUri, projection, selection, selectionArgs, null)
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                cursor.close()
                return cursor.getString(columnIndex)
            }
        } catch (e: Exception) {
            Log.e("ContextExtensions", e.message)
        }

    } else if ("file".equals(mUri.scheme, ignoreCase = true)) {
        return mUri.path
    }
    return null
}


/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
fun isExternalStorageDocument(uri: Uri): Boolean {
    return "com.android.externalstorage.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
fun isDownloadsDocument(uri: Uri): Boolean {
    return "com.android.providers.downloads.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is MediaProvider.
 */
fun isMediaDocument(uri: Uri): Boolean {
    return "com.android.providers.media.documents" == uri.getAuthority()
}