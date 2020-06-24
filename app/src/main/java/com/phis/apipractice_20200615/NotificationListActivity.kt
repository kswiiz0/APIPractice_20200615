package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NotificationListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)

        setValues()
        setupEvent()


    }

    override fun setValues() {
        activityNotificationBtn.visibility = View.GONE

    }

    override fun setupEvent() {
    }
}