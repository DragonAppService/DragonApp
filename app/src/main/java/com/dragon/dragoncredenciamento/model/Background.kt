package com.dragon.dragoncredenciamento.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @Author Guilherme
 * @Date 15/06/2019
 */
@Entity
data class Background(

    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var path: String

) : Serializable