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
import com.phis.apipractice_20200615.datas.Notification
import com.phis.apipractice_20200615.datas.Topic
import kotlinx.android.synthetic.main.topic_list_item.view.*

class NotificationAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<Notification>
) : ArrayAdapter<Notification>(mContext, resId, mList) {

    val inf = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.listview_item_nofitication, null)
        }

        val row = tempRow!!

        val data = mList[position]


        return row
    }


}