'''     Python 最大公约数、最小公倍数
'''

# 求两个数的最大公约数

def hcf(a,b):
    """"该函数返回两个数的最大公约数"""
    pass
    if a>b:
        samller=b
    else:
        samller=a
    temp=1
    for x in range(1,samller):
        if a%x==0 and b%x==0:
            temp=x
    print('{} 和 {} 的 最大公约数是：{}'.format(a,b,temp))

hcf(12,8)
hcf(35,49)

import math
# 求两个数的最小公约数
def lcm(x,y):
    ''' 该方法返回两个数的最小公倍数 '''
    bigNum=max(x,y)
    while True:
        if bigNum%x==0 and bigNum%y==0:
            print('{} 和 {} 的最小公倍数是： {}'.format(x,y,bigNum))
            return bigNum
        bigNum+=1
lcm(5,6)
lcm(10,8)






