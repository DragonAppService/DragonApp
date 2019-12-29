package com.dragon.dragoncredenciamento.flow.admin.chart

import android.app.Application
import androidx.lifecycle.*
import com.dragon.dragoncredenciamento.database.AppDatabase
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.model.ChartData

/**
 * @Author Guilherme
 * @Date 18/08/2019
 */
class ChartViewModel(var context: Application) : AndroidViewModel(context), LifecycleObserver {

    val chartDataLiveData: MutableLiveData<ChartData> by lazy {
        MutableLiveData<ChartData>()
    }

    private val database: AppDatabase.Database by lazy {
        DbHelper.getDatabase(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun loadData() {
        val dataChart = database.chartDataDao().getAll()

        if (dataChart != null && dataChart.isNotEmpty()) {
            chartDataLiveData.postValue(dataChart[0])
        } else {
            chartDataLiveData.postValue(ChartData(ChartData.NO_CHART_DATA_ID, emptyList()))
        }
    }

}