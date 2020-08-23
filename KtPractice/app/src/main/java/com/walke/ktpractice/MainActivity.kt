package com.walke.ktpractice

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.walke.ktpractice.constant.toast
import com.walke.ktpractice.with_flutter.Flutter01Activity
import com.walke.ktpractice.with_flutter.Flutter02Activity
import com.walke.ktpractice.with_flutter.Flutter03Activity
import com.walke.ktpractice.with_flutter.Flutter04Activity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment

/** Mac 中 AS 快捷键：
 * 格式化代码 <-->  alt + cmd +L
 * 复制上一行： cmd + D
 * 大小写转化快捷键： shift + cmd + U
 * 缩放界面字体： cmd + 控制板手势
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.main_btTF01,R.id.main_tvTitle -> {
//                TODO() 以fragment的形式，参考：https://www.jianshu.com/p/a37a2e1c6847
                // Toast.makeText(this, "333", Toast.LENGTH_SHORT).show()
                var tx = supportFragmentManager.beginTransaction()
                var fragment =
                    FlutterFragment.withNewEngine()
                        .initialRoute("{name:'张三原生数据'}").build<FlutterFragment>()
                tx.replace(R.id.main_frameLayout, fragment)
                tx.commit()
            }

            R.id.main_btTF02 -> {
                Toast.makeText(this, "tf02", Toast.LENGTH_SHORT).show()
                //todo 以flutterView 形式。https://www.jianshu.com/p/17abdf8e17ce
                Flutter01Activity.start(this, "Flutter01Act")
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
            R.id.main_btTF03 -> {
                Toast.makeText(this, "tf03", Toast.LENGTH_SHORT).show()
                startActivity(FlutterActivity.createDefaultIntent(this))
            }
            R.id.main_btTF04 -> {
                Toast.makeText(this, "tf04", Toast.LENGTH_SHORT).show()
                // 参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-screen
                startActivity(
                    FlutterActivity
                        .withNewEngine()
                        .initialRoute("page01")
                        .build(this)
                )
            }
            R.id.main_btTF05 -> {
                Toast.makeText(this, "tf05", Toast.LENGTH_SHORT).show()
                // 使用Fragment形式。参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-fragment?tab=use-prewarmed-engine-kotlin-tab
                // 注意参考地址容易打不开timeout，不是需要翻墙。经实验：打开失败后切换网络再次打开容易成功打开。
                Flutter02Activity.start(this, "Flutter Fragment 02")

            }
            R.id.main_btTF06 -> {
                toast("tf06")
                // 使用Fragment形式。参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-fragment?tab=use-prewarmed-engine-kotlin-tab
                // 注意参考地址容易打不开timeout，不是需要翻墙。经实验：打开失败后切换网络再次打开容易成功打开。
                Flutter03Activity.start(this, "Flutter03 MethodChannel")

            }
            R.id.main_btTF07->Flutter04Activity.start(this,"Flutter04Activity EventChannel")


        }

    }
}
