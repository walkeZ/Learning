package com.walke.ktpractice.with_flutter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import kotlinx.android.synthetic.main.activity_flutter_test.*

/** Mac 中 AS 快捷键：
 * 格式化代码 <-->  alt + cmd +L
 * 复制上一行： cmd + D
 * 大小写转化快捷键： shift + cmd + U
 * 缩放界面字体： cmd + 控制板手势
 */
class FlutterTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter_test)
        // 传入lambda表达式函数 结构：{参数n：类型,参数n：类型 -> 方法体}
        // command + P查看可用方法。((v:View!)->Boolean)? 。问号表示事件可能为null
        aft_btTF01.setOnLongClickListener({ v -> false }) // ②
        aft_btTF01.setOnLongClickListener { v -> false }  // ③
        aft_btTF01.setOnLongClickListener(object : View.OnLongClickListener {  // ①  事件进化
            override fun onLongClick(v: View?): Boolean {
//                TODO("Not yet implemented")
                return false
            }
        })

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.aft_btTF01, R.id.aft_tvTitle -> {
//                TODO() 以fragment的形式，参考：https://www.jianshu.com/p/a37a2e1c6847
                // Toast.makeText(this, "333", Toast.LENGTH_SHORT).show()
                var tx = supportFragmentManager.beginTransaction()
                var fragment =
                    FlutterFragment.withNewEngine()
                        .initialRoute("{name:'张三原生数据'}").build<FlutterFragment>()
                tx.replace(R.id.aft_frameLayout, fragment)
                tx.commit()
            }

            R.id.aft_btTF02 -> {
                Toast.makeText(this, "Flutter01Act", Toast.LENGTH_SHORT).show()
                //todo 以flutterView 形式。https://www.jianshu.com/p/17abdf8e17ce
                Flutter01Activity.start(this, "Flutter01Activity FlutterView")
//                var flutterView = FlutterView(this)
//                var layoutParams = FrameLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                main_frameLayout.addView(flutterView,layoutParams)
//                var flutterEngine = FlutterEngine(this)
//                flutterEngine.navigationChannel.setInitialRoute("")
//                flutterEngine.dartExecutor.executeDartEntrypoint(
//                    DartExecutor.DartEntrypoint.createDefault()
//                )
//                flutterView.attachToFlutterEngine(flutterEngine)
            }
            R.id.aft_btTF03 -> {
                Toast.makeText(this, "tf03", Toast.LENGTH_SHORT).show()
                startActivity(FlutterActivity.createDefaultIntent(this))
            }
            R.id.aft_btTF04 -> {
                Toast.makeText(this, "tf04", Toast.LENGTH_SHORT).show()
                // 参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-screen
                startActivity(
                    FlutterActivity
                        .withNewEngine()
                        .initialRoute("page01")
                        .build(this)
                )
            }
            R.id.aft_btTF05 -> {
                Toast.makeText(this, "tf05", Toast.LENGTH_SHORT).show()
                // 使用Fragment形式。参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-fragment?tab=use-prewarmed-engine-kotlin-tab
                // 注意参考地址容易打不开timeout，不是需要翻墙。经实验：打开失败后切换网络再次打开容易成功打开。
                Flutter02Activity.start(this, "Flutter Fragment 02")

            }
            R.id.aft_btTF06 -> {
                toast("tf06")
                // 使用Fragment形式。参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-fragment?tab=use-prewarmed-engine-kotlin-tab
                // 注意参考地址容易打不开timeout，不是需要翻墙。经实验：打开失败后切换网络再次打开容易成功打开。
                Flutter03Activity.start(this, "Flutter03 MethodChannel")

            }
            R.id.aft_btTF07 -> Flutter04Activity.start(this, "Flutter04Activity EventChannel")
            R.id.aft_btTF08 -> Flutter05Activity.start(this, "Flutter05Activity basicMsgChannel")


        }

    }
}
