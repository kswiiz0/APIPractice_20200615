package com.phis.apipractice_20200615.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.phis.apipractice_20200615.datas.Topic

class MyViewAdapter(context: Context, resId: Int, list: ArrayList<Topic>) :
    ArrayAdapter<Topic>(context, resId, list) {

    val inf = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {




        return super.getView(position, convertView, parent)
    }


}