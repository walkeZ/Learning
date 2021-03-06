# -*- coding=utf-8 -*-
# DesiredCapabilities[所需功能]参数配置及含义: 这个应该是java中使用的
# https://www.cnblogs.com/sunshine-sky66/p/6100448.html

'''
    注意：Android7.0+ 有兼容问题 参考：https://blog.csdn.net/xl_lx/article/details/78717898
    发现：魅族测试手机老是不能运行，
          终端机和模拟器都可以运行，类似UIAutomator Viewer可用情况，初步估计与root权限有关
'''

from appium import webdriver
from selenium.webdriver.common.by import By
import time








desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '5.1'      # 模拟器 ，发现也可以运行在不同系统的模拟器中【4.4、6.0都可以】
desired_caps['deviceName'] = 'Meizu'        # 自定义，一般与设备对应
desired_caps['appPackage'] = 'com.android.settings'
desired_caps['appActivity'] = '.Settings'

driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
time.sleep(6)

driver.find_element_by_name("显示").click()    # 模拟器
driver.find_element_by_name("亮度").click()


# driver.find_element_by_name("安全").click()    # 魅族 note3
# driver.find_element_by_name("手机密码").click()


driver.quit()






