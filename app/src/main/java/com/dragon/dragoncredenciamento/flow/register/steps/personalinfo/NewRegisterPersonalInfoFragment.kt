package com.dragon.dragoncredenciamento.flow.register.steps.personalinfo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.KeyEvent.ACTION_UP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.flow.register.NewRegisterStep
import com.dragon.dragoncredenciamento.flow.register.NewRegisterViewModel
import com.dragon.dragoncredenciamento.model.Gender
import com.dragon.dragoncredenciamento.model.Question
import kotlinx.android.synthetic.main.fragment_new_register_personal_info.*
import java.util.*

class NewRegisterPersonalInfoFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        val TAG: String = NewRegisterPersonalInfoFragment::class.java.name
        fun newInstance(): NewRegisterPersonalInfoFragment {
            return NewRegisterPersonalInfoFragment()
        }
    }

    private val viewModel: NewRegisterViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(NewRegisterViewModel::class.java)
    }

    private val genderDialog: AlertDialog.Builder by lazy {
        AlertDialog.Builder(requireContext())
    }

    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_register_personal_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.availableQuestionsLiveData.observe(this, androidx.lifecycle.Observer {
            updateUi(it)
        })

        setupViews()
        setListeners()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.updateParticipantBirthdate(GregorianCalendar(year, month, dayOfMonth).time)
        userBirthdateDayEditText.setText(dayOfMonth.toString())
        userBirthdateMonthEditText.setText(month.toString())
        userBirthdateYearEditText.setText(year.toString())
    }

    private fun updateUi(questions: List<Question>) {
        userNameEditText.visibility = if (questions[0].isActive) View.VISIBLE else View.INVISIBLE
        userGenderEditText.visibility = if (questions[1].isActive) View.VISIBLE else View.INVISIBLE
        userEmailEditText.visibility = if (questions[2].isActive) View.VISIBLE else View.INVISIBLE
        val phoneWidgets =
            listOf<View>(userPhoneCodeEditText, userPhoneNumberEditText, userPhoneBranchEditText)
        phoneWidgets.forEach {
            it.visibility = if (questions[3].isActive) View.VISIBLE else View.INVISIBLE
        }
        userSurnameEditText.visibility = if (questions[4].isActive) View.VISIBLE else View.INVISIBLE
        val birthDateWidgets =
            listOf<View>(userBirthdateDayEditText, userBirthdateMonthEditText, userBirthdateYearEditText)
        birthDateWidgets.forEach {
            it.visibility = if (questions[5].isActive) View.VISIBLE else View.INVISIBLE
        }
        userCopyEmailEditText.visibility = if (questions[6].isActive) View.VISIBLE else View.INVISIBLE
        val mobilePhoneWidgets =
            listOf<View>(userMobileCodeEditText, userMobileNumberEditText)
        mobilePhoneWidgets.forEach {
            it.visibility = if (questions[7].isActive) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun setupViews() {
        val currentDateCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            requireContext(),
            android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
            this,
            currentDateCalendar.get(Calendar.YEAR),
            currentDateCalendar.get(Calendar.MONTH),
            currentDateCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun setListeners() {
        userNameEditText.addTextChangedListener { viewModel.updateParticipantName(it.toString()) }
        userSurnameEditText.addTextChangedListener { viewModel.updateParticipantSurname(it.toString()) }
        userGenderEditText.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP) {
                genderDialog.setItems(R.array.new_register_gender_array) { dialog, which ->
                    when (which) {
                        0 -> {
                            viewModel.updateParticipantGender(Gender.MALE)
                            userGenderEditText.setText(Gender.MALE.description)
                        }
                        1 -> {
                            viewModel.updateParticipantGender(Gender.FEMALE)
                            userGenderEditText.setText(Gender.FEMALE.description)
                        }
                    }
                    dialog.dismiss()
                }
                genderDialog.show()

            }
            return@setOnTouchListener true
        }
        userBirthdateDayEditText.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP) {
                datePickerDialog.show()
            }
            return@setOnTouchListener true
        }
        userBirthdateMonthEditText.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP) {
                datePickerDialog.show()
            }
            return@setOnTouchListener true
        }
        userBirthdateYearEditText.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP) {
                datePickerDialog.show()
            }
            return@setOnTouchListener true
        }
        userCpfEditText.addTextChangedListener { viewModel.updateParticipantCpf(it.toString()) }
        userEmailEditText.addTextChangedListener { viewModel.updateParticipantEmail(it.toString()) }
        userCopyEmailEditText.addTextChangedListener { viewModel.updateParticipantCopyEmail(it.toString()) }
        userPhoneCodeEditText.addTextChangedListener { viewModel.updateParticipantPhoneCode(it.toString()) }
        userPhoneNumberEditText.addTextChangedListener { viewModel.updateParticipantPhoneNumber(it.toString()) }
        userPhoneBranchEditText.addTextChangedListener { viewModel.updateParticipantBranch(it.toString()) }
        userMobileCodeEditText.addTextChangedListener { viewModel.updateParticipantMobileCode(it.toString()) }
        userMobileNumberEditText.addTextChangedListener { viewModel.updateParticipantMobileNumber(it.toString()) }

        newRegisterConfirmText.setOnClickListener {
            viewModel.updateCurrentStep(NewRegisterStep.RegisterStep.ADDRESS)
        }
    }

}
