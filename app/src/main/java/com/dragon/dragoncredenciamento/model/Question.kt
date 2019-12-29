package com.dragon.dragoncredenciamento.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */
@Entity
data class Question(
    @PrimaryKey(autoGenerate = true) var id: Long = 1,
    var isActive: Boolean = true
) : Serializable