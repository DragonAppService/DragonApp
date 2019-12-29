package com.dragon.dragoncredenciamento.flow.cpf

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.extension.goToActivity
import com.dragon.dragoncredenciamento.flow.admin.login.AdminLoginActivity
import com.dragon.dragoncredenciamento.flow.base.BaseActivity
import com.dragon.dragoncredenciamento.flow.login.LoginCpfActivity
import com.dragon.dragoncredenciamento.flow.register.NewRegisterActivity
import com.dragon.dragoncredenciamento.model.Event
import kotlinx.android.synthetic.main.activity_cpf.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*

class CpfActivity : BaseActivity() {

    companion object {
        private const val RC_WRITE_READ_EXTERNAL_STORAGE = 112
    }

    private var handler: Handler? = Handler()

    private val viewModel: CpfViewModel by lazy {
        ViewModelProviders.of(this).get(CpfViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpf)

        verifyHasNeededPermissions()

        lifecycle.addObserver(viewModel)

        viewModel.eventLive.observe(this, Observer {
            updateUi(it)
        })

        setListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun getBackgroundImageView(): View = cpfActivityRoot

    @AfterPermissionGranted(RC_WRITE_READ_EXTERNAL_STORAGE)
    private fun verifyHasNeededPermissions() {
        val perms = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (!EasyPermissions.hasPermissions(this, *perms)) {
            EasyPermissions.requestPermissions(
                this, getString(R.string.cpf_activity_rationale_dialog_message),
                RC_WRITE_READ_EXTERNAL_STORAGE, *perms
            )
        }

    }

    private fun setListeners() {
        updateTimer()
        adminAreaBackground.setOnClickListener { goToActivity(AdminLoginActivity::class.java) }
        preRegisterButton.setOnClickListener { goToActivity(LoginCpfActivity::class.java) }
        newRegisterButton.setOnClickListener { goToActivity(NewRegisterActivity::class.java) }
    }

    private fun updateTimer() {
        handler?.postDelayed({
            currentDateText?.text = SimpleDateFormat("HH:mm:ss", Locale("pt", "BR")).format(Date())
            updateTimer()
        }, 1000)
    }

    private fun updateUi(event: Event) {
        eventNameText.text = event.eventName
    }

}