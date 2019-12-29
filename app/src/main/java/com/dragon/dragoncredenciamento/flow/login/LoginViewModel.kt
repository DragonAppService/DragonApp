package com.dragon.dragoncredenciamento.flow.login

import android.app.Application
import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.database.PrinterConfigSession
import com.dragon.dragoncredenciamento.flow.base.BaseViewModel
import com.dragon.dragoncredenciamento.model.Participant
import com.dragon.dragoncredenciamento.tools.PrintTools

/**
 * @Author Guilherme
 * @Date 01/06/2019
 */
class LoginViewModel(var app: Application) : BaseViewModel(app), LifecycleObserver {

    val decoratorLiveData: MutableLiveData<LoginDecorator> by lazy {
        MutableLiveData<LoginDecorator>()
    }

    val printTools: PrintTools by lazy {
        PrintTools()
    }

    private val database by lazy {
        DbHelper.getDatabase(context)
    }

    private val decorator: LoginDecorator by lazy {
        LoginDecorator()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun inidDatabase() {
        PrinterConfigSession.init(DbHelper.getDatabase(context))
    }

    fun onCpfChanged(newCpf: String) {
        decorator.currentCpf = newCpf
        decoratorLiveData.value = decorator
    }

    fun onCpfSubmitted() {
        val participants: List<Participant> =
            database.participantDao().getAll()?.toList() ?: listOf()

        if (participants.isNotEmpty()) {
            val participant = participants.firstOrNull {
                it.cpf == decoratorLiveData.value?.currentCpf
            }

            participant?.let {
                decorator.participant = it
                decorator.submitPath = LoginDecorator.SubmitPath.LOGIN_FOUND
                decoratorLiveData.value = decorator
                onLoginFound(it)
                return
            }

            onLoginNotFound()
        } else {
            onLoginNotFound()
        }
    }

    private fun onLoginFound(participant: Participant) {
        Handler().postDelayed({
            printTools.tryPrint(participant)
            decorator.resetState()
            decoratorLiveData.value = decorator
        }, 5000)
    }

    private fun onLoginNotFound() {
        decorator.participant = null
        decorator.submitPath = LoginDecorator.SubmitPath.LOGIN_NOT_FOUND
        decoratorLiveData.value = decorator
    }

}