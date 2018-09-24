import 'dart:async';
import 'dart:core';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}


class _MyHomePageState extends State<MyHomePage> {
  static const jumpPlugin = const MethodChannel('com.jzhu.jump/plugin');

  Future<Null> _jumpToNative() async {
    Map<String, String> map = {"path": "/main/TestActivity"};

    String result = await jumpPlugin.invokeMethod('jump2act', map);
    print(result);
  }

  Future<bool> _requestPop() {
    SystemNavigator.pop();
    return new Future.value(false);
  }


  @override
  Widget build(BuildContext context) {
    return new WillPopScope(
        child: new Scaffold(
          appBar: new AppBar(
            title: new Text("Home Page"),
          ),
          body: new Center(
            child: new RaisedButton(
                child: new Text("跳到TestActivity"), onPressed: _jumpToNative),
          ),
          // This trailing comma makes auto-formatting nicer for build methods.
        ),
        onWillPop: _requestPop);
  }
}
