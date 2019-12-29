package com.dragon.dragoncredenciamento.extension

import com.google.gson.Gson

/**
 * @Author Guilherme
 * @Date 16/06/2019
 */

inline fun <reified T> Gson.deserializeList(
    json: String?,
    clazz: Class<Array<T>>
): List<T>? {
    return json?.let {
        return try {
            fromJson(json, clazz).toList()
        } catch (e: Exception) {
            null
        }
    }
}