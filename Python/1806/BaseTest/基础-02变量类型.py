# -*- coding:utf-8 -*-
'''
参考：http://www.runoob.com/python3/python3-data-type.html
    1.Python 中的变量赋值不需要类型声明,每个变量在内存中创建，都包括变量的标识，名称和数据这些信息。
             每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建。
             当变量赋值后"="右边开辟内存空间，左边是持有该内存空间的引用[类似java]
    2.变量赋值 "=" 将右边的值赋值给左边的变量

'''

num = 100  # 赋值整型变量
miles = 1000.010  # 赋值浮点型变量
name = 'walke'  # 字符串，用''或""皆可
# print("num = "+num+"miles = "+miles+"name = "+name) # Error: TypeError: must be str, not int
print(num, miles, name)
# print("num = "+num) # 类型异常
print('num = ', num), print('name = ' + name)
print('num = ', num, ', miles = ', miles, ', name = ', name)

# 多变量赋值
x = y = z = 12  # 该实例，创建一个整型对象，值为1，三个变量被分配到相同的内存空间上。
print('type(x):', type(x), 'type(y):', type(y), 'type(z):', type(z))
a, b, c = 1, 2.0, 'walke'  # 该实例，两个整型对象1和2的分配给变量 a 和 b，字符串对象 "john" 分配给变量 c。
print('type(a):', type(a), 'type(b):', type(b), 'type(c):',
      type(c))  # type(a): <class 'int'> type(b): <class 'float'> type(c): <class 'str'>

'''
标准数据类型
  在内存中存储的数据可以有多种类型。
  例如：一个人的年龄可以用数字来存储，他的名字可以用字符串来存储。
  Python定义了一些标准类型，用于存储各种类型的数据
  Python有5个标准的数据类型：
    Numbers(数字)
    String(字符串)
    List(列表)
    Tuple(元组)
    Dictionary(字典)
'''
'''Python 数字  数字数据类型用于存储 数值
# 他们是不可改变的数据类型，这意味着改变数字数据类型会分配一个新的对象
# 当你指定一个值时，Numbers对象就会被创建
    Python支持4种不同的数据类型
        int(有符号整型)
        long(长整型，也可以代表八进制/十六进制)
        float(浮点型)
        complex(复数)
'''
var1 = 11
var2 = 22
# 使用del删除对象引用
print('var1=', var1, ',var2=', var2)
del var1, var2
# print('var1=',var1,',var2=',var2) # NameError: name 'var1' is not defined

'''
  Python字符串: 字符串或串(String)是由数字、字母、下划线组成的一串字符。[基本与Java一样]
    从左到右索引默认0开始的，最大范围是字符串长度少1
    从右到左索引默认-1开始的，最大范围是字符串开头
'''
print('-------------分割线(字符串)--------------')
s = 'walke_hello_10086'
n = len(s)
print('n = ', n)  # 输出字符串长度
print('s[0]', s[0])  # 输出字符串的第一个字符
print(s[6:11])  # 输出第7个到第第11个之间的字符串
print(s[5:])  # 输出第六个字符开始的字符串
print(s * 2)  # 输出字符串2次
print(s + "_886")  # 输出连接的字符串

'''
  Python列表 ：支持字符，数字，字符串甚至可以包含列表（即嵌套）。
    列表用[]标识，是Python最通用的复合型数据
    从左到右索引默认 0 开始，从右到左索引默认 -1 开始，下标可以为空表示取到头或尾。
'''
print('-------------分割线(List)--------------')
list = ['10086', 'walke', 3.14235, 'Jone', 'Jack', 59.50]
tinyList = ['10086', 10086]
print('list:', list)  # 输出完整列表
print('list[0]：', list)  # 输出列表的第一个元素
print('list[1:4]：', list[1:4])  # 输出列表的第二个到第四个元素
print('list[2:]：', list[2:])  # 输出列表的第三个到最后一个元素
print('tinyList*2：', tinyList * 2)  # 输出列表两次
print('list+tinyList：', list + tinyList)  # 输出组合的列表

'''
  元组是另一个数据类型，类似于List（列表）。
    元组用"()"标识。内部元素用逗号隔开。但是元组不能二次赋值，相当于只读列表。
'''
print('-------------分割线(Tuple元组)--------------')
tuple = ('10086', 'walke', 3.14235, 'Jone', 'Jack', 59.50)
tinyTuple = ('10086', 10086)
print('tuple:', tuple)  # 输出完整元组
print('tuple[0]：', tuple)  # 输出元组的第一个元素
print('tuple[1:4]：', tuple[1:4])  # 输出元组的第二个到第四个元素
print('tuple[2:]：', tuple[2:])  # 输出列表的第三个到最后一个元素
print('tinyTuple*2：', tinyTuple * 2)  # 输出元组两次
print('tuple+tinyTuple：', tuple + tinyTuple)  # 输出组合的元组

# List与Tuple区别：元组是不允许更新的。而列表是允许更新的：
print('-------------分割线(Tuple、List区别)--------------')
list[2] = 3.14
print('list[2] = ', list[2])
# 异常： https://blog.csdn.net/HongKong_Python/article/details/78950194
try:
    tuple[2] = 3.14  # 'tuple' object does not support item assignment
    print('tuple[2] = ', tuple[2])
except Exception as error:
    print('error:', error)

print('len(tuple)',len(tuple))

'''
  Python 字典 类似Java的Map
    字典(dictionary)是除列表以外python之中最灵活的内置数据结构类型。列表是有序的对象集合，字典是无序的对象集合。
    两者之间的区别在于：字典当中的元素是通过键来存取的，而不是通过偏移存取。
    字典用"{ }"标识。字典由索引(key)和它对应的值value组成。
'''
print('-------------分割线(Dictionary)--------------')
dict = {}
dict['one'] = 'this is one'  # key为one，value是this is one。
dict[2] = 2
dict['two'] = 'This is two'
tinyDict = {'name': 'walke', 'age': 18, 'sex': True, 'email': 'xxx163.com'}
print('dict[\'one\']：', dict['one'])                         # 输出key为'one'的值
print('dict[2]：', dict[2])                                   # 输出key为2的值
print('dict[\'two\']：',dict['two'])                          # 输出key为'two'的值
print('tinyDict：',tinyDict )                                 # 输出完整的字典
print('tinyDict.keys()：',tinyDict.keys())                    # 输出字典的所有key
print('tinyDict.values()：',tinyDict.values())                # 输出字典的所有Value
print('type(tinyDict_sex)',type(tinyDict['sex']))             # 输出key为'sex'的类型
print('type(tinyDict_age)',type(tinyDict['age']))             # 输出key为'age'的类型
print('type(tinyDict_keys)',type(tinyDict.keys()))             # 输出key为'age'的类型
print('type(tinyDict_values)',type(tinyDict.values()))             # 输出key为'age'的类型

'''
  Python类型转换 ：数据类型的转换，你只需要将数据类型作为函数名即可
'''
print('-------------分割线(x=\'10\')--------------')
x='10'
print("int(x)=",int(x))
print("float(x)=",float(x))










