package com.dragon.dragoncredenciamento.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dragon.dragoncredenciamento.model.extension.DateConverter
import java.io.Serializable
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Participant(

    //Personal Info
    @PrimaryKey var cpf: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String? = null,
    @ColumnInfo(name = "gender") var gender: String? = null,
    @ColumnInfo(name = "birthdate") var birthdate: Date? = null,
    @ColumnInfo(name = "email") var email: String? = null,
    @ColumnInfo(name = "copyEmail") var copyEmail: String? = null,
    @ColumnInfo(name = "phoneCode") var phoneCode: String? = null,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String? = null,
    @ColumnInfo(name = "branch") var branch: String? = null,
    @ColumnInfo(name = "mobileCode") var mobileCode: String? = null,
    @ColumnInfo(name = "mobileNumber") var mobileNumber: String? = null,

    //Address
    @ColumnInfo(name = "country") var country: String? = null,
    @ColumnInfo(name = "state") var state: String? = null,
    @ColumnInfo(name = "city") var city: String? = null,
    @ColumnInfo(name = "neighbourhood") var neighbourhood: String? = null,
    @ColumnInfo(name = "address") var address: String? = null,
    @ColumnInfo(name = "addressNumber") var addressNumber: String? = null,
    @ColumnInfo(name = "postalCode") var postalCode: String? = null,
    @ColumnInfo(name = "complement") var complement: String? = null,

    //Company
    @ColumnInfo(name = "companyName") var companyName: String? = null,
    @ColumnInfo(name = "companyNameForBadge") var companyNameForBadge: String? = null,
    @ColumnInfo(name = "nameForBadge") var nameForBadge: String? = null,
    @ColumnInfo(name = "companyOfficeForBadgeEditText") var companyOfficeForBadge: String? = null,

    //Products
    @ColumnInfo(name = "gadgets") var gadgets: Boolean? = null,
    @ColumnInfo(name = "cinemaCapitation") var cinemaCapitation: Boolean? = null,
    @ColumnInfo(name = "setDesign") var setDesign: Boolean? = null,
    @ColumnInfo(name = "remoteContribution") var remoteContribution: Boolean? = null,
    @ColumnInfo(name = "cameraAndLens") var cameraAndLens: Boolean? = null,
    @ColumnInfo(name = "lightsAndSupports") var lightsAndSupports: Boolean? = null,
    @ColumnInfo(name = "microphone") var microphone: Boolean? = null,
    @ColumnInfo(name = "mobileProduction") var mobileProduction: Boolean? = null,
    @ColumnInfo(name = "cinemaProduction") var cinemaProduction: Boolean? = null,
    @ColumnInfo(name = "workflowSolutions") var workflowSolutions: Boolean? = null,
    @ColumnInfo(name = "digitalSolutions") var digitalSolutions: Boolean? = null,
    @ColumnInfo(name = "displaysBusinessStakeholders") var displaysBusinessStakeholders: Boolean? = null,
    @ColumnInfo(name = "displaysEvents") var displaysEvents: Boolean? = null,
    @ColumnInfo(name = "displaysTablets") var displaysTablets: Boolean? = null,
    @ColumnInfo(name = "partnerEmails") var partnerEmails: Boolean? = null,
    @ColumnInfo(name = "eventChangesEmails") var eventChangesEmails: Boolean? = null,
    @ColumnInfo(name = "setNewsletter") var setNewsletter: Boolean? = null

) : Serializable