package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phis.apipractice_20200615.BaseActivity
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    var mReplyId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        setValues()
        setupEvent()
    }

    override fun setValues() {

        val replyId = intent.getIntExtra("replyId", -1)

        ServerUtil.getRequestReplyDetail(
            mContext,
            replyId,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }


            })


    }

    override fun setupEvent() {
    }
}