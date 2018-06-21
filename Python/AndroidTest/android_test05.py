# -*- coding=utf-8 -*-
# DesiredCapabilities[所需功能]参数配置及含义: 这个应该是java中使用的
# https://www.cnblogs.com/sunshine-sky66/p/6100448.html
from appium import webdriver
from selenium.webdriver.common.by import By
import time

desired_caps = {}
desired_caps['platformName'] = 'Android'
# desired_caps['platformVersion'] = '5.1'
desired_caps['platformVersion'] = '4.4'   # 模拟器 发现也可以运行在6.0的模拟器中
desired_caps['deviceName'] = 'Meizu'
desired_caps['appPackage'] = 'com.mabang.android'
desired_caps['appActivity'] = '.activity.user.UserLoginActivity'

driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
# 启动app时，需要一定时间进入引导页，所以必须设置等待时间，不然下面会一直报错定位不到元素
time.sleep(8)


driver.quit()






