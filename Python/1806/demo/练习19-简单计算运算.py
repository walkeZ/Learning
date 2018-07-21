'''     Python 简单计算运算：加减乘除
'''
# 加
def add(a,b):
    '''加法'''
    return a+b
# 减
def minus(a,b):
    '''减法'''
    return a-b
# 乘
def multiply(a,b):
    '''乘法'''
    return a*b
# 除
def divide(a,b):
    '''除法'''
    return a/b

print('选择运算：')
print('1.加法')
print('2.减法')
print('3.乘法')
print('4.除法')

num1=int(input('请输入第一个数'))
num2=int(input('请输入第二数'))

for i in range(4):
    choise = input('请选择运算方法')
    # if choise.__eq__(1):  输入5/6/8也满足条件，估计是都相等的，不能用
    if choise=="1":  # choise 是字符型
        print(num1,' + ',num2,' =  ',add(num1,num2))
    elif choise=='2':
        print(num1,' - ',num2," = ",minus(num1,num2))
    elif choise=='3':
        print(num1,' * ',num2,' = ',multiply(num1,num2))
    else:
        print(num1,' / ',num2,' = ',divide(num1,num2))









