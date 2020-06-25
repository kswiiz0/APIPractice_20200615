package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.phis.apipractice_20200615.adapters.NotificationAdapter
import com.phis.apipractice_20200615.datas.Notification
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_notification_list.*
import org.json.JSONObject

class NotificationListActivity : BaseActivity() {

    val mNotiList = ArrayList<Notification>()
    lateinit var notiAdapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)

        setValues()
        setupEvent()


    }

    override fun onResume() {
        super.onResume()
        getNotificationFromServer()
    }

    fun getNotificationFromServer() {

        ServerUtil.getRequestNotification(
            mContext,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val data = json.getJSONObject("data")
                    val notis = data.getJSONArray("notifications")
                    //기존Noti 제거
                    mNotiList.clear()
                    for (i in 0..notis.length() - 1) {
                        mNotiList.add(Notification.getNotificationFromJson(notis.getJSONObject(i)))
                    }

                    //알림을 받았으면 최신 알림의 id를 서버로 전파
                    //여기까지 알림을 읽었다고 서버에 알려줌. (unread_noti_count를 0으로)
                    ServerUtil.postRequestNotification(
                        mContext,
                        mNotiList[0].id,
                        null
                    )


                    runOnUiThread {
                        notiAdapter.notifyDataSetChanged()
                    }


                }
            })
    }


    override fun setValues() {
        activity_notiFrameLayout.visibility = View.GONE

        notiAdapter = NotificationAdapter(mContext, R.layout.listview_item_nofitication, mNotiList)
        notiListView.adapter = notiAdapter


    }

    override fun setupEvent() {
    }
}