package com.walke.ktpractice.with_flutter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.IntentTool
import com.walke.ktpractice.constant.key_flutter_engine2
import com.walke.ktpractice.constant.toast
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.TransparencyMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_flutter03.*

/**
 * 与flutter交互方式1：MethodChannel ：
 * 参考：https://flutter.dev/docs/development/platform-integration/platform-channels
 * https://www.jianshu.com/p/8d23a86aecd4
 *
 */
class Flutter03Activity : AppCompatActivity() {

    private var methodChannel: MethodChannel? = null
    private var flutterFragment: FlutterFragment?=null

    companion object {

        /**
         * 与flutter交互的互通标识
         */
        const val FLUTTER_METHOD_CHANNEL = "walke_mac_first_module_methodchannel"

        fun start(activity: AppCompatActivity, title: String) {
            var intent = Intent(activity, Flutter03Activity::class.java)
            intent.putExtra(IntentTool.KEY_TITLE, title)
            activity.startActivityForResult(intent, IntentTool.REQUEST_CODE_DEFAULT)
            activity.overridePendingTransition(R.anim.intent_enter, 0)// 该方法应在start、finish后
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter03)

        initContent()


    }


    private fun initContent() {
        flutter03_tvTitle.text = intent.getStringExtra(IntentTool.KEY_TITLE)
        var withCachedEngine = FlutterFragment.withCachedEngine(key_flutter_engine2)
            .transparencyMode(TransparencyMode.transparent) // transparent上层的底部按钮(addFlutterCount)不显示,opaque显示
            .shouldAttachEngineToActivity(false) // 不影响MethodChannel交互
         flutterFragment = withCachedEngine.build() as FlutterFragment
        supportFragmentManager.beginTransaction().add(R.id.flutter03_flContent, flutterFragment!!)
            .commit()
//        var flutterEngine = flutterFragment?.flutterEngine
        // 上一行报错：Attempt to invoke virtual method 'io.flutter.embedding.engine.FlutterEngine
        // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.getFlutterEngine()' on a null object reference
        var flutterEngine = FlutterEngineCache.getInstance().get(key_flutter_engine2)
        initFlutterChannel(flutterEngine)

    }

    private fun initFlutterChannel(flutterEngine: FlutterEngine?) {
        // 创建MethodChannel对象传入DartExecutor对象和channel标识，并设置callHandler实现监听
        // flutter 调原生事例。
        methodChannel = MethodChannel(flutterEngine?.dartExecutor, FLUTTER_METHOD_CHANNEL)
        methodChannel?.setMethodCallHandler { call, result ->
                // 通过call 可以获取到方法名和参数，执行对应的业务逻辑即可
                Log.i("Hui", "initFlutterChannel: ---------->thread ${Thread.currentThread().name}   "
                        + "  methodName: ${call.method}   params: ${call.arguments}")
                when (call.method) {
                    "getActivityResult" -> {
                        val params = HashMap<String, String>()
                        params.put("title","来自native的title")
                        result.success(params)  // ok 成功。
//                        result.notImplemented()
                    }
                    "addFriends"->{
                        addFriends(call.arguments.toString(),result)
                    }
                    else->result.notImplemented()
                }
            }
    }

    private fun addFriends(
        arguments: String,
        result: MethodChannel.Result
    ) {
        Log.i("Hui", "addFriends: -------> $arguments")
        var fromJson = Gson().fromJson(arguments, MutableMap::class.java)
        toast("${fromJson["name"]}   ${fromJson["age"]} Flutter来添加好友")
        flutter03_tvTitle.postDelayed({
            // TODO: 2020/8/20
            result.success("添加好友成功")
        },2000)
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.flutter03_tvTitle,R.id.flutter03_btAddFlutterCount->{
                // 原生调Flutter
                methodChannel?.invokeMethod("flutterAddCount","{'arg1':'来自原生'}",object :MethodChannel.Result{
                    override fun notImplemented() {
                        Log.i("Hui", "notImplemented: ------> 原生调Flutter")
                    }

                    override fun error(
                        errorCode: String?,
                        errorMessage: String?,
                        errorDetails: Any?
                    ) {
                        Log.e("Hui", "error: ------> 原生调Flutter ", errorDetails as Throwable?)
                    }

                    override fun success(result: Any?) {
                        Log.i("Hui", "success: --------> 原生调Flutter")
                        toast(result.toString())
                    }
                })
            }
            else->{

            }
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null)
            flutterFragment?.onNewIntent(intent)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment?.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.intent_exit)
    }



}