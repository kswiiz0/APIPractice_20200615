package com.phis.apipractice_20200615.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.phis.apipractice_20200615.R
import com.phis.apipractice_20200615.datas.Topic
import kotlinx.android.synthetic.main.topic_list_item.view.*

class TopicAdapter(
    val mContext:Context,
    val resId:Int,
    val mList:List<Topic>) : ArrayAdapter<Topic>(mContext, resId, mList) {

    val inf = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        tempRow?.let {}.let {
            tempRow = inf.inflate(R.layout.topic_list_item, null)

        }

        val row = tempRow!!

        val topicTitletxt = row.findViewById<TextView>(R.id.topicTitletxt)
        val topicImg = row.findViewById<ImageView>(R.id.topicImg)

        val data = mList[position]

        topicTitletxt.text = data.title
        Glide.with(mContext).load(data.imageUrl).into(topicImg)



        return row
    }


}