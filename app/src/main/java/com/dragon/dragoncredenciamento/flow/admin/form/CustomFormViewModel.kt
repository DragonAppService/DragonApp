package com.dragon.dragoncredenciamento.flow.admin.form

import android.app.Application
import androidx.lifecycle.*
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.model.Question

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
class CustomFormViewModel(var context: Application) : AndroidViewModel(context), LifecycleObserver {

    val customFormLiveData: MutableLiveData<List<Question>> by lazy {
        MutableLiveData<List<Question>>()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun retrieveCustomFormData() {
        val questions = DbHelper.getDatabase(context).questionsDao().getQuestions()

        customFormLiveData.postValue(questions)
    }

    fun onQuestionStatusChanged(question: Question) {
        DbHelper.getDatabase(context).questionsDao().update(question)

        val questions = DbHelper.getDatabase(context).questionsDao().getQuestions()

        customFormLiveData.postValue(questions)
    }

}