# -*— coding=utf-8 -*-

'''
迭代器
迭代是Python最强大的功能之一，是访问集合元素的一种方式。
'''
import sys                      # 引入 sys 模块

list =[1,2,3,4,5]
it=iter(list)                   # 创建迭代器对象
# print(next(it))                 # 输出迭代器的下一个元素

# 遍历1
# for x in it:
#     print(x,end=' , ')

# 遍历2
while True:
    # print(next(it))         #   报错了 Error: StopIteration
    try:
        print(next(it))
    except StopIteration:
        # pass
        print('')           # 这里会执行到，与java不同，不是捕获到异常才执行到
        sys.exit()

'''
生成器
在 Python 中，使用了 yield 的函数被称为生成器（generator）。
跟普通函数不同的是，生成器是一个返回迭代器的函数，只能用于迭代操作，更简单点理解生成器就是一个迭代器。
在调用生成器运行的过程中，每次遇到 yield 时函数会暂停并保存当前所有的运行信息，返回 yield 的值, 并在下一次执行 next() 方法时从当前位置继续运行。
调用一个生成器函数，返回的是一个迭代器对象。
'''
''' PS : 发现走了前面的代码这方法定义及后面的没再执行了，当将前面的代码全部注释，又可以执行了
         估计与方法定义方面有关，其实与 sys.exit() 有关
         
'''
# 定义一个函数 生成 斐波那契数列
def fibobacci(n): # def 方法定义关键字，fibobacci方法名，n传入参数
    pass # pass 使用，当你还没想好具体实现时用pass，避免编译报错
    a,b=0,1
    count=0     # 次数，用于与传入n比较
    while count<n:
        yield a             # 使用yield
        count+=1
        a,b=b,a+b

f=fibobacci(10)
print(f)
while True:
    try:
        print(next(f),end=",")
    except StopIteration:
        sys.exit()

