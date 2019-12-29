package com.dragon.dragoncredenciamento.flow.login

import com.dragon.dragoncredenciamento.model.Participant

/**
 * @Author Guilherme
 * @Date 01/06/2019
 */
data class LoginDecorator (

    var currentCpf: String? = null,
    var submitPath: SubmitPath? = null,
    var participant: Participant? = null

) {

    fun isCpfInserted() : Boolean {
        return currentCpf?.isNotEmpty() == true
    }

    fun resetState() {
        this.currentCpf = null
        this.submitPath = null
        this.participant = null
    }

    enum class SubmitPath {

        LOGIN_FOUND,
        LOGIN_NOT_FOUND,

    }

}