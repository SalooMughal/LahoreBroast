package com.lb.lahorebroast.utilities

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.lb.lahorebroast.model.RegisterationResponse

//import com.google.gson.Gson;
class TinyDB(context: Context) {
    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getString(key: String?): String {
        if(preferences.contains(key)) {
            return preferences.getString(key, "")!!
        }
        return ""
    }

    fun getObject(key: String?): RegisterationResponse.Data? {
        val json = getString(key)
        if(json.isNotEmpty()) {
            return Gson().fromJson(json, RegisterationResponse.Data::class.java)
        }
        return RegisterationResponse.Data()
    }

    fun putString(key: String?, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    fun putObject(key: String?, obj: RegisterationResponse.Data?) {
        val gson = Gson()
        if(null != obj) {
            CacheManager.instance.setisLogin(true)
            CacheManager.instance.setUserData(obj!!)
            putString(key, gson.toJson(obj))
        }
    }

    fun remove(key: String?) {
        preferences.edit().remove(key).apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
        CacheManager.instance.setisLogin(false)
    }


}