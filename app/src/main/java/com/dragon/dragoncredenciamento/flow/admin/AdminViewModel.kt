package com.dragon.dragoncredenciamento.flow.admin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.flow.base.BaseViewModel
import com.dragon.dragoncredenciamento.model.Background
import com.dragon.dragoncredenciamento.model.ExportDbPath
import com.dragon.dragoncredenciamento.model.ImportDbPath
import com.dragon.dragoncredenciamento.tools.FileTools

class AdminViewModel(var app: Application) : BaseViewModel(app),
    DbHelper.OnExportDbListener,
    DbHelper.OnImportDbListener {

    val exportPathLive: MutableLiveData<ExportDbPath> by lazy {
        MutableLiveData<ExportDbPath>()
    }

    val importPathLive: MutableLiveData<ImportDbPath> by lazy {
        MutableLiveData<ImportDbPath>()
    }

    override fun onDbImported() {
        importPathLive.postValue(ImportDbPath.IMPORTED)
    }

    override fun onUnsupportedFormat() {
        importPathLive.postValue(ImportDbPath.UNSUPPORTED_FORMAT)
    }

    override fun onImportError() {
        importPathLive.postValue(ImportDbPath.IMPORT_ERROR)
    }

    override fun onDbExported() {
        exportPathLive.postValue(ExportDbPath.EXPORTED)
    }

    override fun onEmptyParticipants() {
        exportPathLive.postValue(ExportDbPath.EMPTY_PARTICIPANTS)
    }

    override fun onDbExportError() {
        exportPathLive.postValue(ExportDbPath.EXPORT_ERROR)
    }

    fun exportCsv() {
        DbHelper.exportDb(context, this)
    }

    fun importCsv(filePath: String) {
        DbHelper.importDb(context, filePath, this)
    }

    fun changeBackground(newBgPath: String) {
        val imagePath = FileTools().createImageFile(newBgPath)
        val background = Background(path = imagePath)
        DbHelper.getDatabase(context).backgroundDao().insert(background)
        onBackgroundChanged(background)
    }

}