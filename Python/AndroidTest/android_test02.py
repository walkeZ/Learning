# -*- coding=utf-8 -*-
# DesiredCapabilities[所需功能]参数配置及含义: 这个应该是java中使用的
# https://www.cnblogs.com/sunshine-sky66/p/6100448.html
from appium import webdriver
from selenium.webdriver.common.by import By

desired_caps = {}
desired_caps['platformName'] = 'Android'
# desired_caps['platformVersion'] = '5.1'
desired_caps['platformVersion'] = '4.4'
desired_caps['deviceName'] = 'Android Emulator'
desired_caps['appPackage'] = 'com.android.calculator2'
desired_caps['appActivity'] = '.Calculator'

'''
注意：1.运行python脚本[写有python代码的文本]前先跑起Appium Server
      2.Appium Server记得用127.0.0.1【对应webdriver.Remote的localhost】
      3.(Original error: UiAutomator quit before it successfully launched)
      原因:可能是收到之前一次使用的Appium Server的影响，先停止再启动再运行python脚本即可
      4：Android7.0+ 有兼容问题 参考：https://blog.csdn.net/xl_lx/article/details/78717898
      发现：魅族测试手机老是不能运行，
          终端机和模拟器都可以运行，类似UIAutomator Viewer可用情况，初步估计与root权限有关
'''

driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
# name定位报错 https://www.cnblogs.com/yoyoketang/p/7804345.html
# driver.find_element_by_name("5").click()  # Appium  V1.5以后版本不支持name定位了
driver.find_element_by_xpath("//*[@text='1']").click()    # 1

driver.find_element_by_xpath("//*[@text='5']").click()       # 5
str1=By.NAME
# driver.find_element(str1,"5").click()       # 5

# driver.find_element(By.XPATH("//android.widget.Button[contains(@text,'9')]")).click()     # 9
driver.find_element_by_xpath("//*[@text='9']").click()     # 9

driver.find_element_by_xpath("//*[@text='删除']").click()

driver.find_element_by_xpath("//*[@text='1']").click()

driver.find_element_by_xpath("//*[@text='5']").click()

driver.find_element_by_xpath("//*[@text='+']").click()

driver.find_element_by_xpath("//*[@text='6']").click()

driver.find_element_by_xpath("//*[@text='=']").click()

driver.quit()






