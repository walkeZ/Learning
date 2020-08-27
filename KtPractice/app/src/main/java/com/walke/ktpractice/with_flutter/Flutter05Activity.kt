package com.walke.ktpractice.with_flutter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.IntentTool
import com.walke.ktpractice.constant.key_flutter_engine3
import com.walke.ktpractice.constant.toast
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.android.TransparencyMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec
import kotlinx.android.synthetic.main.activity_flutter05.*
import kotlin.math.log

// Mac 格式化代码块 option + cmd +L
// Flutter交互3：BaseMasagge
class Flutter05Activity : AppCompatActivity() {

    private var basicMessageChannel: BasicMessageChannel<String>? = null

    companion object {
        // Mac 大小写切换快捷键：shift + cmd + U
        const val FLUTTER_CHANNEL_BASIC_MESSAGE = "walke_first_module_basic_msg_channel"
        const val FRAGMENT_TAG = "Flutter05Activity"
        fun start(activity: AppCompatActivity, title: String) {
            //  Flutter05Activity.javaClass 会报错： android.content.ActivityNotFoundException: Unable to find explicit activity class
            //   {com.walke.ktpractice/com.walke.ktpractice.with_flutter.Flutter05Activity$Companion};
            //   have you declared this activity in your AndroidManifest.xml?
            var intent = Intent(activity, Flutter05Activity::class.java)
            intent.putExtra(IntentTool.KEY_TITLE, title)
            activity.startActivityForResult(intent, 0)
//            activity.overridePendingTransition(R.anim.intent_enter, 0)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter05)
        initContent()
    }

    private fun initContent() {
        flutter05_tvTitle.text = intent.getStringExtra(IntentTool.KEY_TITLE)

//        var flutterEngine = FlutterEngine(this)
//        flutterEngine.navigationChannel.setInitialRoute("page03")
//        flutter05_flContent.attachToFlutterEngine(flutterEngine)
        var flutterFragment = FlutterFragment
            .withCachedEngine(key_flutter_engine3)
            .transparencyMode(TransparencyMode.opaque)// flutter默认绘制在最上层
            .build<FlutterFragment>()
        supportFragmentManager.beginTransaction()
            .add(R.id.flutter05_flContent,flutterFragment, FRAGMENT_TAG)
            .commit()

        var flutterEngine = FlutterEngineCache.getInstance().get(key_flutter_engine3)

        basicMessageChannel =
            flutterEngine?.dartExecutor?.let { BasicMessageChannel<String>(it, FLUTTER_CHANNEL_BASIC_MESSAGE,StringCodec.INSTANCE) }

        // BasicMessageChannel 主要是数据传输，根据传输数据不同而做不同响应，模式类似于我们平常的网络接口
        // 设置监听：
        basicMessageChannel?.setMessageHandler(object :BasicMessageChannel.MessageHandler<String>{
            override fun onMessage(message: String?, reply: BasicMessageChannel.Reply<String>) {
                Log.i("Hui", "onMessage: -----> $message   replay: $reply")
                toast("$message");
                var rsp="原生默认"
                when(message){
                    "getDataFromNative"->{
                        rsp="我是原生的data"
                        reply.reply( rsp)
                    }
                    else->{
                        var fromJson = Gson().fromJson(message, Map::class.java)
                        var method = fromJson.get("method")
                        var params = fromJson.get("params")
                        Log.i("Hui", "onMessage: else -----> mtd: $method  ; $params")
                        if ("toNativeShare".equals(method)){
                            toShare(params,reply);
                        }
                    }

                }

            }
        })


    }

    private fun toShare(params: Any?,reply: BasicMessageChannel.Reply<String>) {
        flutter05_tvTitle.postDelayed({
            reply.reply("分享成功！")
        },2000)
    }


    fun onClick(view: View) {
        when(view.id){
            R.id.flutter05_fab->{
                // 发送信息
                var map = hashMapOf<String, Any>()
                // 建议。正式使用时使用指令配置，如 1001 是显示flutter的title，1002是刷新数据等等。
                map.put("method","addFlutterCount")
                map.put("count",3)
                var toJson = Gson().toJson(map)
                Log.i("Hui", "onClick: -----> $toJson")
                basicMessageChannel?.send(toJson,(BasicMessageChannel.Reply {
                    Log.i("Hui", "onClick: replyStr: $it")

                }))
            }
        }
    }

}