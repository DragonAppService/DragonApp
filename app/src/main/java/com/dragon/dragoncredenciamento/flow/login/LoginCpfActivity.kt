package com.dragon.dragoncredenciamento.flow.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.extension.goToActivity
import com.dragon.dragoncredenciamento.flow.base.BaseActivity
import com.dragon.dragoncredenciamento.flow.cpf.CpfActivity
import com.dragon.dragoncredenciamento.flow.register.NewRegisterActivity
import com.dragon.dragoncredenciamento.tools.PrintTools
import kotlinx.android.synthetic.main.activity_login_cpf.*
import kotlinx.android.synthetic.main.partial_found_state.*
import kotlinx.android.synthetic.main.partial_login_state.*
import kotlinx.android.synthetic.main.partial_not_found_state.*

class LoginCpfActivity : BaseActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cpf)
        lifecycle.addObserver(viewModel)

        viewModel.decoratorLiveData.observe(this, Observer {
            updateUi(it)
        })

        viewModel.printTools.printStatusLive.observe(this, Observer {
            updatePrintStatusUi(it)
        })

        setupViews()
        setListeners()
    }

    override fun getBackgroundImageView(): View = loginActivityRoot

    private fun setupViews() {
        ViewCompat.setElevation(italicCpfSubmitLabel, 40f)
        ViewCompat.setElevation(cpfSubmitLabel, 40f)
    }

    private fun setListeners() {
        inputCpfEditText.addTextChangedListener {
            it?.apply { viewModel.onCpfChanged(this.toString()) }
        }

        cpfSubmitButton.setOnClickListener {
            viewModel.onCpfSubmitted()
        }
    }

    private fun updateUi(decorator: LoginDecorator) {
        cpfSubmitButton.isEnabled = decorator.isCpfInserted()
        when (decorator.submitPath) {
            LoginDecorator.SubmitPath.LOGIN_FOUND -> {
                loginFound(decorator)
            }
            LoginDecorator.SubmitPath.LOGIN_NOT_FOUND -> {
                loginNotFound()
            }
        }
    }

    private fun updatePrintStatusUi(state: PrintTools.PrintStatus) {
        when (state) {
            PrintTools.PrintStatus.PRINTING -> {
                //No code?
            }
            PrintTools.PrintStatus.ERROR -> {
                AlertDialog.Builder(this)
                    .setMessage(R.string.cpf_login_error_messaage)
                    .setOnDismissListener {
                        goToActivity(CpfActivity::class.java)
                        finish()
                    }
                    .create()
                    .show()
            }
            PrintTools.PrintStatus.PRINTED -> {
                printed()
            }
        }
    }

    private fun printed() {
        finish()
    }

    private fun loginFound(decorator: LoginDecorator) {
        partialLoginState.visibility = View.GONE
        partialFoundState.visibility = View.VISIBLE

        foundMessageTextView.text =
            getString(R.string.cpf_login_located_login_message, decorator.participant?.name)
    }

    private fun loginNotFound() {
        partialLoginState.visibility = View.GONE
        partialNotFoundState.visibility = View.VISIBLE

        ViewCompat.setElevation(createNewRegisterButtonMessage, 40f)
        ViewCompat.setElevation(createNewRegisterButtonItalicMessage, 40f)

        createNewRegisterButton.setOnClickListener {
            goToActivity(NewRegisterActivity::class.java)
        }
    }

}
