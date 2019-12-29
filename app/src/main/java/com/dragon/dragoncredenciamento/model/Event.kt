package com.dragon.dragoncredenciamento.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Event(

    @PrimaryKey var eventName: String

) : Serializable