package com.dragon.dragoncredenciamento.extension

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.goToActivity(newActivity: Class<*>) {
    val intent = Intent(this, newActivity)
    startActivity(intent)
}

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}