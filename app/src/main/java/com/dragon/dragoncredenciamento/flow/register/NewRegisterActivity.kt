package com.dragon.dragoncredenciamento.flow.register

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.flow.register.steps.address.NewRegisterAddressFragment
import com.dragon.dragoncredenciamento.flow.register.steps.company.NewRegisterCompanyFragment
import com.dragon.dragoncredenciamento.flow.register.steps.personalinfo.NewRegisterPersonalInfoFragment
import com.dragon.dragoncredenciamento.flow.register.steps.products.NewRegisterProductsFragment
import com.dragon.dragoncredenciamento.tools.UiTools
import kotlinx.android.synthetic.main.activity_new_register.*
import kotlinx.android.synthetic.main.activity_new_register_header.*

class NewRegisterActivity : AppCompatActivity() {

    private val viewModel: NewRegisterViewModel by lazy {
        ViewModelProviders.of(this).get(NewRegisterViewModel::class.java)
    }

    private val savingParticipantDialog: AlertDialog by lazy {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setView(ProgressBar(this).apply { isIndeterminate = true })
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_register)
        lifecycle.addObserver(viewModel)

        viewModel.currentStepLiveData.observe(this, Observer {
            updateUi(it)
        })

        viewModel.savingParticipantLiveData.observe(this, Observer {
            updateSavingParticipantUi(it)
        })

        setupViews()
    }

    private fun setupViews() {
        ViewCompat.setElevation(newRegisterProductsToolbar, 4f)
        setSupportActionBar(newRegisterProductsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        newRegisterProductsToolbar.setBackgroundColor(Color.parseColor("#4285F4"))
    }

    private fun updateUi(step: NewRegisterStep) {
        var fragment: Fragment? = null
        var tag: String? = null
        when (step.currentStep) {
            NewRegisterStep.RegisterStep.PERSONAL_INFO -> {
                fragment = NewRegisterPersonalInfoFragment.newInstance()
                tag = NewRegisterPersonalInfoFragment.TAG

                personalInfoBlueCircle.setImageResource(R.drawable.blue_circle)
                personalInfoBlueCircleText.visibility = View.VISIBLE
                personalInfoBlueCircleImage.visibility = View.INVISIBLE
                personalInfoTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                addressBlueCircle.setImageResource(R.drawable.grey_circle)
                addressBlueCircleText.visibility = View.VISIBLE
                addressBlueCircleImage.visibility = View.INVISIBLE
                addressTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )

                companyBlueCircle.setImageResource(R.drawable.grey_circle)
                companyBlueCircleText.visibility = View.VISIBLE
                companyBlueCircleImage.visibility = View.INVISIBLE
                companyTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )

                productsBlueCircle.setImageResource(R.drawable.grey_circle)
                productsBlueCircleText.visibility = View.VISIBLE
                productsBlueCircleImage.visibility = View.INVISIBLE
                productsTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )
            }
            NewRegisterStep.RegisterStep.ADDRESS -> {
                fragment = NewRegisterAddressFragment.newInstance()
                tag = NewRegisterAddressFragment.TAG

                personalInfoBlueCircle.setImageResource(R.drawable.blue_circle)
                personalInfoBlueCircleText.visibility = View.INVISIBLE
                personalInfoBlueCircleImage.visibility = View.VISIBLE
                personalInfoTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                addressBlueCircle.setImageResource(R.drawable.blue_circle)
                addressBlueCircleText.visibility = View.VISIBLE
                addressBlueCircleImage.visibility = View.INVISIBLE
                addressTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                companyBlueCircle.setImageResource(R.drawable.grey_circle)
                companyBlueCircleText.visibility = View.VISIBLE
                companyBlueCircleImage.visibility = View.INVISIBLE
                companyTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )

                productsBlueCircle.setImageResource(R.drawable.grey_circle)
                productsBlueCircleText.visibility = View.VISIBLE
                productsBlueCircleImage.visibility = View.INVISIBLE
                productsTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )
            }
            NewRegisterStep.RegisterStep.COMPANY -> {
                fragment = NewRegisterCompanyFragment.newInstance()
                tag = NewRegisterCompanyFragment.TAG

                personalInfoBlueCircle.setImageResource(R.drawable.blue_circle)
                personalInfoBlueCircleText.visibility = View.INVISIBLE
                personalInfoBlueCircleImage.visibility = View.VISIBLE
                personalInfoTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                addressBlueCircle.setImageResource(R.drawable.blue_circle)
                addressBlueCircleText.visibility = View.INVISIBLE
                addressBlueCircleImage.visibility = View.VISIBLE
                addressTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                companyBlueCircle.setImageResource(R.drawable.blue_circle)
                companyBlueCircleText.visibility = View.VISIBLE
                companyBlueCircleImage.visibility = View.INVISIBLE
                companyTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                productsBlueCircle.setImageResource(R.drawable.grey_circle)
                productsBlueCircleText.visibility = View.VISIBLE
                productsBlueCircleImage.visibility = View.INVISIBLE
                productsTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.grey,
                        null
                    )
                )
            }
            NewRegisterStep.RegisterStep.PRODUCTS -> {
                fragment = NewRegisterProductsFragment.newInstance()
                tag = NewRegisterProductsFragment.TAG

                personalInfoBlueCircle.setImageResource(R.drawable.blue_circle)
                personalInfoBlueCircleText.visibility = View.INVISIBLE
                personalInfoBlueCircleImage.visibility = View.VISIBLE
                personalInfoTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                addressBlueCircle.setImageResource(R.drawable.blue_circle)
                addressBlueCircleText.visibility = View.INVISIBLE
                addressBlueCircleImage.visibility = View.VISIBLE
                addressTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                companyBlueCircle.setImageResource(R.drawable.blue_circle)
                companyBlueCircleText.visibility = View.INVISIBLE
                companyBlueCircleImage.visibility = View.VISIBLE
                companyTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )

                productsBlueCircle.setImageResource(R.drawable.blue_circle)
                productsBlueCircleText.visibility = View.VISIBLE
                productsBlueCircleImage.visibility = View.INVISIBLE
                productsTextView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.black,
                        null
                    )
                )
            }
        }
        changeFragment(fragment, tag)
    }

    private fun updateSavingParticipantUi(newEvent: NewRegisterViewModel.SaveParticipantEvent) {
        when (newEvent) {
            NewRegisterViewModel.SaveParticipantEvent.SAVING -> savingParticipantDialog.show()
            NewRegisterViewModel.SaveParticipantEvent.ALREADY_REGISTERED -> {
                savingParticipantDialog.dismiss()
                Toast.makeText(
                    this,
                    R.string.new_register_participant_already_registered_message,
                    Toast.LENGTH_LONG
                ).show()
            }
            NewRegisterViewModel.SaveParticipantEvent.SAVED -> {
                savingParticipantDialog.dismiss()
                finish()
            }
        }
    }

    private fun changeFragment(newFragment: Fragment, tag: String) {
        UiTools.loadFragmentNoBackstack(
            supportFragmentManager,
            R.id.newRegisterCurrentStepFrame,
            newFragment,
            tag
        )
    }
}
