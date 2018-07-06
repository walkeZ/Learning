# -*- coding:utf-8 -*-
'''  http://www.runoob.com/python3/python3-module.html
    Python3 模块（泛指.py文件）
在前面的几个章节中我们脚本上是用 python 解释器来编程，如果你从 Python 解释器退出再进入，那么你定义的所有的方法和变量就都消失了。
为此 Python 提供了一个办法，把这些定义存放在文件中，为一些脚本或者交互式的解释器实例使用，这个文件被称为模块。
模块是一个包含所有你定义的函数和变量的文件，其后缀名是.py。模块可以被别的程序引入，以使用该模块中的函数等功能。这也是使用 python 标准库的方法。
下面是一个使用 python 标准库中模块的例子。
'''
import sys                             #引入 python 标准库中的 sys.py 模块；这是引入某一模块的方法。
print('命令参数如下：')
for i in sys.argv:                     # sys.argv 是一个包含命令行参数的列表
    print('i:',i)

print('\n\nPython 路径为： ',sys.path,'\n') # sys.path 包含了一个Python解析器自动查找所需模块的路径列表。

'''import语句
想使用Python 源文件，只需在另一个源文件中执行import语句，语法如下：
import model1,model2,...
'''
# import 基础-08-1模块  #  有中文，编译报错了,执行也报错：SyntaxError: invalid syntax，原因中划线识别为减号了
import base_test1   # 编译器报错了，但能有效执行特点：同级文件夹，
import basetest   # 编译器报错了，但能有效执行特点：同级文件夹，
import BaseTest   # 编译器正常了，但能有效执行特点：同级文件夹，
import base_test2   # 成功了，
import 基础_08_2模块

base_test1.print_fun1('fun1--walke')
base_test2.print_fun2('fun2--walke')
basetest.print_fun('fun--walke')        # 如果没使用import，执行也报错，
# BaseTest.print_fun('fun--walke')        #  使用import 但报错了 AttributeError: module 'BaseTest' has no attribute 'print_fun'
基础_08_2模块.print_fun('fun--walke')    ##  有中文，编译报错了，能执行

'''
一个模块只会被导入一次，不管你执行了多少次import。这样可以防止导入模块被一遍又一遍地执行。
'''

import fibo2        # 上一级文件夹中
# import fibo1        # 下一级文件夹中  编译、运行报错，

fibo2.fib(100)
print('-----fib2(100)-----')
t=fibo2.fib2(100)
print(t)
# fibo1.fib(10)
# print('-----fib1(100)-----')
# t=fibo1.fib2(100)
# print(t)

''' from ... import 语句
Python 的 from 语句让我们可以从模块中导入
from 'modname' import *
这提供了一个简单的方法来导入一个模块中的所有项目。然而这种声明不该被过多地使用。

    深入模块
模块除了方法定义，还可以包括可执行的代码。这些代码一般用来初始化这个模块。这些代码只有在第一次被导入时才会被执行。
每个模块有各自独立的符号表，在模块内部为所有的函数当作全局符号表来使用。
所以，模块的作者可以放心大胆的在模块内部使用这些全局变量，而不用担心把其他用户的全局变量搞花。
从另一个方面，当你确实知道你在做什么的话，你也可以通过 modname.itemname 这样的表示法来访问模块内的函数。
模块是可以导入其他模块的。在一个模块（或者脚本，或者其他地方）的最前面使用 import 来导入一个模块，当然这只是一个惯例，而不是强制的。被导入的模块的名称将被放入当前操作的模块的符号表中。
还有一种导入的方法，可以使用 import 直接把模块内（函数，变量的）名称导入到当前操作模块。比如:
'''
from fibo2 import fib,fib2
fib(100)
t=fib2(100)
print(t)
# fib3(100)     # 用不了没引用/声明
'''
这种导入的方法不会把被导入的模块的名称放在当前的字符表中（所以在这个例子里面，fibo 这个名称是没有定义的）。
这还有一种方法，可以一次性的把模块中的所有（函数，变量）名称都导入到当前模块的字符表:
import *：这将把所有的名字都导入进来，但是那些由单一下划线（_）开头的名字不在此例。大多数情况， Python程序员不使用这种方法，因为引入的其它来源的命名，很可能覆盖了已有的定义。
'''
from fibo2 import *

fib3(100)       # 可以用

'''     __name__属性
一个模块被另一个程序第一次引入时，其主程序将运行。如果我们想在模块被引入时，模块中的某一程序块不执行，我们可以
用__name__属性来使该程序块仅在该模块自身运行时执行。
'''
import using_name















