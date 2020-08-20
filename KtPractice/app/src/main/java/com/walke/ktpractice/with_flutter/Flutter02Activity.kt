package com.walke.ktpractice.with_flutter

import android.content.ComponentCallbacks2
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.IntentTool
import com.walke.ktpractice.constant.toast
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.android.TransparencyMode

/**
 * 该类是通过fragment方式接入Flutter
 * 参考：https://flutter.dev/docs/development/add-to-app/android/add-flutter-fragment?tab=add-fragment-kotlin-tab
 * 注意: 参考地址容易打不开timeout，不是需要翻墙。经实验：打开失败后切换网络再次打开容易成功打开。
 */
class Flutter02Activity : AppCompatActivity() {
    companion object {

        // Define a tag String to represent the FlutterFragment within this
        // Activity's FragmentManager. This value can be whatever you'd like.
        private const val TAG_FRAGMENT = "flutter_fragment02" // fragment 的 tag

        /**
         * 封装，统一跳转写法，便于跟踪、维护，如：快速定位调用地方，防止缺漏。
         */
        fun start(activity: AppCompatActivity, title: String) {
            var intent = Intent(activity, Flutter02Activity::class.java)
            intent.putExtra(IntentTool.key_title, title)
            activity.startActivity(intent)
//            activity.overridePendingTransition() // 切换动画
        }
    }

    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter02)

        initContent()

    }

    private fun initContent() {
//        waysDefault()
//        waysEngineCache()
//        waysNewEngine()
//        waysRenderMode()
        waysTransparent()
    }

    /**
     * 缓存引擎方式，推荐，可以显著减少切换时白屏，可以先跳转打开页面，再通过数据交互传递需要的数据token、account等
     */
    private fun waysEngineCache() {
        flutterFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as FlutterFragment?
        if (flutterFragment==null){
//            flutterFragment = FlutterFragment.withCachedEngine(key_flutter_engine).build()
            // CacheEngine 不能动态设置路由，只能一开始时设置初始路由.如 MyApp 中
//            var withCachedEngine = FlutterFragment.withCachedEngine(key_flutter_engine)
            flutterFragment = FlutterFragment.withNewEngine().initialRoute("page01").build()
            supportFragmentManager.beginTransaction()
                .add(R.id.flutter02_flContent,flutterFragment!!, TAG_FRAGMENT)
                .commit()
        }
    }

    /**
     * 新引擎方式，推荐，场景动态设定路由，并可以指定Flutter Module中 main.dart 的方法作为入口【默认方法：main()】。
     */
    private fun waysNewEngine() {
        flutterFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as FlutterFragment?
        if (flutterFragment==null){
//            flutterFragment = FlutterFragment.withCachedEngine(key_flutter_engine).build()
            // CacheEngine 不能动态设置路由，只能一开始时设置初始路由.如 MyApp 中
//            var withCachedEngine = FlutterFragment.withCachedEngine(key_flutter_engine)
//            flutterFragment = FlutterFragment.withNewEngine().initialRoute("page01").build()
            flutterFragment = FlutterFragment.withNewEngine().dartEntrypoint("myEntryPoint").build()// 试验成功
            supportFragmentManager.beginTransaction()
                .add(R.id.flutter02_flContent,flutterFragment!!, TAG_FRAGMENT)
                .commit()
        }
    }

    /**
     * 选择渲染模式。主要考虑第二点。一般的设计来说就仅是一个FrameLayout包着一个FlutterFragment，这点可以明确避开第一点。
     * FlutterFragment可以使用SurfaceView呈现其Flutter内容，也可以使用TextureView。默认值为SurfaceView，
     * 它的性能明显优于TextureView。但是，SurfaceView 不能在Android View层次结构的中间交错。
     * ①A SurfaceView必须是View层次结构中的最底层或层次结构中的最顶层View。②此外，在Android N之前的Android版本上，
     * SurfaceView无法动画化，因为它们的布局和渲染与View层次结构的其余部分不同步。如果这两个用例都是您应用程序的要求，
     * 那么您需要使用TextureView而不是SurfaceView。TextureView通过用构建一个选择FlutterFragment一个 texture RenderMode：
     */
    private fun waysRenderMode() {
        flutterFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as FlutterFragment?
        if (flutterFragment==null){
//            flutterFragment = FlutterFragment.withCachedEngine(key_flutter_engine).build()
            // CacheEngine 不能动态设置路由，只能一开始时设置初始路由.如 MyApp 中
//            var withCachedEngine = FlutterFragment.withCachedEngine(key_flutter_engine)
//            flutterFragment = FlutterFragment.withNewEngine().initialRoute("page01").build()
            var withNewEngine = FlutterFragment.withNewEngine()
            flutterFragment = withNewEngine
                .renderMode(RenderMode.surface).build() // surface 可以，但是 texture 报错，估计SDK版本有关，是SDK漏洞。
            supportFragmentManager.beginTransaction()
                .add(R.id.flutter02_flContent,flutterFragment!!, TAG_FRAGMENT)
                .commit()
            Log.i("Hui", "waysRenderMode: ------>")
        }
    }

    private fun waysTransparent() {
        flutterFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as FlutterFragment?
        if (flutterFragment==null){
//            flutterFragment = FlutterFragment.withCachedEngine(key_flutter_engine).build()
            // CacheEngine 不能动态设置路由，只能一开始时设置初始路由.如 MyApp 中
//            var withCachedEngine = FlutterFragment.withCachedEngine(key_flutter_engine)
//            flutterFragment = FlutterFragment.withNewEngine().initialRoute("page01").build()
            var withNewEngine = FlutterFragment.withNewEngine()
            flutterFragment = withNewEngine
                .transparencyMode(TransparencyMode.transparent) // // 我目前的SDK版本已经不需要设置了。没有不同。
                .shouldAttachEngineToActivity(false)
                // 是否把引擎绑定到宿主Activity，即通过引擎是否可以交互，默认是true。如何交互，继续学习
                .build()
            supportFragmentManager.beginTransaction()
                .add(R.id.flutter02_flContent,flutterFragment!!, TAG_FRAGMENT)
                .commit()
            Log.i("Hui", "waysRenderMode: ------>")
        }
    }

    /**
     * 默认方式，直接打开flutter module中的main的runapp()且默认路由"/"
     */
    private fun waysDefault() {
        flutterFragment =
            supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as FlutterFragment?  // 没有？会报空奔溃
        if (flutterFragment == null) {
            flutterFragment = FlutterFragment.createDefault()
            supportFragmentManager.beginTransaction()
                .add(R.id.flutter02_flContent, flutterFragment!!, TAG_FRAGMENT)
                .commit()
        } else {
            supportFragmentManager.beginTransaction().replace(
                R.id.flutter02_flContent, flutterFragment!!,
                TAG_FRAGMENT
            ).commit()
        }
    }


    fun onClick(view: View) {
        "拓展吐司".toast(this)
        when (view.id) {

        }
    }

    /********* 关联生命周期,将系统信号传给FlutterFragment *********/

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()

    }

    // 没销毁时重新进入。
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment?.onNewIntent(intent)
    }

    // 系统返回键
    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    // 权限申请返回
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // 听说是home键监听： https://blog.csdn.net/u013772237/article/details/106200210/
    // 华为p9是。任务管理器按钮也走这个方法。同时执行完后会执行 onTrimMemory ，无法拦截只是告知。
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    // 系统GC回收 。home、任务管理器按钮都是 80【level】，再次试验是20
    /**
     * https://blog.csdn.net/zxm317122667/article/details/52123819
     */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)

        when(level){
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN->{ // 20
                // 表示应用程序的所有UI界面被隐藏了，即用户点击了Home键或者Back键导致应用的UI界面不可见．
                // 这时候应该释放一些资源,这个等级比较常用,和下面六个的关系不是很强.
            }
            TRIM_MEMORY_RUNNING_MODERATE->{ // 5
                // 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，
                // 系统可能会开始根据LRU缓存规则来去杀死进程了

            }
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW->{  // 10
                //表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，我们应该去释放掉
                // 一些不必要的资源以提升系统的性能，同时这也会直接影响到我们应用程序的性能。
            }
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL->{ // 15
                // 表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。
                // 这个时候我们应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，
                // 并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务。

            }
            //// 当应用程序是缓存的，则会收到以下几种类型的回调：
            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND->{ // 40
                // 表示手机目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。这个时候我们的程序在LRU缓存列表的最近位置，
                // 是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，
                // 从而让我们的程序更长时间地保留在缓存当中，这样当用户返回我们的程序时会感觉非常顺畅，
                // 而不是经历了一次重新启动的过程
            }
            ComponentCallbacks2.TRIM_MEMORY_MODERATE->{ 60
                // 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，如果手机内存还得不到
                // 进一步释放的话，那么我们的程序就有被系统杀掉的风险了。
            }
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE->{ 80
                // 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，
                // 系统会最优先考虑杀掉我们的应用程序，在这个时候应当尽可能地把一切可以释放的东西都进行释放。
            }
        }
    }


}