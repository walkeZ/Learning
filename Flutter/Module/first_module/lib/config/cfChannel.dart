import 'package:flutter/services.dart';

// config包，放置一些配置成员(通常是常量)、数据
// cf是config包具体文件的前缀，cfChannel ：与交互相关的配置，
// walke_mac_first_module_channel：与原生交互的互通标识，两端一样。
const myMethodChannel = const MethodChannel("walke_mac_first_module_channel");
