package com.dragon.dragoncredenciamento.flow.admin.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.model.ChartData
import com.dragon.dragoncredenciamento.tools.BarChartDateFormat
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.charts_activity.*

/**
 * @Author Guilherme
 * @Date 18/08/2019
 */
class ChartActivity : AppCompatActivity() {

    private val viewModel: ChartViewModel by lazy {
        ViewModelProviders.of(this).get(ChartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.charts_activity)
        lifecycle.addObserver(viewModel)

        viewModel.chartDataLiveData.observe(this, Observer {
            updateUi(it)
        })

    }

    private fun updateUi(newChartData: ChartData) {
        configureBarChart(newChartData)
        configurePieChart(newChartData)
    }

    private fun configurePieChart(newChartData: ChartData) {
        val pieEntries = mutableListOf<PieEntry>()

        newChartData.getRegisterByHour().forEach {
            pieEntries.add(PieEntry(it.second.toFloat(), it.first))
        }

        val pieDataSet = PieDataSet(pieEntries, getString(R.string.pie_chart_title))
        val pieData = PieData(pieDataSet)

        graphPieChart.data = pieData
        graphPieChart.invalidate()
    }

    private fun configureBarChart(newChartData: ChartData) {
        val barEntries = mutableListOf<BarEntry>()
        val valuesXArray = arrayListOf<String>()

        newChartData.getRegistersByDay().forEach { pair ->
            val xPos = newChartData.getRegistersByDay().indexOf(pair)

            barEntries.add(BarEntry(xPos.toFloat(), pair.second.toFloat()))
            valuesXArray.add(pair.first.time.toString())
        }

        graphBarChart.xAxis.valueFormatter = BarChartDateFormat(valuesXArray)

        val barDataSet = BarDataSet(barEntries, getString(R.string.bar_chart_title))
        val barData = BarData(barDataSet)

        graphBarChart.data = barData
        graphBarChart.invalidate()
    }

}