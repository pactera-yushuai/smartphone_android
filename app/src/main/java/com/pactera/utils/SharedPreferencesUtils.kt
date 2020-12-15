package com.pactera.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 *
 */
object SharedPreferencesUtils {

    private val TAG = "TAG"
    private var sharedPreferences: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    fun init(context: Context, name: String) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        edit = sharedPreferences!!.edit()
    }

    fun remove(key: String) {
        edit!!.remove(key)
        edit!!.commit()
    }

    fun getstring(key: String, defalut: String): String {
        return if (sharedPreferences != null) {
//            sharedPreferences!!.getString(key, defalut)
            ""
        } else ""
    }

    fun getInteger(key: String, defalut: Int): Int {
        return if (sharedPreferences != null) {
            sharedPreferences!!.getInt(key, defalut)
        } else 0
    }

    fun getBoolean(key: String, defalut: Boolean): Boolean {
        return if (sharedPreferences != null) {
            sharedPreferences!!.getBoolean(key, defalut)
        } else false
    }

    fun putsetstring(key: String, value: String) {
        edit!!.putString(key, value)
        val commit = edit!!.commit()
        Log.d(TAG, "putsetstring: $key $commit")

    }

    fun putsetinteger(key: String, value: Int) {
        edit!!.putInt(key, value)
        edit!!.commit()

    }

    fun putboolean(key: String, value: Boolean) {
        edit!!.putBoolean(key, value)
        edit!!.commit()

    }

    fun removeAll(){
        remove("username")
        remove("password")
        remove("user_id")
        remove("token")
        remove("role")
        remove("phone_no")
    }

}
