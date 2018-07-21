# -*— coding=utf-8 -*-

'''   PS: 装饰器
'''

def isNumber(a):
    try:
        int(a)
        return True
    except:
        return False

def divide(a,b):
    return a/b
try:
    print(divide(5,0))
except ZeroDivisionError as e:
    print('出现异常：',e)

def myDivide(function):
    def inner(a,b):
        if isNumber(a) and isNumber(b):
            if b==0:
                print('0不可以做除数')   # 除号前面的是“被除数”，除号后面的是“除数”
            else:
                function(a,b)
    return inner    # 返回变量(方法名即可)

print('-----装饰后------')
# 方法也算(是)一种数据类型(变量)
divide=myDivide(divide)     #
divide(5,0)

@myDivide       # 使用myDivide装饰器，等价于divide2=myDivide(divide2)
def divide2(a,b):
    return a/b

print("divide2:")
divide2(5,0)







