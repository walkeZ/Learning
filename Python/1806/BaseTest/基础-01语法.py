# 命名规范： https://www.cnblogs.com/EmptyRabbit/p/7679093.html
# 基本遵循驼峰式命名规范，注意：
#   函数&方法       --- 小写+下划线              --如-- user_login
#   私有类成员      --- 单一下划线前缀标识        --如-- _name
#   异常           --- 以“Error”作为后缀       --如-- user_login
#   文件名          --- 全小写,可使用下划线       --如-- user_login                //本文件是标识学习内容故用了中文

# 缩进在用编辑器(pythonCharm)时机器会帮你识别
# 独立写脚本(note++,系统文本编辑器等写代码时)时可能会遇到
# 常见error： IndentationError: unexpected indent；indent--缩进，unexpected--意外的，想不到的

# -*— coding=utf-8 -*-
print('hello，python')
print("walke")

#等待用户输入
input("按下enter键退出，其他任意键显示1...\n")  # \n 实现换行
str=input("按下enter键退出，其他任意键显示2...\n")  # \n 实现换行
print("str: "+str)  # str = 输入内容

# Print输出  print输出默认是换行的，如果要实现不换行需要在print()里加‘,’号，叠加参数
print('a')
print('c')
print('a','c')
print('a','b','c','D','c','b','a')

# 多个语句构成代码组 ： 缩进相同的一组语句构成一个代码块，我们称之代码组。
# 像if、while、def和class这样的复合语句，首行以关键字开始，以冒号( : )结束，该行之后的一行或多行代码构成代码组
num=input("请输入一个数字：\n") #输入的默认都为 str 要转化
num=int(num)
if (num<5):
    print('input num < 5')
elif(num==5):
    print('input num = 5')
else:
    print('input num >5')