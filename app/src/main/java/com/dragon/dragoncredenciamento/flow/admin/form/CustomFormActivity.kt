package com.dragon.dragoncredenciamento.flow.admin.form

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.model.Question
import kotlinx.android.synthetic.main.activity_custom_form.*

class CustomFormActivity : AppCompatActivity() {

    private val viewModel: CustomFormViewModel by lazy {
        ViewModelProviders.of(this).get(CustomFormViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_form)
        lifecycle.addObserver(viewModel)

        viewModel.customFormLiveData.observe(this, Observer {
            updateUi(it)
        })

        setupViews()
        setListeners()
    }

    private fun setupViews() {
        ViewCompat.setElevation(customFormToolbar, 4f)
        setSupportActionBar(customFormToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        customFormToolbar.setBackgroundColor(Color.parseColor("#4285F4"))
    }

    private fun setListeners() {
        setPersonalInfoListeners()
        setAddressListeners()
        setCompanyListeners()
        setProductsListeners()
    }

    private fun setPersonalInfoListeners() {
        firstNameCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 1,
                    isActive = firstNameCheckbox.isChecked
                )
            )
        }
        genderCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 2,
                    isActive = genderCheckbox.isChecked
                )
            )
        }
        emailCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 3,
                    isActive = emailCheckbox.isChecked
                )
            )
        }
        homePhoneCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 4,
                    isActive = homePhoneCheckbox.isChecked
                )
            )
        }
        surnameCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 5,
                    isActive = surnameCheckbox.isChecked
                )
            )
        }
        birthdateCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 6,
                    isActive = birthdateCheckbox.isChecked
                )
            )
        }
        copyEmailCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 7,
                    isActive = copyEmailCheckbox.isChecked
                )
            )
        }
        cellphoneCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 8,
                    isActive = cellphoneCheckbox.isChecked
                )
            )
        }
    }

    private fun setAddressListeners() {
        countryCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 9,
                    isActive = countryCheckbox.isChecked
                )
            )
        }
        cityCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 10,
                    isActive = cityCheckbox.isChecked
                )
            )
        }
        addressCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 11,
                    isActive = addressCheckbox.isChecked
                )
            )
        }
        complementCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 12,
                    isActive = complementCheckbox.isChecked
                )
            )
        }
        stateCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 13,
                    isActive = stateCheckbox.isChecked
                )
            )
        }
        neighborhoodCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 14,
                    isActive = neighborhoodCheckbox.isChecked
                )
            )
        }
        postalCodeCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 15,
                    isActive = postalCodeCheckbox.isChecked
                )
            )
        }
    }

    private fun setCompanyListeners() {
        companyNameCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 16,
                    isActive = companyNameCheckbox.isChecked
                )
            )
        }
        companyActivityCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 17,
                    isActive = companyActivityCheckbox.isChecked
                )
            )
        }
        companyOfficeCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 18,
                    isActive = companyOfficeCheckbox.isChecked
                )
            )
        }
        companyNameForBadgeCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 19,
                    isActive = companyNameForBadgeCheckbox.isChecked
                )
            )
        }
        companyBadgeNameCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 20,
                    isActive = companyBadgeNameCheckbox.isChecked
                )
            )
        }
        companyJobCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 21,
                    isActive = companyJobCheckbox.isChecked
                )
            )
        }
    }

    private fun setProductsListeners() {
        productsBuyAndProductionCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 22,
                    isActive = productsBuyAndProductionCheckbox.isChecked
                )
            )
        }
        productsConnectedMediaCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 23,
                    isActive = productsConnectedMediaCheckbox.isChecked
                )
            )
        }
        productsDisplaysCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 24,
                    isActive = productsDisplaysCheckbox.isChecked
                )
            )
        }
        productsSystemManagementCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 25,
                    isActive = productsSystemManagementCheckbox.isChecked
                )
            )
        }
        productsProAudioCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 26,
                    isActive = productsProAudioCheckbox.isChecked
                )
            )
        }
        productsContentCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 27,
                    isActive = productsContentCheckbox.isChecked
                )
            )
        }
        productsDistributionAndDeliveriesCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 28,
                    isActive = productsDistributionAndDeliveriesCheckbox.isChecked
                )
            )
        }
        productsServicesCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 29,
                    isActive = productsServicesCheckbox.isChecked
                )
            )
        }
        productsPosProductionCheckbox.setOnClickListener {
            viewModel.onQuestionStatusChanged(
                Question(
                    id = 30,
                    isActive = productsPosProductionCheckbox.isChecked
                )
            )
        }
    }

    private fun updateUi(questions: List<Question>) {
        updatePersonalInfoUi(questions)
        updateAddressUi(questions)
        updateCompanyUi(questions)
        updateProductsUi(questions)
    }

    private fun updatePersonalInfoUi(questions: List<Question>) {
        firstNameCheckbox.isChecked = questions[0].isActive
        genderCheckbox.isChecked = questions[1].isActive
        emailCheckbox.isChecked = questions[2].isActive
        homePhoneCheckbox.isChecked = questions[3].isActive
        surnameCheckbox.isChecked = questions[4].isActive
        birthdateCheckbox.isChecked = questions[5].isActive
        copyEmailCheckbox.isChecked = questions[6].isActive
        cellphoneCheckbox.isChecked = questions[7].isActive
    }

    private fun updateAddressUi(questions: List<Question>) {
        countryCheckbox.isChecked = questions[8].isActive
        cityCheckbox.isChecked = questions[9].isActive
        addressCheckbox.isChecked = questions[10].isActive
        complementCheckbox.isChecked = questions[11].isActive
        stateCheckbox.isChecked = questions[12].isActive
        neighborhoodCheckbox.isChecked = questions[13].isActive
        postalCodeCheckbox.isChecked = questions[14].isActive
    }

    private fun updateCompanyUi(questions: List<Question>) {
        companyNameCheckbox.isChecked = questions[15].isActive
        companyActivityCheckbox.isChecked = questions[16].isActive
        companyOfficeCheckbox.isChecked = questions[17].isActive
        companyNameForBadgeCheckbox.isChecked = questions[18].isActive
        companyBadgeNameCheckbox.isChecked = questions[19].isActive
        companyJobCheckbox.isChecked = questions[20].isActive
    }

    private fun updateProductsUi(questions: List<Question>) {
        productsBuyAndProductionCheckbox.isChecked = questions[21].isActive
        productsConnectedMediaCheckbox.isChecked = questions[22].isActive
        productsDisplaysCheckbox.isChecked = questions[23].isActive
        productsSystemManagementCheckbox.isChecked = questions[24].isActive
        productsProAudioCheckbox.isChecked = questions[25].isActive
        productsContentCheckbox.isChecked = questions[26].isActive
        productsDistributionAndDeliveriesCheckbox.isChecked = questions[27].isActive
        productsServicesCheckbox.isChecked = questions[28].isActive
        productsPosProductionCheckbox.isChecked = questions[29].isActive
    }
}
