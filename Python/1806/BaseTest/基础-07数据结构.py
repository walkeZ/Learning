# -*— coding=utf-8 -*-
'''
列表
Python中列表是可变的，这是它区别于字符串和元组的最重要的特点，一句话概括即：列表可以修改，而字符串和元组不能。
'''
a = [3.1425, 33, 12, 12, 12.5, "walke", 'hello world']
print(a)
print("12在列表出现的次数", a.count(12), " w在列表出现的次数", a.count('w'), )  # 12在列表出现的次数 2  w在列表出现的次数 0
# print(a.sort()) # TypeError: '<' not supported between instances of 'str' and 'int'
b = [14, 8, 9, 15, 16, 25]
b.sort()
print('b.sort()', b)
a.append('世界杯')
print(a)
a.extend(b)
print(a)
a.remove(12)  # 移除第一个值为12的元素
print(a)
a.insert(1, 'insert')
print(a)
print(a.index('walke'))
print(a.copy())
print(type(a.copy()))
print(a.pop(5))
print(a.pop())
print(a)

'''
将列表当做堆栈使用
列表方法使得列表可以很方便的作为一个堆栈来使用，堆栈作为特定的数据结构，最先进入的元素最后一个被释放（后进
先出）。用 append() 方法可以把一个元素添加到堆栈顶。用不指定索引的 pop() 方法可以把一个元素从堆栈顶释放出来。
'''
print('---------------列表当做堆栈使用-----------------')
stack = [2, 3, 4]
print(stack)
stack.append(5)
stack.append(6)
print(stack)
stack.pop()
print(stack)
''' 将列表当作队列使用
也可以把列表当做队列用，只是在队列里第一加入的元素，第一个取出来；但是拿列表用作这样的目的效率不高。在列表的
最后添加或者弹出元素速度快，然而在列表里插入或者从头部弹出速度却不快（因为所有其他的元素都得一个一个地移动）。
'''
print('---------------列表当做队列使用-----------------')
from collections import deque

queue = deque(['Jone', 'Jack', 'Toms'])
print(queue)
queue.append('Terry')
queue.append('Walke')
print(queue)
print(queue.popleft())
print(queue.popleft())
print(queue)
'''列表推导式
列表推导式提供了从序列创建列表的简单途径。通常应用程序将一些操作应用于某个序列的每个元素，
用其获得的结果作为生成新列表的元素，或者根据确定的判定条件创建子序列。每个列表推导式都在 
for 之后跟一个表达式，然后有零到多个 for 或 if 子句。返回结果是一个根据表达从其后的 for 
和 if 上下文环境中生成出来的列表。如果希望表达式推导出一个元组，就必须使用括号。
'''
print('------将列表中每个数值乘三，获得一个新的列表------')
vec = [2, 3, 5]
vec2 = [4 * n for n in vec]
print(vec2)
print([4 * n for n in vec if n < 3])  # --：[8],针对条件元素(元素<3)做处理,并返回处理结果
print([4 * n for n in vec if n < 2])  # --：[]，对应条件无元素

vec3 = [2, 4, 5]
vec4 = [4, -2]
print([x * y for x in vec3 for y in vec4])  # --:[8, -4, 16, -8, 20, -10],语句含义是vec3的每个元素乘以vec4的每个元素
print([x + y for x in vec3 for y in vec4])  # --:[6, 0, 8, 2, 9, 3]
'''
三目运算符 在python中的格式为
    为真时的结果 if 判定条件 else 为假时的结果  
'''
b = len(vec3) if len(vec3) < len(vec4) else len(vec4)

print([vec3[i] * vec4[i] for i in range(b)])  # --: [8, -8]

# 三目运算符
b = True
print('b=True' if b else 'b=False')
b = 10
print('b>=0' if b > 0 else 'b<0')
test = [str(round(355 / 113, i)) for i in range(1, 6)]  # round() 方法返回浮点数x的四舍五入值。
print(test)
import math

test = [str(round(math.pi, i)) for i in range(1, 6)]
" python中的递归问题，求圆周率 https://www.cnblogs.com/pinking/p/7944783.html "
print(test)  # --: ['3.1', '3.14', '3.142', '3.1416', '3.14159']

matrix = [  # 这是一个3*4的矩阵列表
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12]
]
print(matrix)
# 将3X4的矩阵列表转换为4X3列表 方式一：
print([[row[i] for row in matrix] for i in range(4)])
# 将3X4的矩阵列表转换为4X3列表 方式二：
transposed = []
for n in range(4):
    transposed.append([row[n] for row in matrix])
    # [row[0] for row in matrix] ：遍历matrix里的每个(第一层)元素(list)的第一个元素,返回一个list
print(transposed)

print([row[0] for row in matrix])  # [1, 5, 9]

# 将3X4的矩阵列表转换为4X3列表 方式二：
transposed = []
for i in range(4):
    # the following 3 lines implement the nested listcomp
    transposed_row = []
    for row in matrix:
        transposed_row.append(row[i])
    transposed.append(transposed_row)
print(transposed)

a = [-1, 1, 66, 25, 333, 333, 1234.5]
print(a)
del a[0]
del a[0]
print(a)
del a[2:4]
print(a)
del a[:]
print(a)        # --: []

del a # 回收变量(索引)  del语句作用在变量上，而不是数据对象上，删除的是变量，而不是数据

''' 元组和序列
    元组由若干逗号分隔的值组成
    元组在输出时总是有括号的，以便于正确表达嵌套结构。在输入时可能有或没有括号，
    不过括号通常是必须的（如果元组是更大的表达式的一部分）
'''
print('---------元组和序列---------')
t=1234,4321,'hello'
print(t[0])                 # --: 1234
print(t)                    # --: (1234, 4321, 'hello')
u=t,(1,2,3)                 # --： u是一个二元元组(元素是一元元组)
print(u)                    # --: ((1234, 4321, 'hello'), (1, 2, 3))

'''     集合
 集合是一个无序不重复元素的集。基本功能包括关系测试和消除重复元素
 可以用大括号{}创建集合。注：如果要创建一个空集合，必须用set()而不是{};
 后者创建一个空的字典。
'''
print('---------集合---------')
baskset={'apple','orange','pear','banana','apple','lemon'}
print(baskset)
print('apple' in baskset)       # --: True判断 'apple' 是否在baskset中，即集合中是否有这个元素
print('watermelon' in baskset)  # --: False

a=set('apple')
b=set('phone')
print('a:',a)
print('a-b',a-b)        # --: {'l','a'}, a特有(在a中，但不在b中的字母)--数学中的(相对)补集      这里会从右往左筛选排序
print('a|b',a|b)        # --: ...数学中的并集(a、b中(不重复)的所有字母)
print('a&b',a&b)        # --: {'e','p'}数学中的交集(a、b中共有的所有字母),     这里会从右往左筛选排序
print('a^b',a^b)        # --: {'n','o','l','h','a'}数学中的交集的补集(a、b中共有的所有字母)

'''     字典（K-V） 类似Java的Map
序列是以连续的整数为索引，与此不同的是，字典以关键字为索引，关键字可以是任意不可变类型，通常用字符串或数值。
理解字典的最佳方式是把它看做无序的键=>值对集合。在同一个字典之内，关键字必须是互不相同。
一对大括号创建一个空的字典：{}。
'''
print('---------字典---------')
person={}
person['key']="value"
person['name']="walke"
person['age']=26
person['weight']=59.65
person['email']='www.walke.com'
print(person)
print(person['name'])
del person['key']
print(person)

print('list(person.keys()):',list(person.keys()))
print('sorted(person.keys()):',sorted(person.keys()))   # 排序
print('walke' in  person)       # False
print('name' in  person)        # True
print(type(person.keys()))
print(type(person.values()))

# 构造函数 dict() 直接从键值对元组列表中构建字典。如果有固定的模式，列表推导式指定特定的键值对：
d=dict([('sape',4139),('guido',4127),('jack',4098)])
print(d)        # --： {'sape': 4139, 'guido': 4127, 'jack': 4098}
d={x:x**2 for }












