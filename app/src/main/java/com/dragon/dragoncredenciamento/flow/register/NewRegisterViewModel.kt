package com.dragon.dragoncredenciamento.flow.register

import android.app.Application
import androidx.lifecycle.*
import com.dragon.dragoncredenciamento.database.DbHelper
import com.dragon.dragoncredenciamento.model.ChartData
import com.dragon.dragoncredenciamento.model.Gender
import com.dragon.dragoncredenciamento.model.Participant
import com.dragon.dragoncredenciamento.model.Question
import java.util.*

/**
 * @Author Guilherme
 * @Date 30/06/2019
 */
class NewRegisterViewModel(var context: Application) : AndroidViewModel(context),
    LifecycleObserver {

    val currentStepLiveData: MutableLiveData<NewRegisterStep> by lazy {
        MutableLiveData<NewRegisterStep>()
    }

    val availableQuestionsLiveData: MutableLiveData<List<Question>> by lazy {
        MutableLiveData<List<Question>>()
    }

    val savingParticipantLiveData: MutableLiveData<SaveParticipantEvent> by lazy {
        MutableLiveData<SaveParticipantEvent>()
    }

    enum class SaveParticipantEvent {
        SAVING,
        ALREADY_REGISTERED,
        SAVED
    }

    private val participant: Participant by lazy {
        Participant("", "")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStartNewRegister() {
        val questions = DbHelper.getDatabase(context).questionsDao().getQuestions()

        availableQuestionsLiveData.postValue(questions)
        currentStepLiveData.postValue(NewRegisterStep(NewRegisterStep.RegisterStep.PERSONAL_INFO))
    }

    //Personal Info
    fun updateParticipantName(name: String) {
        this.participant.name = name
    }

    fun updateParticipantSurname(surname: String) {
        this.participant.surname = surname
    }

    fun updateParticipantGender(gender: Gender) {
        this.participant.gender = gender.description
    }

    fun updateParticipantBirthdate(birthdate: Date) {
        this.participant.birthdate = birthdate
    }

    fun updateParticipantCpf(cpf: String) {
        this.participant.cpf = cpf
    }

    fun updateParticipantEmail(email: String) {
        this.participant.email = email
    }

    fun updateParticipantCopyEmail(copyEmail: String) {
        this.participant.copyEmail = copyEmail
    }

    fun updateParticipantPhoneCode(phoneCode: String) {
        this.participant.phoneCode = phoneCode
    }

    fun updateParticipantPhoneNumber(phoneNumber: String) {
        this.participant.phoneNumber = phoneNumber
    }

    fun updateParticipantBranch(branch: String) {
        this.participant.branch = branch
    }

    fun updateParticipantMobileCode(mobileCode: String) {
        this.participant.mobileCode = mobileCode
    }

    fun updateParticipantMobileNumber(mobileNumber: String) {
        this.participant.mobileNumber = mobileNumber
    }

    //Address
    fun updateParticipantCountry(newCountry: String) {
        this.participant.country = newCountry
    }

    fun updateParticipantState(newState: String) {
        this.participant.state = newState
    }

    fun updateParticipantCity(newCity: String) {
        this.participant.city = newCity
    }

    fun updateParticipantNeighbourhood(newNeighbourhood: String) {
        this.participant.neighbourhood = newNeighbourhood
    }

    fun updateParticipantAddress(newAddress: String) {
        this.participant.address = newAddress
    }

    fun updateParticipantAddressNumber(newAddressNumber: String) {
        this.participant.addressNumber = newAddressNumber
    }

    fun updateParticipantPostalCode(newPostalCode: String) {
        this.participant.postalCode = newPostalCode
    }

    fun updateParticipantComplement(newComplement: String) {
        this.participant.complement = newComplement
    }

    //Company
    fun updateParticipantCompanyName(newCompanyName: String) {
        this.participant.companyName = newCompanyName
    }

    fun updateParticipantCompanyNameForBadge(newCompanyNameForBadge: String) {
        this.participant.companyNameForBadge = newCompanyNameForBadge
    }

    fun updateParticipantNameForBadge(newNameForBadge: String) {
        this.participant.nameForBadge = newNameForBadge
    }

    fun updateParticipantCompanyOfficeForBadge(newCompanyOfficeForBadge: String) {
        this.participant.companyOfficeForBadge = newCompanyOfficeForBadge
    }

    //Products
    fun updateParticipantGadgets(newGadgets: Boolean) {
        this.participant.gadgets = newGadgets
    }

    fun updateParticipantCinemaCapitation(newCinemaCapitation: Boolean) {
        this.participant.cinemaCapitation = newCinemaCapitation
    }

    fun updateParticipantSetDesign(newSetDesign: Boolean) {
        this.participant.setDesign = newSetDesign
    }

    fun updateParticipantRemoteContribution(newRemoteContribution: Boolean) {
        this.participant.remoteContribution = newRemoteContribution
    }

    fun updateParticipantCameraAndLens(newCameraAndLens: Boolean) {
        this.participant.cameraAndLens = newCameraAndLens
    }

    fun updateParticipantLightsAndSupports(newLightsAndSupports: Boolean) {
        this.participant.lightsAndSupports = newLightsAndSupports
    }

    fun updateParticipantMicrophone(newMicrophone: Boolean) {
        this.participant.microphone = newMicrophone
    }

    fun updateParticipantMobileProduction(newMobileProduction: Boolean) {
        this.participant.mobileProduction = newMobileProduction
    }

    fun updateParticipantCinemaProduction(newCinemaProduction: Boolean) {
        this.participant.cinemaProduction = newCinemaProduction
    }

    fun updateParticipantWorkflowSolutions(newWorkflowSolutions: Boolean) {
        this.participant.workflowSolutions = newWorkflowSolutions
    }

    fun updateParticipantDigitalSolutions(newDigitalSolutions: Boolean) {
        this.participant.digitalSolutions = newDigitalSolutions
    }

    fun updateParticipantDisplaysBusinessStakeholders(newDisplaysBusinessStakeholders: Boolean) {
        this.participant.displaysBusinessStakeholders = newDisplaysBusinessStakeholders
    }

    fun updateParticipantDisplaysEvents(newDisplaysEvents: Boolean) {
        this.participant.displaysEvents = newDisplaysEvents
    }

    fun updateParticipantDisplaysTablets(newDisplaysTablets: Boolean) {
        this.participant.displaysTablets = newDisplaysTablets
    }

    fun updateParticipantPartnerEmails(newPartnerEmails: Boolean) {
        this.participant.partnerEmails = newPartnerEmails
    }

    fun updateParticipantEventChangesEmails(newEventChangesEmails: Boolean) {
        this.participant.eventChangesEmails = newEventChangesEmails
    }

    fun updateParticipantSetNewsletter(newSetNewsletter: Boolean) {
        this.participant.setNewsletter = newSetNewsletter
    }

    fun updateCurrentStep(newStep: NewRegisterStep.RegisterStep) {
        val step = currentStepLiveData.value
        step?.currentStep = newStep
        currentStepLiveData.postValue(step)
    }

    fun saveNewParticipant() {
        savingParticipantLiveData.postValue(SaveParticipantEvent.SAVING)
        val database = DbHelper.getDatabase(context)

        val participants = database.participantDao().getAll()

        if (participants?.isNotEmpty() == true) {
            participants.forEach {
                if (it.cpf == participant.cpf) {
                    savingParticipantLiveData.postValue(SaveParticipantEvent.ALREADY_REGISTERED)
                    return
                }
            }
        }

        database
            .participantDao()
            .insert(participant)

        val dates = database.chartDataDao().getAll()

        if (dates?.isNotEmpty() == true) {
            val chartDates = dates[0].registerDates

            chartDates.toMutableList().add(Calendar.getInstance().time)

            database
                .chartDataDao()
                .insert(ChartData(1, chartDates))
        } else {
            database
                .chartDataDao()
                .insert(ChartData(1, listOf(Calendar.getInstance().time)))
        }

        savingParticipantLiveData.postValue(SaveParticipantEvent.SAVED)
    }

}