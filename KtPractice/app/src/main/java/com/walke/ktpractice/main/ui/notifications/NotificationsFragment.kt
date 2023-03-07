package com.walke.ktpractice.main.ui.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.walke.ktpractice.R
import com.walke.ktpractice.demo.vp_fragment.Main2Activity
import com.walke.ktpractice.with_flutter.FlutterTestActivity
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    private val acts = ArrayList<Class<*>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = context?.let { Adapter00(it, acts) }

    }

    private fun initData() {
        acts.add(FlutterTestActivity::class.java)
        acts.add(Main2Activity::class.java)
        acts.add(String::class.java)

    }


}



private class Adapter00(
    var mContext: Context,
    var acts: ArrayList<Class<*>>
) : RecyclerView.Adapter<Adapter00.VHolder>() {


    override fun getItemCount() = acts.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        var inflate = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null)
        return VHolder(inflate)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.textView.text=acts[position].simpleName
        holder.itemView.setOnClickListener {
            jump(acts[position])
        }
    }

    private fun jump(clazz: Class<*>) {
        var intent = Intent(mContext, clazz)
        intent.apply {
            intent.putExtra("title",clazz.simpleName)
        }
        mContext.startActivity(intent)
    }

    private class VHolder(item: View) : RecyclerView.ViewHolder(item) {
        // 使用关键字 lateinit，它会告诉编译器，晚些时候对该变量进行初始化，在代码中就不用进行判空保护
        var textView: TextView = item.findViewById(android.R.id.text1)

    }

}



