package com.dragon.dragoncredenciamento.tools

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brother.ptouch.sdk.Printer
import com.brother.ptouch.sdk.PrinterInfo
import com.brother.ptouch.sdk.PrinterStatus
import com.dragon.dragoncredenciamento.database.PrinterConfigSession
import com.dragon.dragoncredenciamento.model.Participant
import com.dragon.dragoncredenciamento.model.PrinterConfig
import java.io.File

/**
 * @Author Guilherme
 * @Date 02/06/2019
 */
class PrintTools {

    companion object {
        private const val DEFAULT_PRINT_FILE_PATH = "dragonCredential/print/"
        private const val DEFAULT_PRINT_FULL_FILE_PATH = "/template.blf"
        private const val OBJECT_NAME_NAME= "Nome"
        private const val OBJECT_NAME_CITY= "Cidade"
        private const val OBJECT_NAME_COMPANY= "Empresa"
        private const val OBJECT_NAME_CPF= "CPF"
    }

    private val printer: Printer by lazy {
        Printer()
    }

    val printersIps: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val printStatusLive: MutableLiveData<PrintStatus> by lazy {
        MutableLiveData<PrintStatus>()
    }


    enum class PrintStatus {
        PRINTING,
        ERROR,
        PRINTED
    }

    fun fetchWifiPrinters(printerModel: PrinterConfig.PrinterModel) {
        Thread {
            val ips = mutableListOf<String>()
            val netPrinter = printer.getNetPrinters(printerModel.toPrinterModelCorrectName())
            netPrinter.forEach {
                Log.d(
                    "printerTag",
                    String.format("Model: %s, IP Address: %s", it.modelName, it.ipAddress)
                )
                ips.add(it.ipAddress)
            }
            printersIps.postValue(ips)
        }.start()

    }

    fun tryPrint(participant: Participant) {
        printStatusLive.postValue(PrintStatus.PRINTING)
        val dir = File(Environment.getExternalStorageDirectory(), DEFAULT_PRINT_FILE_PATH)

        if (!dir.exists()) {
            dir.mkdirs()
        }

        val filePath = dir.path + DEFAULT_PRINT_FULL_FILE_PATH

        if (File(filePath).exists().not()) {
            printStatusLive.postValue(PrintStatus.ERROR)
            return
        }

        val printer = configurePrinter()

        transferPrintFile(printer, filePath, participant)
    }

    private fun transferPrintFile(printer: Printer, filePath: String, participant: Participant) {
        Thread(Runnable {
            if (printer.startCommunication()) {
                val result = printer.transfer(filePath)
                if (result.errorCode != PrinterInfo.ErrorCode.ERROR_NONE) {
                    printStatusLive.postValue(PrintStatus.ERROR)
                    Log.d("Print tools", "ERROR - " + result.errorCode)
                } else {
                    printTransferredFile(printer, result, participant)
                }
                printer.endCommunication()
            } else {
                printStatusLive.postValue(PrintStatus.ERROR)
            }
        }).start()
    }

    private fun printTransferredFile(
        printer: Printer,
        result: PrinterStatus,
        participant: Participant
    ) {
        if (printer.startPTTPrint(1, null)) {
            printer.replaceTextName(participant.name, OBJECT_NAME_NAME)
            printer.replaceTextName(participant.city ?: " ", OBJECT_NAME_CITY)
            printer.replaceTextName(participant.companyName ?: " ", OBJECT_NAME_COMPANY)
            printer.replaceTextName(participant.cpf, OBJECT_NAME_CPF)
            val otherResult = printer.flushPTTPrint()
            if (otherResult.errorCode != PrinterInfo.ErrorCode.ERROR_NONE) {
                printStatusLive.postValue(PrintStatus.ERROR)
                Log.d("Print tools", "ERROR - " + result.errorCode)
            } else {
                printStatusLive.postValue(PrintStatus.PRINTED)
            }
        }
    }

    private fun configurePrinter(): Printer {
        val printer = Printer()
        val printerSettings = printer.printerInfo
        printerSettings.printerModel =
            PrinterConfig.PrinterModel.fromString(PrinterConfigSession.currentPrinterModel.chosenConfig.optionTitle)
                .toPrinterModel()
        printerSettings.ipAddress = PrinterConfigSession.currentDevice.chosenConfig.optionTitle
        printerSettings.labelNameIndex =
            PrinterConfig.Label.fromString(PrinterConfigSession.currentLabel.chosenConfig.optionTitle)
                .toLabelModel().ordinal
        printerSettings.port =
            PrinterConfig.AccessType.fromString(PrinterConfigSession.currentAccessType.chosenConfig.optionTitle)
                .toPortModel()
        printer.printerInfo = printerSettings
        return printer
    }

}