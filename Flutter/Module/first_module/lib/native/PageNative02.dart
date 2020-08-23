import 'package:first_module/config/cfChannel.dart';
import 'package:flutter/material.dart';

/// MethodChannel交互
class PageNative02 extends StatefulWidget {
  String title = '默认标题';
  String _title = '私有标题'; // 以下划线"_"开头的代表私有访问权限即仅在当前文件/widget 内。

  @override
  State<StatefulWidget> createState() => _PageNativeState02();
}

/**
   * 以下划线开头的代表私有访问权限即仅在当前文件/widget 内。
   */
/// MethodChannel 交互
class _PageNativeState02 extends State<PageNative02> {
  String _nativeData = '--';
  String _addFriendResult = '--';

  String paramsNativeCtrl = '--';
  int countNativeCtrl = 0;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initNativeChannelListener();
  }

  /// 初始化原生channel监听
  void initNativeChannelListener() {
    // ignore: missing_return
    myMethodChannel.setMethodCallHandler((call) {
      print(
          'initNativeChannelListener -----> name: ${call.method}   params: ${call.arguments}');
      if ("flutterAddCount" == call.method) {
        countNativeCtrl++;
        paramsNativeCtrl = call.arguments;
      }
      setState(() {});
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text(widget._title),
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
}
