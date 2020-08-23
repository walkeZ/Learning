import 'dart:developer';

import 'package:first_module/native/PageNative01.dart';
import 'package:first_module/page/DefaultPage.dart';
import 'package:flutter/material.dart';

import 'PageNative02.dart';
import 'PageNative03.dart';

///
///
// ignore: must_be_immutable 忽略这是（nativeData）不可变的。
class NativeConnect01 extends StatelessWidget {
  String nativeData = '';

  NativeConnect01({this.nativeData});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    // 记录：整个项目或者module最外层的 build 中第一级的树Widget 不能是scafold，应该是MaterialApp
    // 直接使用Scaffold报错：
    /*  The following assertion was thrown building NativeConnect:
I/flutter (12834): MediaQuery.of() called with a context that does not contain a MediaQuery.
I/flutter (12834): No MediaQuery ancestor could be found starting from the context that was passed to MediaQuery.of().
I/flutter (12834): This can happen because you do not have a WidgetsApp or MaterialApp widget (those widgets introduce
I/flutter (12834): a MediaQuery), or it can happen if the context you use comes from a widget above those widgets.
I/flutter (12834): The context used was:
I/flutter (12834):   Scaffold
**/
    print("-------------------->$nativeData");

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('原生对接'),
        ),
        body: Center(
          child: Text('居中文本  :  $nativeData'),
        ),
      ),
    );
  }
}

class ConnectNative02 extends StatefulWidget {
  final String route;
  ConnectNative02({Key key, this.route}) : super(key: key);

  @override
  State<StatefulWidget> createState() => ConnectNativeState02(route);
}

/// Mac 中 VScode 快捷键：
/// 格式化代码块：   shift + alt + F
/// 复制上一行：    shift + alt + 下箭头
class ConnectNativeState02 extends State<ConnectNative02> {
  final String route;
  ConnectNativeState02(this.route);

  @override
  Widget build(BuildContext context) {
    print('ConnectNativeState02 -----------> build() :  route = $route');

    return MaterialApp(
      initialRoute: route, // 初始化路由【页面指引/映射】
      routes: {
        '/': (context) => DefaultPage(),
        'page01': (context) => PageNative01(),
        'page02': (context) => PageNative02(),
        'page03': (context) => PageNative03(),
      },
    );
  }
}
