package com.dragon.dragoncredenciamento.database

import com.dragon.dragoncredenciamento.model.PrinterConfig

/**
 * @Author Guilherme
 * @Date 21/06/2019
 */
object PrinterConfigSession {

    var currentPrinterModel: PrinterConfig = PrinterConfig.getDefaultPrinterModel()

    var currentDevice: PrinterConfig = PrinterConfig.getDefaultDevice()

    var currentLabel: PrinterConfig = PrinterConfig.getDefaultLabel()

    var currentAccessType: PrinterConfig = PrinterConfig.getDefaultCurrentAccessTypeModel()

    private var isDefaultConfig: Boolean = true

    fun init(database: AppDatabase.Database) {
        getCurrentConfig(database)
    }

    fun getCurrentConfig(database: AppDatabase.Database): List<PrinterConfig> {
        val configs = database.printerConfigDatabaseDao().getAll()

        if (configs?.isNotEmpty() == true) {
            isDefaultConfig = false
            currentPrinterModel = configs[0].currentPrinterModel
            currentAccessType = configs[0].currentAccessType
            currentDevice = configs[0].currentDevice
            currentLabel = configs[0].currentLabel
            return listOf(
                currentPrinterModel,
                currentAccessType,
                currentDevice,
                currentLabel
            )
        }

        return if (isDefaultConfig) {
            listOf(
                PrinterConfig.getDefaultPrinterModel(),
                PrinterConfig.getDefaultCurrentAccessTypeModel(),
                PrinterConfig.getDefaultDevice(),
                PrinterConfig.getDefaultLabel()
            )
        } else {
            listOf(
                currentPrinterModel,
                currentAccessType,
                currentDevice,
                currentLabel
            )
        }
    }

    fun updateDeviceName(newDeviceName: String, database: AppDatabase.Database) {
        val databaseConfig = database.printerConfigDatabaseDao().getAll()

        this.currentDevice.chosenConfig.optionTitle = newDeviceName
        databaseConfig?.get(0)?.currentDevice = currentDevice

        databaseConfig?.get(0)?.let {
            database.printerConfigDatabaseDao().insert(it)
        }

        isDefaultConfig = false
    }

    fun updateConfig(newConfig: PrinterConfig, database: AppDatabase.Database) {
        isDefaultConfig = false

        val databaseConfig = database.printerConfigDatabaseDao().getAll()

        when (newConfig.configType) {
            PrinterConfig.ConfigType.PRINTER_MODEL -> {
                currentPrinterModel = newConfig

                if (newConfig.chosenConfig.optionTitle == "QL800") {
                    currentAccessType = PrinterConfig.getDefaultCurrentAccessTypeModel().apply {
                        this.chosenConfig = PrinterConfig.ConfigOption("USB")
                        this.isEnabled = false
                    }

                    currentDevice = PrinterConfig.getDefaultDevice().apply {
                        this.isEnabled = false
                    }
                    databaseConfig?.get(0)?.currentPrinterModel = currentPrinterModel
                    databaseConfig?.get(0)?.currentAccessType = currentAccessType
                    databaseConfig?.get(0)?.currentDevice = currentDevice

                    databaseConfig?.get(0)?.let {
                        database.printerConfigDatabaseDao().insert(it)
                    }
                    return
                }

                currentAccessType = PrinterConfig.getDefaultCurrentAccessTypeModel()
                currentDevice = PrinterConfig.getDefaultDevice()

                databaseConfig?.get(0)?.currentPrinterModel = currentPrinterModel
                databaseConfig?.get(0)?.currentAccessType = currentAccessType
                databaseConfig?.get(0)?.currentDevice = currentDevice
            }
            PrinterConfig.ConfigType.ACCESS_TYPE -> {
                currentAccessType = newConfig
                databaseConfig?.get(0)?.currentAccessType = currentAccessType
            }
            PrinterConfig.ConfigType.DEVICE -> {
                currentDevice = newConfig
                databaseConfig?.get(0)?.currentDevice = currentDevice
            }
            PrinterConfig.ConfigType.LABEL -> {
                currentLabel = newConfig
                databaseConfig?.get(0)?.currentLabel = currentLabel
            }
        }

        databaseConfig?.get(0)?.let {
            database.printerConfigDatabaseDao().insert(it)
        }

    }


}