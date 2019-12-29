package com.dragon.dragoncredenciamento.flow.register.steps.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.flow.register.NewRegisterStep
import com.dragon.dragoncredenciamento.flow.register.NewRegisterViewModel
import com.dragon.dragoncredenciamento.model.Question
import kotlinx.android.synthetic.main.fragment_new_register_company.*

class NewRegisterCompanyFragment : Fragment() {

    companion object {
        val TAG: String = NewRegisterCompanyFragment::class.java.name
        fun newInstance(): NewRegisterCompanyFragment {
            return NewRegisterCompanyFragment()
        }
    }

    private val viewModel: NewRegisterViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(NewRegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_register_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.availableQuestionsLiveData.observe(this, Observer {
            updateUi(it)
        })

        setListeners()
    }

    private fun updateUi(questions: List<Question>) {
        companyNameEditText.visibility = if (questions[15].isActive) View.VISIBLE else View.INVISIBLE
        companyNameForBadgeEditText.visibility = if (questions[18].isActive) View.VISIBLE else View.INVISIBLE
        nameForBadgeEditText.visibility = if (questions[19].isActive) View.VISIBLE else View.INVISIBLE
        companyOfficeForBadgeEditText.visibility = if (questions[20].isActive) View.VISIBLE else View.INVISIBLE
    }

    private fun setListeners() {
        companyNameEditText.addTextChangedListener { viewModel.updateParticipantCompanyName(it.toString()) }
        companyNameForBadgeEditText.addTextChangedListener {
            viewModel.updateParticipantCompanyNameForBadge(
                it.toString()
            )
        }
        nameForBadgeEditText.addTextChangedListener { viewModel.updateParticipantNameForBadge(it.toString()) }
        companyOfficeForBadgeEditText.addTextChangedListener {
            viewModel.updateParticipantCompanyOfficeForBadge(
                it.toString()
            )
        }
        newRegisterAddressConfirmText.setOnClickListener {
            viewModel.updateCurrentStep(
                NewRegisterStep.RegisterStep.PRODUCTS
            )
        }
    }

}
