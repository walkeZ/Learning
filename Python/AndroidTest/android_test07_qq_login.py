# -*- coding:UTF-8 -*-

import os

import time

import unittest

from selenium import webdriver

'''
参考：1.https://blog.csdn.net/qingemengyue/article/details/79820833
      2.https://www.cnblogs.com/szmcn/p/7090070.html
注意：系统权限不同效果不同，Android4.4模拟器成功了
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

        desired_caps['version'] = '7.0'

        desired_caps['deviceName'] = 'FRD-AL00C00B387'  # 这是测试机的型号，可以查看手机的关于本机选项获得，也可以自定义

        # desired_caps['app'] = PATH('D:\\qq.apk')  # 被测试的App在电脑上的位置, []
        desired_caps['appPackage'] ='com.tencent.mobileqq'  # 被测试的App已安装在设备上
        desired_caps['appActivity'] ='.activity.SplashActivity'  # 启动Activity

        self.driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_login(self):
        time.sleep(10)  # 延时 10 秒

        self.driver.find_element_by_name('登 录').click() # 找到控件并设置点击事件

        name = self.driver.find_element_by_name('QQ号/手机号/邮箱')  # 找到控件

        #name.click()

        name.send_keys('1126648815')  # 对控件设置文本

        psd = self.driver.find_element_by_id('password')

        psd.click()

        psd.send_keys("ender753951")

        blogin = self.driver.find_element_by_id('login')

        blogin.click()

        time.sleep(10)

        # 此处加上检测登录是否成功的代码


if __name__ == '__main__':   # 如果是主执行脚本，执行下面的代码，就是在命令行直接调用，不是被其他脚本导入调用
    unittest.main()