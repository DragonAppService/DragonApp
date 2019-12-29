package com.dragon.dragoncredenciamento.flow.base

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dragon.dragoncredenciamento.R
import com.dragon.dragoncredenciamento.database.DbHelper

/**
 * @Author Guilherme
 * @Date 15/06/2019
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        reloadBackground()
    }

    fun reloadBackground() {
        val newBackground =
            getDrawableFromDatabase() ?: ContextCompat.getDrawable(this, R.drawable.img_bg)

        getBackgroundImageView().background = newBackground
    }

    private fun getDrawableFromDatabase(): Drawable? {
        val bgFilePath = DbHelper.getDatabase(this).backgroundDao().getBackground()?.path

        bgFilePath?.let {
            return Drawable.createFromPath(it)
        }

        return null
    }

    abstract fun getBackgroundImageView(): View

}