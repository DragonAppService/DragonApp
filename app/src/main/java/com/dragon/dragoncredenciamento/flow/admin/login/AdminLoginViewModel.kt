package com.dragon.dragoncredenciamento.flow.admin.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dragon.dragoncredenciamento.flow.base.BaseViewModel
import com.dragon.dragoncredenciamento.model.Admin

/**
 * @Author Guilherme
 * @Date 12/06/2019
 */
class AdminLoginViewModel(var app: Application) : BaseViewModel(app) {

    val decoratorLiveData by lazy {
        MutableLiveData<AdminLoginDecorator>()
    }

    private val decorator: AdminLoginDecorator by lazy {
        AdminLoginDecorator()
    }

    fun tryLogin(admin: Admin) {
        val validAdmin = Admin( "dragonapp321")

        decorator.isAdminCredentialsCorrect = admin == validAdmin

        decoratorLiveData.value = decorator
    }

}