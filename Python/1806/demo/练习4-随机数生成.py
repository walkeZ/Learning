'''
需求：# 生成 0 ~ 9 之间的随机数
    标准库random(随机数模块)
    random.randint(a,b)
    函数返回数字 N ，N 为 a 到 b 之间的数字（a <= N <= b），包含 a 和 b。
'''
import random

for x in range(25):
    print('x = ', x, '----------->随机数为：', random.randint(0, 9))  # 随机数包含0,9

# 随机数小游戏：试试你与电脑的心灵相通
count = 1  # 输入次数
a = random.randint(0,100)   # 电脑生成的随机数
b = int(input('请输入0~100中的一个数字\n然后查看是否与电脑一样：'))
while a!=b:
    if b<a:
        print('你第%d次输入的数字小于电脑随机数'%count)
        print('你输入的数字是%d,电脑随机数%d'%(b,a))
        b=int(input('请再次输入数字：'))
    else:
        print('你第%d次输入的数字大于于电脑随机数'%count)
        print('你输入的数字是%d,电脑随机数%d' % (b, a))
        b=int(input('请再次输入数字：'))
    count+=1
else:
    print('恭喜你，你第%d次输入的数字与电脑的随机数%d一样'%(count,b))

