import 'package:flutter/services.dart';

// config包，放置一些配置成员(通常是常量)、数据
// cf是config包具体文件的前缀，cfChannel ：与交互相关的配置，
/**
 * 参数是与原生端商议好的识别标识
 * https://www.jianshu.com/p/39575a90e820
 * 点进去查看源码可知。EventChannel主要向外界提供了了一个方法receiveBroadcastStream()返回一个stream带有流的进度。
 * 参考 https://www.jianshu.com/p/39575a90e820 可知三种Channel与Flutter通信的工具却是相同的，均为BinaryMessager。
 * EventChannel提供外界的方法较为单一，可依据实际情况选用这个三个Channel
 */
/// walke_mac_first_module_channel：与原生交互的互通标识，两端一样。
const String channel_id_method = 'walke_mac_first_module_methodchannel';
const myMethodChannel = const MethodChannel(channel_id_method);

//
const String channel_id_event = 'walke_first_module_event_channel';

const myEventChannel = EventChannel(channel_id_event);

const String channel_id_basic = 'walke_first_module_basic_msg_channel';
// 指定编码类型就会将两端互传的数据解析为对应类型，如此处的String。
const myBasMsgChennel = BasicMessageChannel(channel_id_basic, StringCodec());
