package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phis.apipractice_20200615.adapters.MyViewAdapter
import com.phis.apipractice_20200615.datas.Topic
import com.phis.apipractice_20200615.datas.User
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val topicList = ArrayList<Topic>()
    lateinit var myViewAdapter: MyViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
        setupEvent()
    }

    override fun setValues() {
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
/*
                "code":200,
                "message":"내 정보 조회",
                "data":{
                    "user":{
                    "id":93,
                    "email":"1q1q1q@gmail.com",
                    "nick_name":"1q1q1q",
                    "created_at":"2020-06-16 11:13:44",
                    "updated_at":"2020-06-16 11:13:44"
                    }
                }
                val user = json.getJSONObject("data").getJSONObject("user")
                val loginUser = User.getUserFromJson(user)
                runOnUiThread {
                    nickNameTxt.text = loginUser.nickname
                    emailTxt.text = loginUser.email

                }
*/
                val data = json.getJSONObject("data")
                val topics = data.getJSONArray("topics")
                for (i in 0..topics.length() - 1) {
                    val topic = topics.getJSONObject(i)

//                    topicList.add(Topic.)
                }


            }

        })


    }

    override fun setupEvent() {
    }


}