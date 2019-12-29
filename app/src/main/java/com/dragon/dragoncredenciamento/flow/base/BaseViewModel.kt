package com.dragon.dragoncredenciamento.flow.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dragon.dragoncredenciamento.model.Background

/**
 * @Author Guilherme
 * @Date 15/06/2019
 */
open class BaseViewModel(var context: Application) : AndroidViewModel(context) {

    val backgroundEventLiveData: MutableLiveData<Background> by lazy {
        MutableLiveData<Background>()
    }

    fun onBackgroundChanged(background: Background) {
        backgroundEventLiveData.postValue(background)
    }

}