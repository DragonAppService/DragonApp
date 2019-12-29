package com.dragon.dragoncredenciamento.flow.cpf

import android.app.Application
import androidx.lifecycle.*
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.flow.base.BaseViewModel
import com.dragon.dragoncredenciamento.model.Event

class CpfViewModel(var app: Application) : BaseViewModel(app), LifecycleObserver {

    private val database by lazy {
        DbHelper.getDatabase(app)
    }

    val eventLive: MutableLiveData<Event> by lazy {
        MutableLiveData<Event>()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun retrieveCurrentData() {
        val currentEvent = database.eventDao().get() ?: Event("Título Evento")

        eventLive.postValue(currentEvent)
    }

}