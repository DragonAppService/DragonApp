package com.dragon.dragoncredenciamento.tools

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * @Author Guilherme
 * @Date 30/06/2019
 */
class UiTools {

    companion object {
        fun loadFragmentNoBackstack(
            fm: FragmentManager, @IdRes frame: Int,
            fragment: Fragment,
            tag: String
        ) {
            if (fm.findFragmentByTag(tag) != null) {
                fm.popBackStack()
            }

            fm.beginTransaction()
                .replace(frame, fragment, tag)
                .commit()
        }
    }

}