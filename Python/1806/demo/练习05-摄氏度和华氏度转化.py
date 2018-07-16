'''
需求：# 用户输入摄氏温度s，输出华氏度h，h=(s*1.8)+32

# 接收用户输入
'''

# celsius = float(input('输入摄氏度：'))
# fahrenheit=(celsius*1.8)+32
# print('华氏温度：fahrenheit = ',fahrenheit)

print("-----------------方法&装饰器---------------------")

# 装饰器
def outer(func):
    def inner():
        print('装饰器')
        func()      # 这里要括号
    return inner    # 这里不用括号

# 封装方法
@outer      # 引用装饰器
def c_t_f():
    celsius = float(input('c_t_f输入摄氏度：'))
    fahrenheit = (celsius * 1.8) + 32
    print('摄氏温度：celsius = ', celsius,'  华氏温度：fahrenheit = ', fahrenheit)

# 华氏度 转 摄氏度
def ftc():
    f=float(input('ftc 请输入华氏度：'))
    c=(f-32)/1.8
    print('华氏度：f = ',f,'   摄氏度 c = ',c)



ftc=outer(ftc)
ftc()       # 调用要括号

c_t_f()




