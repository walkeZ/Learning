import 'package:flutter/material.dart';

class DefaultPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
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
    );
  }
}
