package com.dragon.dragoncredenciamento.flow.admin

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.extension.getPath
import com.dragon.dragoncredenciamento.extension.goToActivity
import com.dragon.dragoncredenciamento.extension.toast
import com.dragon.dragoncredenciamento.flow.admin.chart.ChartActivity
import com.dragon.dragoncredenciamento.flow.admin.configure.PrintConfigActivity
import com.dragon.dragoncredenciamento.flow.admin.form.CustomFormActivity
import com.dragon.dragoncredenciamento.flow.base.BaseActivity
import com.dragon.dragoncredenciamento.model.ExportDbPath
import com.dragon.dragoncredenciamento.model.ImportDbPath
import kotlinx.android.synthetic.main.activity_admin_main.*
import pl.aprilapps.easyphotopicker.*

class AdminMainActivity : BaseActivity() {

    companion object {
        private const val FILE_REQUEST_CODE = 321
    }

    private val viewModel: AdminViewModel by lazy {
        ViewModelProviders.of(this).get(AdminViewModel::class.java)
    }

    private val importCsvDialog: AlertDialog.Builder by lazy {
        AlertDialog.Builder(this)
    }

    private val easyImage: EasyImage by lazy {
        EasyImage.Builder(this)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .allowMultiple(false)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        viewModel.exportPathLive.observe(this, Observer {
            updateExportUi(it)
        })

        viewModel.importPathLive.observe(this, Observer {
            updateImportUi(it)
        })

        viewModel.backgroundEventLiveData.observe(this, Observer {
            updateBackgroundUi()
        })

        setListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null)
            return
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                FILE_REQUEST_CODE -> {
                    val filePath = data.data?.let(this::getPath)
                    filePath?.let { viewModel.importCsv(it) }
                }
            }
            easyImage.handleActivityResult(
                requestCode,
                resultCode,
                data,
                this,
                object : DefaultCallback() {

                    override fun onMediaFilesPicked(
                        imageFiles: Array<MediaFile>,
                        source: MediaSource
                    ) {
                        viewModel.changeBackground(imageFiles[0].file.path)
                    }

                })
        }
    }

    override fun getBackgroundImageView(): View = adminMainRootView

    private fun setListeners() {
        importCsvButton.setOnClickListener {
            setupImportDialog()
        }
        exportCsvButton.setOnClickListener {
            viewModel.exportCsv()
        }
        changeBgButton.setOnClickListener {
            startImagePickerIntent()
        }
        editFormButton.setOnClickListener {
            goToActivity(CustomFormActivity::class.java)
        }
        statusButton.setOnClickListener {
            goToActivity(ChartActivity::class.java)
        }
        printConfigButton.setOnClickListener {
            goToActivity(PrintConfigActivity::class.java)
        }
    }

    private fun setupImportDialog() {
        importCsvDialog.setMessage(R.string.admin_activity_import_csv_dialog_message)
            .setPositiveButton(
                R.string.admin_activity_import_csv_dialog_positive_btn
            ) { _, _ ->
                startFileIntent()
            }
            .setNegativeButton(R.string.admin_activity_import_csv_dialog_negative_btn) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun startImagePickerIntent() {
        easyImage.openChooser(this)
    }

    private fun startFileIntent() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/*"
        try {
            startActivityForResult(intent, FILE_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            Log.e(AdminMainActivity::class.java.canonicalName, e.message)
        }
    }

    private fun updateExportUi(path: ExportDbPath) {
        when (path) {
            ExportDbPath.EXPORTED -> toast(getString(R.string.admin_activity_db_exported_message))
            ExportDbPath.EXPORT_ERROR -> toast(getString(R.string.admin_activity_db_not_exported_message))
            ExportDbPath.EMPTY_PARTICIPANTS -> toast(getString(R.string.admin_activity_db_empty_message))
        }
    }

    private fun updateImportUi(path: ImportDbPath) {
        when (path) {
            ImportDbPath.IMPORTED -> toast(getString(R.string.admin_activity_db_imported_message))
            ImportDbPath.IMPORT_ERROR -> toast(getString(R.string.admin_activity_db_not_imported_message))
            ImportDbPath.UNSUPPORTED_FORMAT -> toast(getString(R.string.admin_activity_db_import_unsupported))
        }
    }

    private fun updateBackgroundUi() {
        reloadBackground()
    }

}