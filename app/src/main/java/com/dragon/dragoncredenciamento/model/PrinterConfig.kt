package com.dragon.dragoncredenciamento.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.brother.ptouch.sdk.LabelInfo
import com.brother.ptouch.sdk.PrinterInfo
import com.dragon.dragoncredenciamento.model.extension.ConfigOptionConverter
import com.dragon.dragoncredenciamento.model.extension.ListConverters
import com.dragon.dragoncredenciamento.model.extension.PrinterConfigConverter
import java.io.Serializable

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
@Entity
@TypeConverters(PrinterConfigConverter::class, ListConverters::class, ConfigOptionConverter::class)
data class PrinterConfig(

    @PrimaryKey var id: Int,
    var configTitle: String,
    var configOptions: List<ConfigOption>,
    var chosenConfig: ConfigOption,
    var configType: ConfigType,
    var isEnabled: Boolean = true

) : Serializable {

    companion object {

        fun getDefaultPrinterModel(): PrinterConfig {
            return PrinterConfig(
                1,
                "Modelo da impressora",
                listOf(
                    ConfigOption(PrinterModel.P_810_W.description),
                    ConfigOption(PrinterModel.P_QL800.description)
                ),
                ConfigOption(PrinterModel.P_810_W.description),
                ConfigType.PRINTER_MODEL
            )
        }

        fun getDefaultCurrentAccessTypeModel(): PrinterConfig {
            return PrinterConfig(
                2,
                "Acesso",
                listOf(
                    ConfigOption(AccessType.WIFI.description),
                    ConfigOption(AccessType.USB.description)
                ),
                ConfigOption(AccessType.WIFI.description),
                ConfigType.ACCESS_TYPE
            )
        }

        fun getDefaultDevice(): PrinterConfig {
            return PrinterConfig(
                3,
                "Dispositivo",
                listOf(),
                ConfigOption(""),
                ConfigType.DEVICE
            )
        }

        fun getDefaultLabel(): PrinterConfig {
            return PrinterConfig(
                4,
                "Etiqueta",
                listOf(
                    ConfigOption(Label.W29H90.description),
                    ConfigOption(Label.W62H100.description)
                ),
                ConfigOption(Label.W29H90.description),
                ConfigType.LABEL
            )
        }
    }

    class ConfigOption(var optionTitle: String) : Serializable

    enum class ConfigType {
        PRINTER_MODEL,
        ACCESS_TYPE,
        DEVICE,
        LABEL
    }

    enum class PrinterModel(var description: String) {
        P_810_W("810w"),
        P_QL800("QL800");

        companion object {
            fun fromString(name: String): PrinterModel {
                return if (name == "810w") {
                    P_810_W
                } else {
                    P_QL800
                }
            }
        }

        fun toPrinterModelCorrectName(): String {
            return if (this == P_810_W) {
                "QL-810W"
            } else {
                "QL-800"
            }
        }

        fun toPrinterModel(): PrinterInfo.Model {
            return if (this == P_810_W) {
                PrinterInfo.Model.QL_810W
            } else {
                PrinterInfo.Model.QL_800
            }
        }

    }

    enum class AccessType(var description: String) {
        WIFI("Wi-Fi"),
        USB("USB");

        companion object {
            fun fromString(name: String): AccessType {
                return if (name == "Wi-Fi") {
                    WIFI
                } else {
                    USB
                }
            }
        }

        fun toPortModel(): PrinterInfo.Port {
            return if (this == WIFI) {
                PrinterInfo.Port.NET
            } else {
                PrinterInfo.Port.USB
            }
        }

    }

    enum class Label(var description: String) {
        W29H90("W29H90"), //29x90mm
        W62H100("W62H100"); //62x100mm

        companion object {
            fun fromString(name: String): Label {
                return if (name == "W29H90") {
                    W29H90
                } else {
                    W62H100
                }

            }
        }

        fun toLabelModel(): LabelInfo.QL700 {
            return if (this == W29H90) {
                LabelInfo.QL700.W29H90
            } else {
                LabelInfo.QL700.W62H100
            }
        }
    }

}