import 'dart:convert';

import 'package:first_module/config/cfChannel.dart';
import 'package:flutter/material.dart';

class PageNative03 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _PageNativeState03();
}

class _PageNativeState03 extends State<PageNative03> {
  String _data = '--';

  String _shareResult = "--";

  int _count = -1;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    // 设置监听，相当于挂起一个服务器，
    // 等待来自客户端(原生)的请求。
    // ignore: missing_return
    myBasMsgChennel.setMessageHandler((message) {
      print("PageNative03 setMessageHandler -------> msg: $message");
      if (message == null || message.length == 0) return;
      if (message.startsWith("{") && message.endsWith("}")) {
        var parsedJson = jsonDecode(message);
        if (parsedJson['method'] == 'addFlutterCount') {
          int count = parsedJson['count'];
          _count += count;
          setState(() {});
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
          child: AppBar(
            title: Text("PageNative03 basicMsgChannel"),
            centerTitle: true,
          ),
          preferredSize: Size.fromHeight(45.0)),
      body: Column(
        children: <Widget>[
          InkWell(
            child: Container(
              color: Colors.blue[200],
              child: Text('getDataFromNative'),
            ),
            onTap: _getDataFromNative,
          ),
          Text(_data),
          SizedBox(
            height: 20.0,
          ),
          GestureDetector(
            child: Container(
              color: Colors.blue[200],
              child: Text("toNativeShare"),
            ),
            onTap: _toNativeShare,
          ),
          Text(_shareResult),
          SizedBox(
            height: 20.0,
          ),
          Container(
            height: 50,
            width: 50,
            child: CircleAvatar(
              backgroundColor: Colors.blue,
              child: Text("$_count"),
            ),
          ),
        ],
      ),
    );
  }

  void _getDataFromNative() async {
    _data = await myBasMsgChennel.send("getDataFromNative").whenComplete(() {});
    if (_data == null) _data = 'Natvie ruturn null';
    setState(() {});
  }

  void _toNativeShare() async {
    Map<String, dynamic> map = {
      'method': "toNativeShare",
      'params': {'title': '分享的标题', 'id': 10002}
    };

    myBasMsgChennel.send(map.toString()).then((value) {
      print("PageNative03 ----->_toNativeShare: $value");
      _shareResult = value == null ? 'native return null' : value;
      setState(() {});
    });
  }
}
