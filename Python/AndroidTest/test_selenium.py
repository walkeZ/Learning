# -*- coding:utf-8 -*-
# appium新手入门（1）—— appium介绍 http://www.testclass.net/appium/appium-base-summary/
from selenium import webdriver

driver = webdriver.Firefox()
#driver = webdriver.Chrome()
driver.get('https://baidu.com/')
print(driver.title)
driver.quit()

