package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.phis.apipractice_20200615.BaseActivity
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.datas.TopicReply
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_reply_detail.*
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    var mReplyId = -1
    lateinit var mReply: TopicReply

    val mReReplyList = ArrayList<TopicReply>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)

        setValues()
        setupEvent()
    }

    override fun setValues() {

        val replyId = intent.getIntExtra("replyId", -1)
        mReplyId = replyId

        ServerUtil.getRequestViewReplyDetail(
            mContext,
            replyId,
            object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val data = json.getJSONObject("data")
                    val reply = data.getJSONObject("reply")
                    mReply = TopicReply.getTopicReplyFromJson(reply)




                    runOnUiThread {
                        contentTxt.text = mReply.content
                        nickNameTxt.text = mReply.writer.nickname
                        selectedSideTitleTxt.text = "(${mReply.selectedSide.title})"

                    }

                }


            })


    }

    override fun setupEvent() {

        postReplyBtn.setOnClickListener {
            val reply = replyContentEdt.text.toString()
            if ( reply.length < 5){
                Toast.makeText(mContext,"5자리이상 입력하세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ServerUtil.postRequestReReply(mContext,mReplyId,reply,object :ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                }

            })




        }

    }
}