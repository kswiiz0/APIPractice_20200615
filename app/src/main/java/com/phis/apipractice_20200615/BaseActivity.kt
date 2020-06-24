package com.phis.apipractice_20200615

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {
    val mContext = this

    lateinit var activityTitleTxt : TextView

    abstract fun setValues()
    abstract fun setupEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            setCustomActionBar()
        }
    }

    //각화면의 setTitle 기본기능을 > 커스텀 액션바에게 반영하도록 오버라이딩
    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)

        supportActionBar!!.let{
            activityTitleTxt.text = title
        }
    }


    fun setCustomActionBar() {
        //상단배너 커스텀

        //커스텀 기능 활성화
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)

        //기본 배경색 제거
        supportActionBar!!.setBackgroundDrawable(null)

        //커스트텀 액션바 영역 확장 > 뒷단 배경 삭제
        val parent = supportActionBar!!.customView.parent as Toolbar

        //실제 여백 제거
        parent.setContentInsetsAbsolute(0, 0)
        
        
        //XML에 있는 뷰들을 사용할수 있도록 연결
        activityTitleTxt = supportActionBar!!.customView.findViewById(R.id.activityTitleTxt)
        

    }
}