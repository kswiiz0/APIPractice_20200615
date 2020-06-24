package com.phis.apipractice_20200615


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.phis.apipractice_20200615.adapters.TopicAdapter
import com.phis.apipractice_20200615.datas.Topic
import com.phis.apipractice_20200615.utils.ContextUtil
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val topicList = ArrayList<Topic>()
    lateinit var myViewAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
        setupEvent()

    }

    override fun setValues() {
//        setTitle("토론목록")

        myViewAdapter = TopicAdapter(mContext, R.layout.topic_list_item, topicList)
        topicListView.adapter = myViewAdapter

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
                    topicList.add(Topic.getTopicFromJson(topic))
                    runOnUiThread {
                        myViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })


    }

    override fun setupEvent() {

        logoutBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("로그아웃을 진행합니다.")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                ContextUtil.setUserToken(mContext, "")

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()


            })
            alert.setNegativeButton("취소", null)
            alert.show()


        }


        topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopic = topicList[position]

            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic_id", clickedTopic.id)
            startActivity(myIntent)
        }


    }



}