import 'dart:async';

import 'package:first_module/config/cfChannel.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

/// eventChannel 交互:
/// 参考：https://www.cnblogs.com/nesger/p/10580750.html
///       https://www.jianshu.com/p/b23174d06cf3
///
class PageNative02 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _PageNativeState02();
}

class _PageNativeState02 extends State<PageNative02> {
  String _data = '--';
  String _shareResult = '--';

  int _count = -1;

  String _dataWithCount = '--';

  StreamSubscription _subscription;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    myEventChannel.binaryMessenger
        // ignore: missing_return
        // 对照MethodChannel查看源码参考源码方式实现监听。
        .setMessageHandler(channel_id_event, (message) {
      MethodCall call = StandardMethodCodec().decodeMethodCall(message);
      print(
          'initState  setMessageHandler -----> name: ${call.method}   params: ${call.arguments}');
      if ("addFlutterCount" == call.method) {
        setState(() {
          _count++;
          _dataWithCount = call.arguments;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: PreferredSize(
          child: AppBar(
            title: Text('EventChannel 交互'),
            centerTitle: true,
          ),
          preferredSize: Size.fromHeight(40)),
      body: Column(
        children: <Widget>[
          MaterialButton(
            onPressed: _getDataFromNative,
            child: Text("getDateFromNative"),
          ),
          SizedBox(
            height: 20.0,
          ),
          Text(_data),
          SizedBox(
            height: 20,
          ),
          InkWell(
            child: Text("toNativeShare"),
            onTap: _toNativeShare,
          ),
          Text(_shareResult),
          SizedBox(height: 50.0),
          Text("count: $_count  <> $_dataWithCount"),
        ],
      ),
    );
  }

  /// 交互都是异步的，相当于局域网络请求。或者说互为客户端服务器。
  /// 直接获取原生数据，不带参数 . 类似广播的通讯方式。
  Future<void> _getDataFromNative() async {
    if (_subscription != null) {
      _subscription.cancel();
      _subscription = null;
    }
    // 进入方法查看可知里面使用的是MethodChannel,且已设默认方法名为listen。
    // 所以相当于自己另行定义一些MethodChannel方式中的arguments规律，两端对应协议处理即可
    _subscription = myEventChannel.receiveBroadcastStream([
      {'method': 'getDateFromNative'}
    ]).listen((event) {
      print("_getDataFromNative()----------> event :$event");
      setState(() {
        _data = event;
      });
    });
  }

  // 调原生分享，传数据过去即带参数
  Future<void> _toNativeShare() async {
    if (_subscription != null) {
      _subscription.cancel();
      _subscription = null;
    }
    _subscription = myEventChannel.receiveBroadcastStream([
      {
        'method': 'nativeShare',
        'params': {'title': '标题', 'content': '内容'}
      }
    ]).listen((event) {
      print("_toNativeShare()----------> event :$event");
      setState(() {
        _shareResult = event;
      });
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    if (_subscription != null) {
      _subscription.cancel();
    }
  }
}
