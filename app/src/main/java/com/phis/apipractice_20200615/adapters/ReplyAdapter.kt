package com.phis.apipractice_20200615.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.datas.TopicReply
import com.phis.apipractice_20200615.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ReplyAdapter(val mContext: Context, val resId: Int, val mList: List<TopicReply>) :
    ArrayAdapter<TopicReply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        tempRow?.let {}.let {
            tempRow = inf.inflate(R.layout.listview_item_topic_replay, null)
        }

        val row = tempRow!!
        val nicknameTxt = row.findViewById<TextView>(R.id.nickNameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val createTimeTxt = row.findViewById<TextView>(R.id.createTimeTxt)

        val replyBtn = row.findViewById<Button>(R.id.replyBtn)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val dislikeBtn = row.findViewById<Button>(R.id.dislikeBtn)

        val selectedSideTitleTxt = row.findViewById<TextView>(R.id.selectedSideTitleTxt)

        val item = mList[position]
        nicknameTxt.text = item.writer.nickname
        contentTxt.text = item.content

        val sdf = SimpleDateFormat("M월 d일 a h시 m분")

        createTimeTxt.text = sdf.format(item.createAt.time)

        replyBtn.text = "답글 : ${item.replyCount}개"
        likeBtn.text = "좋아요 : ${item.likeCount}개"
        dislikeBtn.text = "싫어요 : ${item.dislikeCount}개"

        selectedSideTitleTxt.text = "(${item.selectedSide.title})"

        val likeOrDisLikeEvent = View.OnClickListener {
            val isLike = it.id == R.id.likeBtn
            ServerUtil.postRequestTopicReplyLike(mContext, item.id, isLike, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                }


            })
        }

        likeBtn.setOnClickListener(likeOrDisLikeEvent)
        dislikeBtn.setOnClickListener(likeOrDisLikeEvent)




        return row
    }


}