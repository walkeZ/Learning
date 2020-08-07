Module 目录下主要放置Flutter作为module的项目源码【用于接入原生，与原生交互，混合开发】

创建命令：flutter create -t module module_name【模块名】   建议先在控制台cd到目标目录后调用。需要稍等一会儿。会自动生成.android【Android环境/配置】、.iOS【iOS环境/配置】、.gitignore【git管理忽略配置，故上传到远程是没有.android和.ios】等。

flutter 一些常用命令

flutter --help 查看帮助（命令列表）
flutter --version  当前SDK版本。
flutter version  flutter版本列表
Flutter doctor 审查环境、编辑器准备准备状态。
flutter upgrade  SDK升级
