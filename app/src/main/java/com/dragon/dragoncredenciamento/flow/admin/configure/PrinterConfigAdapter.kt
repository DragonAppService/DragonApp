package com.dragon.dragoncredenciamento.flow.admin.configure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.model.PrinterConfig
import kotlinx.android.synthetic.main.printer_config_list_item.view.*

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
typealias OnConfigClickListener = (config: PrinterConfig) -> Unit

class PrinterConfigAdapter(var listener: OnConfigClickListener) :
    RecyclerView.Adapter<PrinterConfigAdapter.ViewHolder>() {

    var configurations: MutableList<PrinterConfig> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.printer_config_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = configurations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val config = configurations[position]

        holder.title.text = config.configTitle
        holder.chosenConfig.text = config.chosenConfig.optionTitle
        holder.itemView.isEnabled = config.isEnabled
        holder.itemView.alpha = if (config.isEnabled) 1f else 0.4f

        holder.itemView.setOnClickListener { listener.invoke(config) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.configTitle
        var chosenConfig: TextView = itemView.configChosenOption
    }
}