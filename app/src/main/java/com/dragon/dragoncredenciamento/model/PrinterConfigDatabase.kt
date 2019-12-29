package com.dragon.dragoncredenciamento.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author Guilherme
 * @Date 17/08/2019
 */

@Entity
data class PrinterConfigDatabase (
    @PrimaryKey var id: String,
    @Embedded(prefix = "currentPrinterModel") var currentPrinterModel: PrinterConfig,
    @Embedded(prefix = "currentDevice") var currentDevice: PrinterConfig,
    @Embedded(prefix = "currentLabel")var currentLabel: PrinterConfig,
    @Embedded(prefix = "currentAccessType")var currentAccessType: PrinterConfig
)