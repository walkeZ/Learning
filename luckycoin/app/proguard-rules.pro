# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\caihui\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
## WebViewActivity.WebObject中的成员不能混淆 很重要
-keepnames class com.gdcaihui.luckycoin.android.activityy.WebViewActivity$WebObject {
    <fields>;
    <methods>;
}
-keep class com.gdcaihui.luckycoin.android.activity.WebViewActivity$WebObject{ *; }


-keepnames class com.gdcaihui.luckycoin.android.fragment.FirstFragment$WebObject {
    <fields>;
    <methods>;
}
-keep class com.gdcaihui.luckycoin.android.fragment.FirstFragment$WebObject{ *; }

#----------------------------------------------------------------------------
#--------------------------------------定制化区域-------------------------------------------------------------
#---------------------------------1.实体类---------------------------------

-keep class com.gdcaihui.luckycoin.android.entity.** { *; }
-keep class com.gdcaihui.luckycoin.android.entity.vo.** { *; }
#不加会导致请求(服务器)返回的数据解析(GSON解析)异常

-keep class com.gdcaihui.luckycoin.android.okhttp.** { *; }

#---------------------------------2.第三方包------------------------------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#-keepnames glide引用
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#Gson引用
-keep class com.google.**{*;}

-dontwarn org.apache.http.**
-dontwarn okio.**
-dontwarn rx.internal.util.unsafe.*
-dontwarn com.squareup.picasso.*
#友盟统计
-keep class com.umeng.commonsdk.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.gdcaihui.luckycoin.android.R$*{
    public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
