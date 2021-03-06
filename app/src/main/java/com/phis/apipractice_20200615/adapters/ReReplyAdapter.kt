package com.phis.apipractice_20200615.adapters

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.ViewReplyDetailActivity
import com.phis.apipractice_20200615.datas.TopicReply
import com.phis.apipractice_20200615.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat


class ReReplyAdapter(val mContext: Context, val resId: Int, val mList: List<TopicReply>) :
    ArrayAdapter<TopicReply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if ( tempRow== null){
            tempRow = inf.inflate(R.layout.listview_item_topic_re_replay, null)
        }

        val row = tempRow!!
        val nicknameTxt = row.findViewById<TextView>(R.id.nickNameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val dislikeBtn = row.findViewById<Button>(R.id.dislikeBtn)

        val selectedSideTitleTxt = row.findViewById<TextView>(R.id.selectedSideTitleTxt)

        val item = mList[position]
        nicknameTxt.text = item.writer.nickname
        contentTxt.text = item.content


        likeBtn.text = "좋아요 : ${item.likeCount}개"
        dislikeBtn.text = "싫어요 : ${item.dislikeCount}개"

        if(item.isMyLike){
            likeBtn.setBackgroundResource(R.drawable.red_border_box)
            dislikeBtn.setBackgroundResource(R.drawable.gray_border_box)

            likeBtn.setTextColor(mContext.resources.getColor(R.color.red))
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.gray))

        } else if ( item.isMyDislike){
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeBtn.setBackgroundResource(R.drawable.blue_border_box)

            likeBtn.setTextColor(mContext.resources.getColor(R.color.gray))
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.blue))

        } else{
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeBtn.setBackgroundResource(R.drawable.gray_border_box)

            likeBtn.setTextColor(mContext.resources.getColor(R.color.gray))
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.gray))
        }

        selectedSideTitleTxt.text = "(${item.selectedSide.title})"

        val likeOrDisLikeEvent = View.OnClickListener {
            val isLike = it.id == R.id.likeBtn
            ServerUtil.postRequestTopicReplyLike(
                mContext,
                item.id,
                isLike,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        val dataObj = json.getJSONObject("data")
                        val reply = dataObj.getJSONObject("reply")

                        item.likeCount = reply.getInt("like_count")
                        item.dislikeCount = reply.getInt("dislike_count")

                        item.isMyLike = reply.getBoolean("my_like")
                        item.isMyDislike = reply.getBoolean("my_dislike")

                        Handler(Looper.getMainLooper()).post({
                            //어댑터 내부에서는 직접 새로고침 가능
                            notifyDataSetChanged()
                        })
                    }
                })
        }

        likeBtn.setOnClickListener(likeOrDisLikeEvent)
        dislikeBtn.setOnClickListener(likeOrDisLikeEvent)

        return row
    }


}