# -*- coding:utf-8 -*-
from appium import webdriver

import time

desired_caps = {}
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '4.4'
# desired_caps['platformVersion'] = '4.4'
# desired_caps['deviceName'] = 'deviceName'
desired_caps['deviceName'] = 'MBX YCF AD BOARD'
desired_caps['appPackage'] = 'com.guaguayi.terminal'
desired_caps['appActivity'] = '.sys.other.LaunchActivity'

# driver = webdriver.Remote('http://192.168.1.197:4723/wd/hub', desired_caps)
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

time.sleep(5)   #启动app时，需要一定时间进入引导页，所以必须设置等待时间，不然下面会一直报错定位不到元素
driver.find_element_by_xpath("//*[@text='初始化设备']").click()
time.sleep(15)
driver.quit()







