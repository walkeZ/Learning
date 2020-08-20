import 'package:flutter/material.dart';

class PageNative01 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _PageNativeState01();
}

class _PageNativeState01 extends State<PageNative01> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: PreferredSize(
          child: AppBar(
            title: Text('PageNative01'),
          ),
          preferredSize: Size.fromHeight(50.0)),
      body: Column(
        children: <Widget>[
          Container(
            height: 50.0,
            color: Colors.green[200],
            margin: EdgeInsets.only(top: 10.0),
            child: Text('data01'),
          ),
          Container(
            height: 50.0,
            color: Colors.green[200],
            margin: EdgeInsets.only(top: 10.0),
            child: Text('data02'),
          ),
        ],
      ),
    );
  }
}
