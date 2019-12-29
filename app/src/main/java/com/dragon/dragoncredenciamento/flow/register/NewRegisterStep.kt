package com.dragon.dragoncredenciamento.flow.register

import java.io.Serializable

/**
 * @Author Guilherme
 * @Date 27/06/2019
 */
data class NewRegisterStep(

    var currentStep: RegisterStep

) : Serializable {
    enum class RegisterStep {
        PERSONAL_INFO,
        ADDRESS,
        COMPANY,
        PRODUCTS
    }
}