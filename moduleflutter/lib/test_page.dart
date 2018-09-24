import 'dart:async';
import 'dart:core';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class TestPage extends StatefulWidget {
  TestPage({Key key}) : super(key: key);

  @override
  _TestPageState createState() => new _TestPageState();
}

class _TestPageState extends State<TestPage> {
  static const channel =
      const BasicMessageChannel<String>('com.jzhu.msg/plugin', StringCodec());

  String _receivedMsg ='';

  String _replyMsg ='';

  Future<bool> _requestPop() {
    SystemNavigator.pop();
    return new Future.value(false);
  }

  void _sendMsg2Android() async {
     _replyMsg = await channel.send('TestPage：发条消息给你');
     setState(() {});
  }

  void _initMessageHandler() {
    channel.setMessageHandler((String message) async {
      _receivedMsg  = message;
      setState(() {});
      });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _initMessageHandler();
  }

  @override
  Widget build(BuildContext context) {
    return new WillPopScope(
        child: new Scaffold(
          appBar: new AppBar(
            title: new Text("Test Page"),
          ),
          body: new Column(
            children: <Widget>[
              new Padding(
                padding: const EdgeInsets.only(
                    left: 10.0, top: 10.0, right: 10.0),
                child:
                new Text('$_receivedMsg'),

              ),
              new Padding(
                padding: const EdgeInsets.only(
                    left: 10.0, top: 10.0, right: 10.0),
                child:
                new Text('$_replyMsg'),

              ),
              new Padding(
                  padding: const EdgeInsets.only(
                      left: 10.0, top: 10.0, right: 10.0),
                  child:
                  new RaisedButton(
                      child: new Text("点击发送数据给FlutterContainerActivity"),
                      onPressed: _sendMsg2Android)
              ),

            ],
          ),
          // This trailing comma makes auto-formatting nicer for build methods.
        ),
        onWillPop: _requestPop);
  }
}
