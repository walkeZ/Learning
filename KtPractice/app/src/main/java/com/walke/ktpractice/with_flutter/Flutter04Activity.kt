package com.walke.ktpractice.with_flutter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.IntentTool
import com.walke.ktpractice.constant.toast
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.*
import kotlinx.android.synthetic.main.activity_flutter04.*
import java.lang.Exception
import java.nio.ByteBuffer
import kotlin.collections.MutableMap as MutableMap

/**
 * 与flutter交互方式2：EventChannel。
 * 参考：https://www.jianshu.com/p/b23174d06cf3
 */
class Flutter04Activity :AppCompatActivity() {


    private var dartExecutor: DartExecutor?=null
    private var eventChannel: EventChannel? = null
    private lateinit var flutterFragment: FlutterFragment

    companion object{
        private const val Flutter_EventChannel="walke_first_module_event_channel"
        private const val FLUTTER_TAG="Flutter04Activity"

        fun start(activity: AppCompatActivity,title:String){
            var intent = Intent(activity, Flutter04Activity::class.java)
            intent.putExtra(IntentTool.KEY_TITLE,title)
            activity.startActivityForResult(intent,IntentTool.REQUEST_CODE_DEFAULT)
            activity.overridePendingTransition(android.R.anim.fade_in,0)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter04)
        initContent()
    }

    private fun initContent() {
        flutter04_tvTitle.text=intent.getStringExtra(IntentTool.KEY_TITLE)
        var newEngineFragmentBuilder = FlutterFragment.withNewEngine()
        flutterFragment = newEngineFragmentBuilder.initialRoute("page03").build<FlutterFragment>()
        supportFragmentManager.beginTransaction()
            .add(R.id.flutter04_flContent,flutterFragment, FLUTTER_TAG)
            .commit()

       /* initFlutterChannel(newEngineFragmentBuilder)
        var provideFlutterEngine = flutterFragment.provideFlutterEngine(this)
        var flutterEngine = flutterFragment.flutterEngine
        // 会报错，查看源码可知，此时delegate为空，在源码内查找delegate，发现在FlutterFragment的onAttach中创建的。
        var dartExecutor = flutterEngine?.dartExecutor
        Log.i("Hui", "initContent: =====》")*/
    }

    override fun onStart() {
        super.onStart()
        initFlutterChannel(flutterFragment)
    }

    override fun onRestart() {
        super.onRestart()
        initFlutterChannel(flutterFragment)
    }

    private fun initFlutterChannel(flutterFragment:FlutterFragment) {
        if (eventChannel!=null) return
        dartExecutor = flutterFragment.flutterEngine?.dartExecutor
        eventChannel =
            EventChannel(dartExecutor, Flutter_EventChannel)
        // 设置监听
        eventChannel?.setStreamHandler(object : EventChannel.StreamHandler{
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                Log.i("Hui", "onListen: -----> $arguments   ---> ${events}")
                arguments.let {
                    var toString = it.toString()
                    var fromJson = Gson().fromJson(toString, ArrayList::class.java)
                    var mutableMap1 = fromJson[0] as MutableMap<String, String>
//                    var map2: MutableMap<String, String>? = null
//                    if (fromJson.size > 1) {
//                        map2 = fromJson[1] as MutableMap<String, String>
//                    }

                    var method = mutableMap1["method"]
                    var params = mutableMap1["params"] as MutableMap<String, String>? // 没？ 会报错，相对于可为空，即没有params字段
                    Log.i("Hui", "onListen: method:$method   params: ${params?.toString()}")
                    when(method){
                        "getDateFromNative"->events?.success(jsonData())
                        "nativeShare"->nativeShare(params,events)
                        else->{

                        }
                    }
                }
            }

            override fun onCancel(arguments: Any?) {
//                TODO("Not yet implemented")
                Log.i("Hui", "onCancel: ------------->  Not yet implemented")

            }
        })
    }

    private fun nativeShare(
        map2: MutableMap<String, String>?,
        events: EventChannel.EventSink?
    ) {
        if (map2==null)return
//        var pms = map2["params"] as MutableMap<String, Any>
        var title = map2["title"].toString()
        toast(title)
        flutter04_tvTitle.postDelayed({
            events?.success("$title  分享成功")
        },2000)
    }

    private fun jsonData(): String {
        var map = hashMapOf<String, Any>()
        map.put("name","walke")
        map.put("age",28)
        return Gson().toJson(map)
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.flutter04_btAddFlutterCount->{
                var methodCodec= StandardMethodCodec.INSTANCE as MethodCodec
                dartExecutor?.binaryMessenger?.send(
                    Flutter_EventChannel,
                    methodCodec.encodeMethodCall(MethodCall("addFlutterCount","你好我是原生")),
                    object :BinaryMessenger.BinaryReply{
                        override fun reply(reply: ByteBuffer?) {
                            try {
                                var decodeEnvelope = methodCodec.decodeEnvelope(reply)
                                Log.i("Hui", "reply: -----> ${decodeEnvelope.toString()}")
                            }catch (e:Exception){
                                Log.i("Hui", "reply: -----> failed")
                            }
                        }
                    }
                )
            }else->{

        }
        }
    }


}


