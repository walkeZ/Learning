
'''     Python 交换变量

'''
# 简单实现案例
x=input('输入x的值')
y=input('输入y的值')
temp = x
x = y
y = temp
print('x = ', x, "   y = ", y)

print('-----------方法-------------')

def changXY(x,y):
    temp=x
    x=y                             # 结合后面的报错情况，这里的x估计是一个新的引用，与全局(方法外/前)不一样了
    y=temp
    print('x = ',x,"   y = ",y)
changXY(x,y)

# Python 优化写法
def changXY2(x,y):
    x,y=y,x
    print('x = ',x,"   y = ",y)

changXY2(x,y)


# Python 作用域复习
def changXY2():
    temp = x
    x1 = y
    y1 = temp
    print('x = ',x,"   y = ",y)

changXY2()

# Python 作用域复习
def changXY2():
    temp = x  # unresolved reference 未解决的引用 这个x变量，
    x = y
    x1 = y
    y1 = temp
    print('x = ',x,"   y = ",y)

changXY2()