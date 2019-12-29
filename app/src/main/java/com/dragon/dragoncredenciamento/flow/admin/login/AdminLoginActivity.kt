package com.dragon.dragoncredenciamento.flow.admin.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.extension.goToActivity
import com.dragon.dragoncredenciamento.flow.admin.AdminMainActivity
import com.dragon.dragoncredenciamento.flow.base.BaseActivity
import com.dragon.dragoncredenciamento.model.Admin
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLoginActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(AdminLoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        viewModel.decoratorLiveData.observe(this, Observer {
            updateUi(it)
        })

        ViewCompat.setElevation(adminLoginSubmitButtonText, 40F)

        setListeners()
    }

    override fun getBackgroundImageView(): View = adminRoot

    private fun setListeners() {
        adminLoginSubmitButton.setOnClickListener {
            viewModel.tryLogin(retrieveAdminFromFields())
        }
    }

    private fun updateUi(decorator: AdminLoginDecorator) {
        if (decorator.isAdminCredentialsCorrect == true) {
            goToActivity(AdminMainActivity::class.java)
        } else {
            Toast.makeText(this, R.string.admin_login_invalid_credentials, Toast.LENGTH_LONG).show()
        }
    }

    private fun retrieveAdminFromFields(): Admin {
        return Admin(
            restrictAreaPasswordEditText.text.toString()
        )
    }
}
