# -*-coding:UTF-8-*-
from com.android.monkeyrunner import MonkeyRunner as mr
from com.android.monkeyrunner import MonkeyDevice as md
from com.android.monkeyrunner import MonkeyImage as mi
#�����豸
device=mr.waitForConnection(2,'10.0.2.2:5555')
device.installPackage('D:\\guaguayi.apk')
device.startActivity('com.guaguayi.app.android.activity.HomeActivity')
# package: com.guaguayi.app.android
mr.sleep(3) 
#���������  
device.touch(100,100,'DOWN_AND_UP') 