import 'dart:io';
import 'dart:ui';

import 'package:first_module/config/cfChannel.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

/// MethodChannel交互
class PageNative01 extends StatefulWidget {
  String title = '默认标题';
  String _title = '私有标题'; // 以下划线"_"开头的代表私有访问权限即仅在当前文件/widget 内。

  @override
  State<StatefulWidget> createState() => _PageNativeState01();
}

/**
   * 以下划线开头的代表私有访问权限即仅在当前文件/widget 内。
   */
/// MethodChannel 交互
class _PageNativeState01 extends State<PageNative01>
    with WidgetsBindingObserver {
  String _nativeData = '--';
  String _addFriendResult = '--';

  String paramsNativeCtrl = '--';
  int countNativeCtrl = 0;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initNativeChannelListener();

    /// 响应
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  Future<bool> didPopRoute() {
    // TODO: implement didPopRoute
    return super.didPopRoute();
    print("flutter----->didPopRoute");
  }

///2020-08-28 01:09:19.258 18848-18946/com.walke.ktpractice I/flutter: flutter------> didChangeAppLifecycleState state AppLifecycleState.resumed
//2020-08-28 01:09:19.258 18848-18946/com.walke.ktpractice I/flutter: flutter------> resumed
//2020-08-28 01:09:19.258 18848-18946/com.walke.ktpractice I/flutter: flutter-----> nativeResume
//2020-08-28 01:09:22.278 18848-18946/com.walke.ktpractice I/flutter: flutter------> didChangeAppLifecycleState state AppLifecycleState.inactive
//2020-08-28 01:09:22.278 18848-18946/com.walke.ktpractice I/flutter: flutter------> inactive
//2020-08-28 01:09:22.705 18848-18946/com.walke.ktpractice I/flutter: flutter------> didChangeAppLifecycleState state AppLifecycleState.paused
//2020-08-28 01:09:22.705 18848-18946/com.walke.ktpractice I/flutter: flutter------> paused
  // 原生没有flutterFragment?.onPostResume()也能回调。
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    // TODO: implement didChangeAppLifecycleState
    super.didChangeAppLifecycleState(state);
    print("flutter------> didChangeAppLifecycleState state $state");
    switch (state) {
      case AppLifecycleState.resumed: // 与原生基本一致
        print("flutter------> resumed");
        nativeResume();
        break;
      case AppLifecycleState.paused:
        print("flutter------> paused");
        break;
      case AppLifecycleState.detached:
        print("flutter------> detached");
        break;
      case AppLifecycleState.inactive: // 跳转、息屏、finish。执行顺序：inactive->pause->detached【finish时】
        print("flutter------> inactive");
        break;
      default:
    }
  }

  /// 初始化原生channel监听
  void initNativeChannelListener() {
    // ignore: missing_return
    // 看源码也是封装了一层：binaryMessenger的使用
    myMethodChannel.setMethodCallHandler((call) {
      print(
          'initNativeChannelListener -----> name: ${call.method}   params: ${call.arguments}');
      if ("flutterAddCount" == call.method) {
        countNativeCtrl++;
        paramsNativeCtrl = call.arguments;
      }
      setState(() {});
    });

    myMethodChannel.setMockMethodCallHandler((call) {});
    var isAndroid = Platform.isAndroid;
    if (isAndroid) {}
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("${widget._title} methodChannel"),
        leading: IconButton(icon: Icon(Icons.arrow_back_ios), onPressed: (){
//          Navigator.of(context).pop();
        SystemNavigator.pop();
        }),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            RaisedButton(
              onPressed: _getActivityResult,
              child: Text('get data from Native'),
            ),
            Text(_nativeData),
            RaisedButton(
              onPressed: _addFriend,
              child: Text('to Native  addFriend'),
            ),
            Text(_addFriendResult),
            Text('$paramsNativeCtrl : $countNativeCtrl')
          ],
        ),
      ),
    );
  }

  /**
           * Flutter 调原生事例1不带参。
           */
  ///
  Future<void> _getActivityResult() async {
    String result;
    try {
      final Map<dynamic, dynamic> map = await myMethodChannel
          .invokeMethod('getActivityResult'); // 反馈在原生的setMethodCallHandler
      result = 'getActivityResult  :  ${map["title"]}';
      print('------------> _getActivityResult  : $result');
    } catch (e) {
      result = 'Failed to get data form natvie';
    }
    print('------------> _getActivityResult  end : $result');
    setState(() {
      _nativeData = result;
    });
  }

  /// Flutter调原生事例2带参数，json，即可任意数据了
  Future<Null> _addFriend() async {
    try {
      _addFriendResult = await myMethodChannel
          .invokeMethod("addFriends", {'name': 'walke', 'age': 28});
      print("_addFriend -------> $_addFriendResult");
    } catch (e) {
      _addFriendResult = 'failed: addFriend from Native';
    }
    print("_addFriend -------> end:  $_addFriendResult");
    setState(() {});
  }

  void nativeResume() {
    print("flutter-----> nativeResume");
  }
}
