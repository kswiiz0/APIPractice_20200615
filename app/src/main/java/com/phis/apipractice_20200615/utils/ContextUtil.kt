package com.phis.apipractice_20200615.utils

import android.content.Context

class ContextUtil {

    companion object{

        val prefName ="APIPracticePref"

        val USER_TOKEN = "USER_TOKEN"
        val AUTO_LOGIN = "AUTO_LOGIN"

        fun setUserToken(context: Context, token:String){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        fun getUserToken(context : Context) : String{
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }

        fun setAutoLogin(context: Context, autoLogin:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply()
        }

        fun isAutoLogin(context: Context) : Boolean{
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }



    }
}