# -*- coding:UTF-8 -*-

import os

import time

import unittest

from selenium import webdriver

'''
参考：1.https://blog.csdn.net/qingemengyue/article/details/79820833
      2.https://www.cnblogs.com/szmcn/p/7090070.html
注意：系统权限不同效果不同，Android4.4模拟器成功了

找元素： http://blog.51cto.com/laomomo/2051631
        https://www.cnblogs.com/forcepush/p/6721828.html
        https://www.cnblogs.com/jiuyigirl/p/7241059.html
        
TestHome中文社区：appium： https://testerhome.com/topics/node23
'''

PATH = lambda p: os.path.abspath(

    os.path.join(os.path.dirname(__file__), p)

)

global driver


class Login(unittest.TestCase):

    # 配置设备
    def setUp(self):
        desired_caps = {}
        desired_caps['device'] = 'android'
        desired_caps['platformName'] = 'Android'

        desired_caps['browserName'] = ''
        desired_caps['version'] = '6.0'
        desired_caps['deviceName'] = 'ch13'  # 这是测试机的型号，可以查看手机的关于本机选项获得，也可以自定义

        # desired_caps['app'] = PATH('D:\\qq.apk')  # 被测试的App在电脑上的位置, []
        desired_caps['appPackage'] = 'com.guaguayi.terminal'  # 被测试的App已安装在设备上
        desired_caps['appActivity'] = '.activity.LaunchActivity'  # 启动Activity
        desired_caps['noReset'] = True  # 是否每次都要重启

        self.driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', desired_caps)

    def tearDown(self):
        pass
        # self.driver.quit()

    #
    # def pay(self):
    #     time.sleep(5)  # 延时 5秒
    #     # 找到控件并设置点击事件
    #     list = self.driver.find_elements_by_id("id/imagelogo")
    #     list[0].click()
    #     time.sleep(5)

    #  以accessibility_id进行定位，对Android而言，就是content-description属性
    def test_buy(self):
        time.sleep(15)  # 延时 10秒
        # 找到控件并设置点击事件

        self.driver.find_element_by_id("com.guaguayi.terminal:id/buyTicketButton").click()
        # pay()
        time.sleep(2)  # 延时 5秒
        # 找到控件并设置点击事件
        list = self.driver.find_elements_by_id("com.guaguayi.terminal:id/imagelogo")
        for x in range(2):
            list[0].click()
            time.sleep(1)
        for x in range(3):
            list[1].click()
        time.sleep(2)
        # 找到控件并设置点击事件
        list = self.driver.find_elements_by_id("com.guaguayi.terminal:id/btnreduce")
        list[0].click()
        list[1].click()

        time.sleep(2)
        self.driver.find_element_by_id("com.guaguayi.terminal:id/functionButton").click()
        time.sleep(3)
        self.driver.find_element_by_id("com.guaguayi.terminal:id/qrCode").click()
        time.sleep(2)
        self.driver.find_element_by_id("com.guaguayi.terminal:id/outExitButton").click()
        time.sleep(2)

    # 此处加上检测登录是否成功的代码


if __name__ == '__main__':  # 如果是主执行脚本，执行下面的代码，就是在命令行直接调用，不是被其他脚本导入调用
    unittest.main()
