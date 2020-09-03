import 'package:flutter/material.dart';

class DefaultPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Stack(
      children: <Widget>[
        Container(
          decoration: new BoxDecoration(
            border: new Border.all(color: Colors.black, width: 5), // 边色与边宽度
            color: Color(0x009E9E9E), // 底色
            //        borderRadius: new BorderRadius.circular((20.0)), // 圆角度
            borderRadius: new BorderRadius.vertical(
                top: Radius.elliptical(20, 50)), // 也可控件一边圆角大小
          ),
        ),
        Scaffold(
          backgroundColor: Color(0X33000000),
          appBar: AppBar(
            title: Text('DefaultPage'),
            centerTitle: true,
          ),
          body: Container(
            color: Color(0X3300ff00),
            child: Center(
              child: Text("data默认页路由是 --  / "),
            ),
          ),
        )
      ],
    );
  }
}
