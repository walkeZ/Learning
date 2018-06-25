# -*— coding=utf-8 -*-
# http://www.runoob.com/python3/python3-conditional-statements.html
''' Python中if语句一般形式如下所示
    if condition_1:
        doSomething1...
    elif condition_2:
        doSomething2...
    ...
    else :
        doSomething

常用操作符：
<	小于
<=	小于或等于
>	大于
>=	大于或等于
==	等于，比较对象是否相等
!=	不等于
'''

var1 = 100
if var1:
    print('1,if表达式条件为True', end=' , ')
    print('var1=', var1)
var2 = 0
if var2:
    print('2，if表达式条件为True', end=' , ')
    print('var2=', var2)
var3 = 1
if var3:
    print('3，if表达式条件为True', end=' , ')
    print('var3=', var3)
var3 = -1
if var3:
    print('4，if表达式条件为True', end=' , ')
    print('var3=', var3)
print('Good bye!')  # 发现 var2=0时没执行if内的语句，估计默认(隐式)的判断语句为value!=0

print('-----------分割线----------')
# 小例子
age = int(input('请输入你家狗狗的年龄：\n'))
print('输入为：', age, end=' , ')
if age < 0:
    print('你是在逗我吧')
elif age == 1:
    print('相当于14的人。')
elif age == 2:
    print('相当于22岁的人。')
elif age > 2:
    human = 22 + (age - 2) * 5
    print('对应人类年龄：', human)
else:
    print('你家狗宝宝刚出生')
input('点击enter键退出')

print('-----------分割线----------')
print('5==6:', 5 == 6)
x = 5
y = 5
z = '5'
print('x=5  y=5  z=\'5\'', )
print('x==y:', x == y, end=' , ')
print('x==z:', x == z)

print('-----------猜字游戏----------')
number = 17
guess=-1
while guess!=number:
    guess=int(input('请输入你猜的数字：(1-100)'))
    if guess==number:
        print('恭喜，你猜对了！')
    elif guess<number:
        print('恭喜，你猜的数字小了！\n')
    else :
        print('恭喜，你猜的数字大了！\n')

''' if 嵌套 
        if 表达式1:
            语句
            if 表达式2:
                语句
            elif 表达式3:
                语句
            else:
                语句
        elif 表达式4:
            语句
        else:
            语句
'''
print('-----------if 嵌套 ----------')
temp=0
while temp<3:
    temp+=1

    num=int(input('请输入一个数字：'))
    if num%2==0:
        if num%3==0:
            print('你输入的数可以被2或3整除')
        elif num%5==0:
            print('你输入的数是2和5的公倍数[可以被2或5整除]')
        else:
            print('你输入的数是偶数')
    else:
        print('你输入的数是奇数')
