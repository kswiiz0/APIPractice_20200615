package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.phis.apipractice_20200615.adapters.ReplyAdapter
import com.phis.apipractice_20200615.datas.Topic
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    var mTopicId = -1
    lateinit var mTopic: Topic
    lateinit var replyAdapter: ReplyAdapter


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

//        runOnUiThread {
//            replyAdapter = ReplyAdapter(
//                mContext,
//                R.layout.listview_item_topic_replay,
//                mTopic.replyList
//            )
//            replayListView.adapter = replyAdapter
//
//        }


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


                        if (mTopic.mySelectedSideIndex == -1) {
                            firstSideVoteBtn.text = "투표하기"
                            secondSideVoteBtn.text = "투표하기"
                        } else if (mTopic.mySelectedSideIndex == 0) {
                            firstSideVoteBtn.text = "취소하기"
                            secondSideVoteBtn.text = "갈아타기"
                        } else {
                            firstSideVoteBtn.text = "갈아타기"
                            secondSideVoteBtn.text = "취소하기"
                        }

                        replyAdapter = ReplyAdapter(
                            mContext,
                            R.layout.listview_item_topic_replay,
                            mTopic.replyList
                        )
                        replayListView.adapter = replyAdapter
                    }


                }
            })
    }


    override fun setupEvent() {

        firstSideVoteBtn.setOnClickListener {

            val sideId = mTopic.sideList[0].id
            ServerUtil.postRequestTopicVote(
                mContext,
                sideId,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        getTopicDetailFromServer()
                    }
                })


        }


        secondSideVoteBtn.setOnClickListener {

            val sideId = mTopic.sideList[1].id
            ServerUtil.postRequestTopicVote(
                mContext,
                sideId,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        getTopicDetailFromServer()
                    }
                })

        }

    }

}