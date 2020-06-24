package com.phis.apipractice_20200615

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    val mContext = this

    abstract fun setValues()
    abstract fun setupEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            setCustomActionBar()
        }
    }

    fun setCustomActionBar() {
        //상단배너 커스텀

        //커스텀 기능 활성화
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        //기본 배경색 제거
        supportActionBar?.setBackgroundDrawable(null)

        //커스트텀 액션바 영역 확장 > 뒷단 배경 삭제
        val parent = supportActionBar?.customView?.parent as androidx.appcompat.widget.Toolbar

        //실제 여백 제거
        parent.setContentInsetsAbsolute(0, 0)

    }
}