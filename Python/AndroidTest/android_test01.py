# -*- coding=utf-8 -*-
from appium import webdriver
# https://www.cnblogs.com/fnng/p/4540731.html
# http://www.testclass.net/appium/appium-base-python/
# 模拟器的的calculate App测试，使用Appium Server【这个需要启动中】"基础版",换成 Appium-desktop报错
#  pip install Appium-Python-Client 遇到
# error: could not create 'C:\Program Files (x86)\Python36-32\Lib\site-packages\appium': 拒绝访问。
#  以管理员身份运行cmd

'''
    注意：Android7.0+ 有兼容问题 参考：https://blog.csdn.net/xl_lx/article/details/78717898
    发现：魅族测试手机老是不能运行，
          终端机和模拟器都可以运行，类似UIAutomator Viewer可用情况，初步估计与root权限有关
'''

desired_caps = {}
desired_caps['platformName'] = 'Android'                # 这是标识
desired_caps['platformVersion'] = '5.1'
# desired_caps['platformVersion'] = '4.4'                 # 这个要对应设备的系统
desired_caps['deviceName'] = 'walke'   # 这个命名可以随意
# desired_caps['appPackage'] = 'com.android.calculator2'
# desired_caps['appActivity'] = '.Calculator'
desired_caps['appPackage'] = 'com.meizu.flyme.calculator' # 魅族
desired_caps['appActivity'] = '.Calculator'

driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

driver.find_element_by_name("1").click()

driver.find_element_by_name("5").click()

driver.find_element_by_name("9").click()

driver.find_element_by_name("删除").click()

driver.find_element_by_name("9").click()

driver.find_element_by_name("5").click()

driver.find_element_by_name("+").click()

driver.find_element_by_name("6").click()

driver.find_element_by_name("=").click()

driver.quit()