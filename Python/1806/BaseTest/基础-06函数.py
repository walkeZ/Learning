# -*— coding=utf-8 -*-

'''   PS: 电脑快捷键：Ctrl+Alt+s,查看机器硬件
定义一个函数
    函数代码块以 def 关键词开头，后接函数标识符名称和圆括号 ()。
    任何传入参数和自变量必须放在圆括号中间，圆括号之间可以用于定义参数。
    函数的第一行语句可以选择性地使用文档字符串—用于存放函数说明。
    函数内容以冒号起始，并且缩进。
    return [表达式] 结束函数，选择性地返回一个值给调用方。不带表达式的return相当于返回 None。
语法：
    Python 定义函数使用 def 关键字，一般格式如下：
    def 函数名（参数列表）:
        函数体
'''
import sys                      # 引入 sys 模块

# 定义一个函数 生成获取0到n的和
def getSum(n): # def 方法定义关键字，getSum 方法名，n 传入参数
    "求0到n的和"  # 这样(方法名下第一行)相当于Java方法注释，在使用的地方“按‘Ctrl’键,鼠标移动到方法名即可查看”
    pass # pass 使用，当你还没想好具体实现时用pass，避免编译报错
    sum=0
    while n>0:
        sum+=n     # 次数，用于与传入n比较
        n-=1
    return sum
def getArea(width,height):
    return width*height

def print_welcome(name):
    print('welcome',name)

f=getSum(10)
print(f)
print_welcome('walke')
print(getArea(6,8))

def print_welcome2(name):
    print('welcome',name)

print_welcome2('walke2')

'''
参数传递
    在 python 中，类型属于对象，变量是没有类型的：
    a=[1,2,3]
    a="Runoob"
    以上代码中，[1,2,3] 是 List 类型，"Runoob" 是 String 类型，而变量 a 是没有类型，
    她仅仅是一个对象的引用（一个指针），可以是指向 List 类型对象，也可以是指向 String 类型对象。

可更改(mutable)与不可更改(immutable)对象
在 python 中，strings, tuples, 和 numbers 是不可更改的对象，而 list,dict 等则是可以修改的对象。
    不可变类型：变量赋值 a=5 后再赋值 a=10，这里实际是新生成一个 int 值对象 10，再让 a 指向它，而 5 被丢弃，不是改变a的值，相当于新生成了a。
    可变类型：变量赋值 la=[1,2,3,4] 后再赋值 la[2]=5 则是将 list la 的第三个元素值更改，本身la没有动，只是其内部的一部分值被修改了。
    
'''
#   python 传不可变对象实例
def change_int(n):
    pass
    n=n+10
    return n
a=5
i=change_int(a)
print('a=',a,end=' , ')
print('i=',i)               # --: a=5 , i=15

# Python 传可变对象实例

def change_list(tagList):
    "修改传入的列表" # 这样(方法名下第一行)相当于Java方法注释，在使用的地方“按‘Ctrl’键,鼠标移动到方法名即可查看”
    tagList.append([1,2,3,4])
    print('函数内取值：',tagList)
    return
list=[10,20,30]
change_list(list)
print('函数外取值：',list)

'''
关键字参数
    使用关键字参数允许函数调用时参数的顺序与声明时不一致，
'''
def printme(name,age):
    print('name=',name,'  age=',age)

printme(age=26,name='walke')

'''
默认参数,在定义方法时给个初始值，调用时可以不传入改参数；例子如下
    调用函数时，如果没有传递参数，则会使用默认参数。以下实例中如果没有传入 age 参数，则使用默认值：
'''
def printInfo(name,age=12):
    print('name=', name, '  age=', age)
printInfo('walke')

# def printInfo(name='Jack',age):  # 编译报错：non-default parameter follows default parameter  将没有默认值的参数在定义时放在了有默认值的参数的后面

def printInfo2(name,age=26,sex=True):
    print('name:',name,'age:',age,'sex',sex)

printInfo2('walke',False)           # --: name: walke age: False sex True
printInfo2('walke',sex=False)           # --: name: walke age: 26 sex False

'''
不定长参数
你可能需要一个函数能处理比当初声明时更多的参数。这些参数叫做不定长参数，和上述 2 种参数不同，声明时不会命名。基本语法如下：
    def functionname([formal_args,] *var_args_tuple ):
       "函数_文档字符串"
       function_suite
       return [expression]
加了星号 * 的参数会以元组(tuple)的形式导入，存放所有未命名的变量参数。
加了两个星号 ** 的参数会以字典的形式导入。
'''
def printInfo(arg1,*vartuple):
    "打印任何输入的参数"
    print('printInfo(arg1,*vartuple) 输出：')
    print('agr1:',arg1,'vartuple:',vartuple)            # --：agr1: 12 vartuple: (13, 14, 15)
# 调用printInfo方法
printInfo(12,13,14,15)


# 可写函数说明
def printInfo(arg1, *vartuple):
    "打印任何传入的参数"
    print("输出: ")
    print(arg1)
    for var in vartuple:
        print(var)
    return


# 调用printinfo 函数
printInfo(10)
printInfo(70, 60, 50)

# 加了两个星号 ** 的参数会以字典的形式导入。
def printInfo3(arg1,**varDict):
    "打印任何传入的参数"
    # pass
    print('arg1:',arg1,'varDict:',varDict)

printInfo3(1,a=2,b=3,c=4)           # --: arg1: 1 varDict: {'a': 2, 'b': 3, 'c': 4}

'''
声明函数时，参数中星号 * 可以单独出现，例如:
    如果单独出现星号 * 后的参数必须用关键字传入。
'''
def f(a,b,*,c):
    return a+b+c
# f(1,2,3)   # 报错  TypeError: f() takes 2 positional arguments but 3 were given
print(f(1,2,c=3))

'''
匿名函数
python 使用 lambda 来创建 匿名函数。
所谓匿名，意即不再使用 def 语句这样标准的形式定义一个函数。
    lambda 只是一个表达式，函数体比 def 简单很多。
    lambda的主体是一个表达式，而不是一个代码块。仅仅能在lambda表达式中封装有限的逻辑进去。
    lambda 函数拥有自己的命名空间，且不能访问自己参数列表之外或全局命名空间里的参数。
    虽然lambda函数看起来只能写一行，却不等同于C或C++的内联函数，后者的目的是调用小函数时不占用栈内存从而增加运行效率。
'''
# 可写函数说明
sum = lambda arg1, arg2: arg1 + arg2   #主体是一个表达式
print("sum(10,12)=",sum(10,12))

# testt= lambda arg1,arg2,arg3:arg1+(arg2*arg3)  # TypeError: must be str, not int
test= lambda arg1,arg2,arg3:arg1+str(arg2*arg3)
print(test('两个数的乘积是：',3,4))         # --: 两个数的乘积是：12

'''
return语句
    return [表达式] 语句用于退出函数，选择性地向调用方返回一个表达式。不带参数值的return语句返回None。
'''
def sum(a,b):
    sum=a+b
    print("函数内：sum=",sum)
    return sum
print("调用函数sum(5,3)=",sum(5,3))
'''
变量作用域
Python 中，程序的变量并不是在哪个位置都可以访问的，访问权限决定于这个变量是在哪里赋值的。
变量的作用域决定了在哪一部分程序可以访问哪个特定的变量名称。Python的作用域一共有4种，分别是：
    L （Local） 局部作用域                                           小
    E （Enclosing） 闭包函数外的函数中[可以理解：局部变量的一种]
    G （Global） 全局作用域
    B （Built-in） 内建作用域                                        大
    以 L –> E –> G –>B 的规则查找，即：在局部找不到，便会去局部外的局部找（例如闭包），再找不到就会去全局找，再者去内建中找。
'''
x=int(3.14)                         # int()的作用域是内建作用域(最大)，是Python系统Api
g_count=0                           # 当前文件的全局作用域
print('x=',x,'g_count=',g_count)
def outer():
    e_count=1                       # 闭包函数外的函数中
    print('x=',x,'g_count=',g_count,'e_count=',e_count)
    def inner():                    # 方法内的子方法在同一层调用，这是闭包函数
        l_count=2                   # 局部作用域
        print('l_count=',l_count)
    inner()                         # 调用方法内的子方法
    # print(l_count)                  # 这里访问不到i_count，编译报错
outer()
# print("end-----> x=",x,'g_count=',g_count,"ec=",e_count,'lc=',l_count)  # 这里访问不到o_count和l_count，编译报错

'''
Python 中只有模块（module），类（class）以及函数（def、lambda）才会引入新的作用域，其它的代码块
（如 if/elif/else/、try/except、for/while等）是不会引入新的作用域的，也就是说这些语句内定义的变量，外部也可以访问，如下代码：
'''
if True:
    msg=123
print('msg=',msg)

'''
全局变量和局部变量
    定义在函数内部的变量拥有一个局部作用域，定义在函数外的拥有全局作用域。
    局部变量只能在其被声明的函数内部访问，而全局变量可以在整个程序范围内访问。
    调用函数时，所有在函数内声明的变量名称都将被加入到作用域中
'''
total=100                     # 全局变量
def sum(a,b):               # Python 中同一文件中可以有相同(172行)的方法定义，由于Python的执行顺序是由上而下的，
                            # 故后面方法将替换前面的方法,变量一样
    pass
    total=a+b
    print("函数里：total = ",total)         # --:30
    return total
def sum3(a, b):
    pass
    n=total+a+b
    print("函数里：total = ",n)
    return n
sum(10,20)
print('sum2(2,3)=', sum3(2, 3))
print('函数前的全局变量：total=',total)  # --:0 发现 全局的total=0没变，
                                        # 当函数内再声明一个与全局变量命名一样的变量时，是一个新的指引(变量)了，与Java不同

def sum2(b):
    pass
    # total=total+b          # 不能这样写，有语义冲突,total=total+b中等号左边是"新命名一个指引"，右边是全局变量，
                            # 错误信息为局部作用域引用错误

''' global(全局) 和 nonlocal(局部)关键字
        当内部作用域想修改外部作用域的变量时，就要用到global和nonlocal关键字了。
'''
num=1
def fun1():
    global num
    print('global num=',num)
    num=123
fun1()
print('全局num=1的--> num=',num)               # num=123，此时改变了全局的num

''' 如果要修改嵌套作用域（enclosing 作用域，外层非全局作用域）中的变量则需要 nonlocal 关键字了，如下实例：
'''
def outer():
    num=10
    def inner_change():
        nonlocal num            # nonlocal关键字声明,相当于锁定了上一层的局部变量num【enclosing】，故是对上一层的num执行了有效修改
        print("inner_change(),num改值前：num=",num)
        num=100
    inner_change()
    print('inner_change()调用后',num)
outer()







