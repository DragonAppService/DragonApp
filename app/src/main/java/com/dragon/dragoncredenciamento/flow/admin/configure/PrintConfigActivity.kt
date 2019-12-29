package com.dragon.dragoncredenciamento.flow.admin.configure

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.model.PrinterConfig
import kotlinx.android.synthetic.main.activity_print_config.*

class PrintConfigActivity : AppCompatActivity(), OnConfigClickListener {

    private val viewModel: PrintConfigViewModel by lazy {
        ViewModelProviders.of(this).get(PrintConfigViewModel::class.java)
    }

    private val adapter: PrinterConfigAdapter by lazy {
        PrinterConfigAdapter(this)
    }

    private val configDialogBuilder: AlertDialog.Builder by lazy {
        AlertDialog.Builder(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_config)
        lifecycle.addObserver(viewModel)

        viewModel.printerConfigurationsLiveData.observe(this, Observer {
            updateUi(it)
        })

        viewModel.printTools.printersIps.observe(this, Observer {
            updateIpsUi(it)
        })

        setupViews()
    }

    override fun invoke(config: PrinterConfig) {
        val optionsText: Array<CharSequence> = config.configOptions.map {
            it.optionTitle
        }.toTypedArray()

        if (config.configType == PrinterConfig.ConfigType.DEVICE) {
            viewModel.searchPrinters()
            return
        }

        configDialogBuilder.setTitle(config.configTitle)
        configDialogBuilder.setItems(optionsText) { dialog, which ->
            viewModel.updateConfig(config, config.configOptions[which])
            dialog.dismiss()
        }
        configDialogBuilder.create().show()
    }

    private fun setupViews() {
        ViewCompat.setElevation(configPrinterToolbar, 4f)
        setSupportActionBar(configPrinterToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configPrinterToolbar.setBackgroundColor(Color.parseColor("#4285F4"))
        configPrinterMainConfigs.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        configPrinterMainConfigs.adapter = adapter
    }

    private fun updateUi(configList: MutableList<PrinterConfig>) {
        adapter.configurations = configList
        adapter.notifyDataSetChanged()
    }

    private fun updateIpsUi(ips: List<String>) {
        configDialogBuilder.setTitle("Dispositivo")
        configDialogBuilder.setItems(ips.toTypedArray()) { dialog, which ->
            viewModel.updateChosenDevice(ips[which])
            dialog.dismiss()
        }
        configDialogBuilder.create().show()
    }

}
