
# _*_ coding:utf-8 _*_   # 编码声明 “#” 单行注释

# 学习地址：http://www.runoob.com/manual/pythontutorial3/docs/html/introduction.html

# 什么是脚本文件，写有(XX语言)程序代码的文本文件。
# 如：python脚本：写有python代码的文本文件。

#this is the first comment 这是第一条注释/评论

a=1 # and this is the second comment: 定义一个变量a=1；int(整数)类型  等号( '=' )用于给变量赋值 
# 注意：变量在使用前必须 “定义”(赋值)
# print(b) # 报错：NameError: name 'b' is not defined
	# ... and now a third
text="# This is not a comment because it's inside quotes." # 字符串： 用单引号/双引号(英文输入状态)包裹
# 文本字符串中的 “#” 字符仅仅表示 “#” 。代码中的注释不会被 Python 解释

print("a: ",a) # print("a: "+a) 报错：TypeError: must be str, not int
#【控制台 复制代码：Ctrl+Insert键，粘贴代码:shift +Insert键】
print(text) 

input() # python 运行暂停方式之一，按enter键继续

print("17/3=",17/3) # 除法：返回float类型
print(17//3,"= 17//3 -----\n") # \n换行； 除法：返回商int类型(无余数)
print("17%3=",17%3,"-----\n") #  求余/取模
print("5*3+2=",5*3+2)
print("5*(3+2)",5*(3+2)) # 遵循先括号后乘除再加减

input()

print("5**2=",5**2) # 平方
print("3**4=",3**4) # 3的4次方
# SyntaxError: (unicode error) 'utf-8' codec can't decode byte 0xd5 in position 0: invalid continuation byte
# 编码问题，解决方法：将编辑器的编码也改为UTF-8
print("\n 整数和浮点数的混合计算中，整数会被转换为浮点数 \n")
print("3*1.25/0.25=",3*1.25/0.25)
print("7.0/4=",7.0/4)

print("round(3.1415926535898,2)=",round(3.1415926535898,2)) # round() 方法返回浮点数x的四舍五入值。

input()
