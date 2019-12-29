package com.dragon.dragoncredenciamento.flow.admin.configure

import android.app.Application
import androidx.lifecycle.*
import com.dragon.dragoncredenciamento.database.AppDatabase
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.database.PrinterConfigSession
import com.dragon.dragoncredenciamento.model.PrinterConfig
import com.dragon.dragoncredenciamento.model.PrinterConfigDatabase
import com.dragon.dragoncredenciamento.tools.PrintTools

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
class PrintConfigViewModel(var context: Application) : AndroidViewModel(context),
    LifecycleObserver {

    val printerConfigurationsLiveData: MutableLiveData<MutableList<PrinterConfig>> by lazy {
        MutableLiveData<MutableList<PrinterConfig>>()
    }

    val printTools: PrintTools by lazy {
        PrintTools()
    }

    private val database: AppDatabase.Database by lazy {
        DbHelper.getDatabase(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun retrieveDefaultConfigurations() {
        updateData()
    }

    fun updateConfig(config: PrinterConfig, newChosenConfig: PrinterConfig.ConfigOption) {
        config.chosenConfig = newChosenConfig

        PrinterConfigSession.updateConfig(config, database)

        updateData()
    }

    fun updateChosenDevice(chosenDevice: String) {
        PrinterConfigSession.updateDeviceName(chosenDevice, database)

        updateData()
    }

    fun searchPrinters() {
        val currentModelName = PrinterConfigSession.currentPrinterModel.chosenConfig.optionTitle

        val modelConfig = PrinterConfig.PrinterModel.fromString(currentModelName)

        printTools.fetchWifiPrinters(modelConfig)
    }

    private fun updateData() {
        val configurations = mutableListOf<PrinterConfig>()

        configurations.addAll(
            PrinterConfigSession.getCurrentConfig(database)
        )

        val configDatabase = PrinterConfigDatabase(
            "1",
            configurations[0],
            configurations[2],
            configurations[3],
            configurations[1]
        )

        database.printerConfigDatabaseDao().insert(configDatabase)

        printerConfigurationsLiveData.postValue(configurations)
    }

}