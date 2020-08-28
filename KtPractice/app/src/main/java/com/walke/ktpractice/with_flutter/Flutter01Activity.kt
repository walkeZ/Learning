package com.walke.ktpractice.with_flutter

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.IntentTool
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor.DartEntrypoint
import io.flutter.view.FlutterMain
import kotlinx.android.synthetic.main.activity_flutter01.*


/**
 * Flutter 01 练习。原生调起Flutter
 * 参考： https://www.jianshu.com/p/17abdf8e17ce
 *
 * ps：Mac AS格式化代码快捷键：option + cmd +L
 */
class Flutter01Activity : AppCompatActivity() {

    companion object {
        fun start(activity: AppCompatActivity, data: String) {
            var intent = Intent(activity, Flutter01Activity::class.java)
            intent.putExtra(IntentTool.KEY_DATA, data)
            activity.startActivityForResult(intent, 0)
        }
    }


    //    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) { // xml布局内容无效果
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter01)

        initViewsMy()
        initData()

    }

    private fun initData() {
        flutter01_tvTitle.text = intent.getStringExtra(IntentTool.KEY_DATA)
    }

    private fun initViewsMy() {
        var flutterView = FlutterView(this)
        var layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        flutterView.setBackgroundResource(android.R.color.holo_blue_dark)

        var flutterEngine = FlutterEngine(this)
//        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        flutterEngine.dartExecutor.executeDartEntrypoint( DartEntrypoint(
                FlutterMain.findAppBundlePath(),
            "main"
        ))
        flutterEngine.navigationChannel.setInitialRoute("/")
        flutterView.attachToFlutterEngine(flutterEngine)

        flutterView.invalidate()
        flutter01_flContent.addView(flutterView, layoutParams)


    }

    private fun initViews() {

    }


}