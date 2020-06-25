package com.phis.apipractice_20200615

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.phis.apipractice_20200615.utils.ServerUtil
import org.json.JSONObject

abstract class BaseActivity : AppCompatActivity() {
    val mContext = this

    lateinit var activityTitleTxt: TextView
    lateinit var activityImageView: ImageView
    lateinit var activityNotificationBtn: ImageView
    lateinit var activity_notiFrameLayout: FrameLayout
    lateinit var activityNotificationCntTxt: TextView


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

        supportActionBar!!.let {
            activityTitleTxt.text = title

            activityImageView.visibility = View.GONE
            activityTitleTxt.visibility = View.VISIBLE
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
        activityImageView = supportActionBar!!.customView.findViewById(R.id.activityImageView)
        activityNotificationBtn =
            supportActionBar!!.customView.findViewById(R.id.activityNotificationBtn)

        activity_notiFrameLayout =
            supportActionBar!!.customView.findViewById(R.id.activity_notiFrameLayout)


        activityNotificationCntTxt =
            supportActionBar!!.customView.findViewById(R.id.activityNotificationCntTxt)

        //알림버튼이 눌리면 어느화면에서도 알림화면으로 이동

        activityNotificationBtn.setOnClickListener {
            val myIntent = Intent(mContext, NotificationListActivity::class.java)
            startActivity(myIntent)

        }

    }

    //모든 화면에서 알림 갯수를 받아와서 표시
    override fun onResume() {
        super.onResume()


        supportActionBar?.let {
            ServerUtil.getRequestNotification(mContext, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val data = json.getJSONObject("data")
                    val unreadNotiCount = data.getInt("unread_noty_count")

                    runOnUiThread {
                        if (unreadNotiCount > 0) {
                            activityNotificationCntTxt.visibility = View.VISIBLE
                            activityNotificationCntTxt.text = unreadNotiCount.toString()
                        } else {
                            activityNotificationCntTxt.visibility = View.GONE
                        }

                    }
                }


            })

        }
    }


}