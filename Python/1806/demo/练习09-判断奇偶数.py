
'''     Python 判断字符串是否为数字
以下实例通过创建自定义函数is_number()方法来判断字符是否为数字
'''

def is_odd(s):  # odd: 奇数
    pass
    if s%2==0:
        print(s,' 是偶数')
        return True
    else:
        print(s, ' 是奇数')
        return False

num=float(input('请输入一个数'))
while is_odd(num):  # 循环，便捷测试
    num = float(input('请输入一个数'))

'''
感觉方法不够细，要增加检测传入数字是否为整数
方案：装饰器
'''

def my_isOdd(func):
    def inner(num):
        try:
            num=int(num)
            func(num)
        except ValueError:
            print('你传入的',num,'不是整数')
    return inner    # 返回一个‘对象’
'''
 使用装饰器，传入方法名即可不用括号，用一个变量名接收，一般用之前的方法名。
 原因：一般在同一模块使用时，都需要用改进后的方法。 
'''
is_odd=my_isOdd(is_odd)
is_odd(input('请输入数字：'))

print('---------注解装饰器----------')
@my_isOdd
def is_odd2(s):  # odd: 奇数
    pass
    if s%2==0:
        print(s,' 是偶数')
        return True
    else:
        print(s, ' 是奇数')
        return False

is_odd2(3.7)        #  --：3  是奇数 ，有点异常， 结合后面案例这是因为装饰器my_isOdd中int(num)转化了3.3为3，发现：int()转化只取整数部分，不是四舍五入

print('---------is_odd3----------')
@my_isOdd
def is_odd3(s):  # odd: 奇数
    pass
    if s%2==0:
        print(s,' 是偶数')
        return True
    else:
        print(s, ' 是奇数')
        return False
is_odd3(input('请输入数字：'))   # --：你传入的 3.3 不是整数

print('---------my_isOdd2----------')

def my_isOdd2(func):
    def inner(num):
        try:
            num=float(num)  # 3.3  可转化
            func(num)
        except ValueError:
            print('你传入的',num,'不是float')
    return inner    # 返回一个‘对象’

@my_isOdd2
def is_odd4(s):  # odd: 奇数
    pass
    if s%2==0:
        print(s,' 是偶数')
        return True
    else:
        print(s, ' 是奇数')
        return False
is_odd4(input('请输入数字：'))        #  3.3  是奇数，