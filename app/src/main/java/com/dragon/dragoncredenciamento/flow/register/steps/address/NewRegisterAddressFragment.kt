package com.dragon.dragoncredenciamento.flow.register.steps.address

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
import kotlinx.android.synthetic.main.fragment_new_register_address.*

class NewRegisterAddressFragment : Fragment() {

    companion object {
        val TAG: String = NewRegisterAddressFragment::class.java.name
        fun newInstance(): NewRegisterAddressFragment {
            return NewRegisterAddressFragment()
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
        return inflater.inflate(R.layout.fragment_new_register_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.availableQuestionsLiveData.observe(this, Observer {
            updateUi(it)
        })

        setupListeners()
    }

    private fun updateUi(questions: List<Question>) {
        addressCountryEditText.visibility = if (questions[8].isActive) View.VISIBLE else View.INVISIBLE
        addressCityEditText.visibility = if (questions[9].isActive) View.VISIBLE else View.INVISIBLE
        val addressInfoWidgets =
            listOf<View>(addressAddressEditText, addressAddressNumberEditText)
        addressInfoWidgets.forEach {
            it.visibility = if (questions[10].isActive) View.VISIBLE else View.INVISIBLE
        }
        addressComplementEditText.visibility = if (questions[11].isActive) View.VISIBLE else View.INVISIBLE
        addressStateEditText.visibility = if (questions[12].isActive) View.VISIBLE else View.INVISIBLE
        addressNeighbourhoodEditText.visibility = if (questions[13].isActive) View.VISIBLE else View.INVISIBLE
        addressPostalCodeEditText.visibility = if (questions[14].isActive) View.VISIBLE else View.INVISIBLE
    }

    private fun setupListeners() {
        addressCountryEditText.addTextChangedListener { viewModel.updateParticipantCountry(it.toString()) }
        addressStateEditText.addTextChangedListener { viewModel.updateParticipantState(it.toString()) }
        addressCityEditText.addTextChangedListener { viewModel.updateParticipantCity(it.toString()) }
        addressNeighbourhoodEditText.addTextChangedListener {
            viewModel.updateParticipantNeighbourhood(
                it.toString()
            )
        }
        addressAddressEditText.addTextChangedListener { viewModel.updateParticipantAddress(it.toString()) }
        addressAddressNumberEditText.addTextChangedListener {
            viewModel.updateParticipantAddressNumber(
                it.toString()
            )
        }
        addressPostalCodeEditText.addTextChangedListener { viewModel.updateParticipantPostalCode(it.toString()) }
        addressComplementEditText.addTextChangedListener { viewModel.updateParticipantComplement(it.toString()) }
        newRegisterAddressConfirmText.setOnClickListener {
            viewModel.updateCurrentStep(
                NewRegisterStep.RegisterStep.COMPANY
            )
        }
    }

}
