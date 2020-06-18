package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.phis.apipractice_20200615.datas.Topic
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    var mTopicId = -1
    lateinit var mTopic: Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setValues()
        setupEvent()

    }

    override fun setValues() {

        mTopicId = intent.getIntExtra("topic_id", -1)
        if (mTopicId == -1) {
            Toast.makeText(mContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        getTopicDetailFromServer()

    }

    fun getTopicDetailFromServer() {
        ServerUtil.getRequestTopicDetail(mContext, mTopicId,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val data = json.getJSONObject("data")
                    val topic = data.getJSONObject("topic")
                    val topicObj = Topic.getTopicFromJson(topic)

                    mTopic = topicObj

                    runOnUiThread {
                        topicTitleTxt.text = mTopic.title
                        Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

                        firstSideTitleTxt.text = mTopic.sideList[0].title
                        firstSideVoteCountTxt.text = "${mTopic.sideList[0].voteCount.toString()}표"


                        secondSideTitleTxt.text = mTopic.sideList[1].title
                        secondSideVoteCountTxt.text = "${mTopic.sideList[1].voteCount.toString()}표"


                    }


                }
            })
    }


    override fun setupEvent() {
    }

}