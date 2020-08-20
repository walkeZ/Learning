package com.walke.ktpractice.base

import android.app.Application
import com.walke.ktpractice.constant.key_flutter_engine
import com.walke.ktpractice.constant.key_flutter_engine2
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initFlutterEngine()
    }

    /**
     * 初始化FlutterEngine ，flutter引擎初始化。
     */
    private fun initFlutterEngine() {
        // Somewhere in your app, before your FlutterFragment is needed,
        // like in the Application class ...
        // Instantiate a FlutterEngine.
        var flutterEngine = FlutterEngine(this)
        // + 配置一个初始路由：
        flutterEngine.navigationChannel.setInitialRoute("/")

        // Start executing Dart code in the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the pre-warmed FlutterEngine to be used later by FlutterFragment.
        // 其实这时已经在执行了dart的部分初始化流程了。所以引用Cache引擎不能动态更改路由，改用NewEngine。
        FlutterEngineCache.getInstance().put(key_flutter_engine,flutterEngine)

        // 通过 key-value的方式理论上可以添加多个CacheEngine

        var flutterEngine2 = FlutterEngine(this)
        // + 配置一个初始路由：
        flutterEngine2.navigationChannel.setInitialRoute("page02")

        // Start executing Dart code in the FlutterEngine.
        flutterEngine2.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(key_flutter_engine2,flutterEngine2)

    }

}