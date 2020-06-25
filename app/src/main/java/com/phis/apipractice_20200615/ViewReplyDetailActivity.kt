package com.phis.apipractice_20200615

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.phis.apipractice_20200615.adapters.ReReplyAdapter
import com.phis.apipractice_20200615.datas.TopicReply
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import org.json.JSONObject


class ViewReplyDetailActivity : BaseActivity() {

    var mReplyId = -1
    lateinit var mReply: TopicReply

    val mReReplyList = ArrayList<TopicReply>()
    lateinit var reReplyAdapter: ReReplyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        setValues()
        setupEvent()
    }

    override fun setValues() {

        setTitle("의견상세화면")

        val replyId = intent.getIntExtra("replyId", -1)
        mReplyId = replyId

        reReplyAdapter = ReReplyAdapter(
            mContext,
            R.layout.listview_item_topic_re_replay,
            mReReplyList
        )
        reReplyListView.adapter = reReplyAdapter


    }

    override fun onResume() {
        super.onResume()
        getReplyFromServer()
    }

    fun getReplyFromServer() {

        ServerUtil.getRequestViewReplyDetail(
            mContext,
            mReplyId,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val data = json.getJSONObject("data")
                    val reply = data.getJSONObject("reply")
                    mReply = TopicReply.getTopicReplyFromJson(reply)


                    runOnUiThread {

                        mReReplyList.clear()

                        val replies = reply.getJSONArray("replies")
                        for (i in 0..replies.length() - 1) {
                            mReReplyList.add(
                                TopicReply.getTopicReplyFromJson(
                                    replies.getJSONObject(
                                        i
                                    )
                                )
                            )
                            reReplyAdapter.notifyDataSetChanged()
                            reReplyListView.smoothScrollToPosition(mReReplyList.size - 1)
                        }

                        contentTxt.text = mReply.content
                        nickNameTxt.text = mReply.writer.nickname
                        selectedSideTitleTxt.text = "(${mReply.selectedSide.title})"


                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(replyContentEdt.windowToken, 0)


                    }


                }


            })


    }

    override fun setupEvent() {




        postReplyBtn.setOnClickListener {
            val reply = replyContentEdt.text.toString()
            if (reply.length < 5) {
                Toast.makeText(mContext, "5자리이상 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReReply(
                mContext,
                mReplyId,
                reply,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        runOnUiThread {
                            replyContentEdt.text.clear()
                            Toast.makeText(mContext, "답글을 등록했습니다.", Toast.LENGTH_SHORT).show()
                            getReplyFromServer()

                        }
                    }

                })


        }

    }
}