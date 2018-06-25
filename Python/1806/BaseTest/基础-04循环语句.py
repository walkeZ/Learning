# -*— coding=utf-8 -*-
'''

'''
#  1 到 100 之和为: 5050
n=100
sum=0
counter=1
while counter<=n:
    sum+=counter
    counter+=1
print('1 到 %d 之和为: %d'% (n,sum))

''' while True:
    你可以使用 CTRL+C 手动来退出当前的无限循环。
'''
print('-------------无限循环--------------')
num=0
while True:
    print('while True: 无限循环  num=',num)
    num+=1
    if num==1000:
        break  # 跳出循环

print('-------------while else--------------')
count=0
while count<5:
    print(count,'小于8')
    count+=1
else:
    print(count,'大于或等于8') #只打印一次


'''
我们可以通过设置条件表达式永远不为 false 来实现无限循环，实例如下：
在cmd控制台你可以使用 CTRL+C 手动来退出当前的无限循环。
类似if语句的语法，如果你的while循环体中只有一条语句，你可以将该语句与while写在同一行中， 如下所示：

flag=1
while flag==1: print('我在使用菜鸟教程学习Python')   # 表达式"flag==1"永远为 true

print('end')

'''
''' for 语句
Python for循环可以遍历任何序列的项目，如一个列表或者一个字符串。
for循环的一般格式如下：
'''
print('-------------for 语句--------------')
langusges=['c','c#','c++','Java','JavaScript','Python','PHP']
for x in langusges:
    print(x,end=' | , | ')

print('-------------for 案例1--------------')
sites=['Baidu','Google','Runoob','Taobao']    # sites:网站
for site in sites:
    if site=='Runoob':
        print('菜鸟教程')
        break
    print('循环数据',site)
else:
    print('没有循环数据')
print('循环结束-------')

print('-------------range()函数--------------')
''' range()函数创建序列  '''
print('range(5):')
for i in range(5):              # 遍历一个序列 ，默认从零开始：即数学的[0,5)
    print(i,end='，')           # --：0，1，2，3，4，
print('\nrange(3,7):')
for i in range(3,7):            # 前包括后不包：即数学的[3,7)
    print(i,end='，')           # --：3,4,5,6，
print('\nrange(0,15,4):')
for i in range(0,15,4):         # 参数1：列表开始数值，参数2：重点数值(不包括)，参数3：间隔，右侧表示，[0,15)间隔为4的列表
    print(i,end='，')           # --：0,4,8,12，
print('\nrange(1,15,4): ')
for i in range(1,15,4):         # 右侧表示，[1,15)间隔为4的列表
    print(i,end='，')           # --：1,5,9,13，
print('\nin range(-10):')
for i in range(-10):
    print(i,end='，')            # 被跳过无输出
else:
    print('列表无数据')          # --：列表无数据
print('\nrange(-10,-20):')
for i in range(-10,-20):
    print(i, end='，')           # 被跳过无输出
else:
    print('列表无数据')

print('\nrange(-20,-15):')
for i in range(-20,-15):
    print(i, end='，')           # --: -20，-19，-18，-17，-16， 即为[-20,15)
print('\nrange(-3,3):')
for i in range(-3,3):
    print(i, end='，')           # --: -3，-2，-1，0，1，2，     即为[-3,3)
print('\nrange(3,-3,-1):')
for i in range(3,-3,-1):
    print(i, end='，')
print('\nrange(-20,-100,-20):')
for i in range(-10,-100,-20):
    print(i, end='，')           # --: -10，-30，-50，-70，-90，
print('\nrange(-100,-10,-20):')
for i in range(-100,-10,20):
    print(i, end='，')           # --: -100，-80，-60，-40，-20，
print('')                        #通过上述案例可知，range()函数默认升序，间隔为1，初始为0，
                                 # 当间隔为负数时，前两个参数表示的区间应为降序，否则无数据
print('-------------range()和len()函数以遍历List--------------')
a=['Google','Baidu','qq','Taobao','Jingdong']
for i in range(len(a)):
    print(i,a[i],end='|')       # --： 0 Google|1 Baidu|2 qq|3 Taobao|4 Jingdong|
print('')


print('-------------使用range()函数来创建一个列表--------------')
print(list(range(5)))       #	list(s) 将序列 s 转换为一个列表

print('-------------break和continue语句及循环中的else子句--------------')
'''
break 跳出当前层循环
'''
for i in range(8):
    for letter in 'hello world!':
        if letter == 'o':
            break                           # 跳出当前层循环
        print(letter,end=',')
    else:
        print("else---------------")

    print('   |** i=',i,end=', ')

print('\n------------continue-------------')
var=6
while var>0:
    var-=1
    print('  var：', var, end=',')           # --: var： 4,  var= 4,  var： 3,  var： 2,  var= 2,
    if var==3:
        continue                            # 跳过当前层循环语句块的后面语句
    print('  var=',var,end=',')
print('\n------------else-------------')
'''
循环语句可以有 else 子句，它在穷尽列表(以for循环)或条件变为 false (以while循环)导致循环终止时被执行,但循环被break终止时不执行。
如下实例用于查询质数的循环例子:
'''

for x in range(1, 5):
    print('x = ',x)
else:  # 循环中没有找到元素
    print('else----->1')
print('-----------------------')

for x in range(1, 5):
    if x==3:
        break
    print('x = ',x)
else:  # 循环中没有找到元素  部分类似Java的finally，即for循环后执行，但for中调用了break不再执行
    print('else----->2')
print('-----------------------')

for x in range(1, 5):
    if x==3:
        continue
    print('x = ', x)
else:  # 循环中没有找到元素  部分类似Java的finally，即for循环后执行，但for中调用了break不再执行
    print('else----->3')
print('-----------------------')

for n in range(2,15):
    for x in range(2,n):
        if n%x==0:
            print(n,'等于',x,'*',n//x)
            break

    else: # 循环中没有找到元素 部分类似Java的finally，即for循环后执行，但for中调用了break不再执行
        print(n,'是质数')


print('-----------pass语句-----------')
''' Python pass是空语句，是为了保持程序结构的完整性。
    参考：https://blog.csdn.net/violet_echo_0908/article/details/52052054
        1.空语句 do nothing
        2.保证格式完整
        3.占位语句保证语义完整
    
'''
if True:
    pass  # 体现:占位作用，如果没有pass,格式不完整，报错(编译时异常，IDE会出现红波浪线)
else:
    pass

for letter in 'hello world!':
    if letter=='w':
        print("letter:",letter)
        pass  # 也可以像Java的ToDo的用法，用来表示待添加功能/业务，
        # 此案例有无也不影响。下一行代码的执行
        print("pass 语句")
    print('当前字母：',letter)





