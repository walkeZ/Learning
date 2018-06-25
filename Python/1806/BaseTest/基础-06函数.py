# -*— coding=utf-8 -*-

'''
定义一个函数
    函数代码块以 def 关键词开头，后接函数标识符名称和圆括号 ()。
    任何传入参数和自变量必须放在圆括号中间，圆括号之间可以用于定义参数。
    函数的第一行语句可以选择性地使用文档字符串—用于存放函数说明。
    函数内容以冒号起始，并且缩进。
    return [表达式] 结束函数，选择性地返回一个值给调用方。不带表达式的return相当于返回 None。
语法：
    Python 定义函数使用 def 关键字，一般格式如下：
    def 函数名（参数列表）:
        函数体

'''
import sys                      # 引入 sys 模块

# 定义一个函数 生成获取0到n的和
def getSum(n): # def 方法定义关键字，getSum 方法名，n 传入参数
    pass # pass 使用，当你还没想好具体实现时用pass，避免编译报错
    sum=0
    while n>0:
        sum+=n     # 次数，用于与传入n比较
        n-=1
    return sum
def getArea(width,height):
    return width*height

def print_welcome(name):
    print('welcome',name)

f=getSum(10)
print(f)
print_welcome('walke')
print(getArea(6,8))

def print_welcome2(name):
    print('welcome',name)

print_welcome2('walke2')


