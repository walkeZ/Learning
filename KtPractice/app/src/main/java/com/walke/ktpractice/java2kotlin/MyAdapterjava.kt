package com.walke.ktpractice.java2kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.walke.ktpractice.R

/**
 * author Walke - 2020/9/22 9:19 AM
 * email 1126648815@qq.ocm
 * dec
 */
internal class MyAdapterjava(private val mList: List<String>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var cv = convertView
        val holder: MyViewHolder
        if (cv == null) {
            cv = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_string, parent, false)
            holder = MyViewHolder(cv)
            cv.tag = holder
        } else {
            holder =
                cv.tag as MyViewHolder
            holder.tv.text = mList[position]
        }
        return cv
    }

    internal inner class MyViewHolder(root: View) {
        val tv: TextView

        init {
            tv = root.findViewById(R.id.ls_tvContent)
        }
    }

}