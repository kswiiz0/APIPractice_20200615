package com.phis.apipractice_20200615.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.datas.TopicReply

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

        val item = mList[position]
        nicknameTxt.text = item.user.nickname
        contentTxt.text = item.content
        createTimeTxt.text = "아직!!"

        return row
    }


}