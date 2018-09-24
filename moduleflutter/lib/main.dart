import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:moduleflutter/home_page.dart';
import 'package:moduleflutter/test_page.dart';

//void main() => runApp(_widgetForRoute(window.defaultRouteName));

//Widget _widgetForRoute(String route) {
//  print("route:"+route);
//  switch (route) {
//    case 'route2':
//      return MyHomePage();
//    default:
//      return Center(
//        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
//      );
//  }
void main() {
  runApp(new MaterialApp(
    routes: <String, WidgetBuilder>{
    '/homepage': (BuildContext context) => new MyHomePage(),
      '/testpage': (BuildContext context) => new TestPage(),

  }, home: new MyHomePage()));
}

