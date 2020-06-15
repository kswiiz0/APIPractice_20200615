package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    val mContext = this

    abstract fun setValues()
    abstract fun setupEvent()
}