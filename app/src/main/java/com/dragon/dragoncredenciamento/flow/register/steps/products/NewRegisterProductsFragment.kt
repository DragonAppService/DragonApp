package com.dragon.dragoncredenciamento.flow.register.steps.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.flow.register.NewRegisterViewModel
import com.dragon.dragoncredenciamento.model.Question
import kotlinx.android.synthetic.main.fragment_new_register_products.*

class NewRegisterProductsFragment : Fragment() {

    companion object {
        val TAG: String = NewRegisterProductsFragment::class.java.name
        fun newInstance() : NewRegisterProductsFragment {
            return NewRegisterProductsFragment()
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
        return inflater.inflate(R.layout.fragment_new_register_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.availableQuestionsLiveData.observe(this, Observer {
            updateUi(it)
        })

        setListeners()
    }

    private fun updateUi(questions: List<Question>) {
        val acquisitionAndProductionWidgets =
            listOf<View>(
                newRegisterProductsBuyAndProductionLabel,
                newRegisterProductsGadgetsCheckbox,
                newRegisterProductsCinemaCapitationCheckbox,
                newRegisterProductsSetDesignCheckbox,
                newRegisterProductsRemoteContributionCheckbox,
                newRegisterProductsCameraAndLensCheckbox,
                newRegisterProductsLightsAndSupportsCheckbox,
                newRegisterProductsMicrophoneCheckbox,
                newRegisterProductsMobileProductionCheckbox,
                newRegisterProductsCinemaProductionCheckbox,
                newRegisterProductsWorkflowSolutionsCheckbox,
                newRegisterProductsDigitalSolutionsCheckbox
            )

        acquisitionAndProductionWidgets.forEach {
            it.visibility = if (questions[21].isActive) View.VISIBLE else View.INVISIBLE
        }

        val productsDisplaysWidgets =
            listOf<View>(
                newRegisterProductsDisplaysLabel,
                newRegisterProductsDisplaysBusinessStakeholdersCheckbox,
                newRegisterProductsDisplaysEventsCheckbox,
                newRegisterProductsDisplaysTabletsCheckbox
            )

        productsDisplaysWidgets.forEach {
            it.visibility = if (questions[23].isActive) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun setListeners() {
        newRegisterProductsGadgetsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantGadgets(
                isChecked
            )
        }
        newRegisterProductsCinemaCapitationCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantCinemaCapitation(
                isChecked
            )
        }
        newRegisterProductsSetDesignCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantSetDesign(
                isChecked
            )
        }
        newRegisterProductsRemoteContributionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantRemoteContribution(
                isChecked
            )
        }
        newRegisterProductsCameraAndLensCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantCameraAndLens(
                isChecked
            )
        }
        newRegisterProductsLightsAndSupportsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantLightsAndSupports(
                isChecked
            )
        }
        newRegisterProductsMicrophoneCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantMicrophone(
                isChecked
            )
        }
        newRegisterProductsMobileProductionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantMobileProduction(
                isChecked
            )
        }
        newRegisterProductsCinemaProductionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantCinemaProduction(
                isChecked
            )
        }
        newRegisterProductsWorkflowSolutionsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantWorkflowSolutions(
                isChecked
            )
        }
        newRegisterProductsDigitalSolutionsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantDigitalSolutions(
                isChecked
            )
        }
        newRegisterProductsDisplaysBusinessStakeholdersCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantDisplaysBusinessStakeholders(
                isChecked
            )
        }
        newRegisterProductsDisplaysEventsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantDisplaysEvents(
                isChecked
            )
        }
        newRegisterProductsDisplaysTabletsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantDisplaysTablets(
                isChecked
            )
        }
        newRegisterProductsOptInPartnerEmailsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantPartnerEmails(
                isChecked
            )
        }
        newRegisterProductsOptInEventChangesEmailsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantEventChangesEmails(
                isChecked
            )
        }
        newRegisterProductsOptInSetNewsletterCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateParticipantSetNewsletter(
                isChecked
            )
        }
        newRegisterProductsConfirmText.setOnClickListener { viewModel.saveNewParticipant() }
    }

}
