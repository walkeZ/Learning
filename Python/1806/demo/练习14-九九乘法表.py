'''     Python 九九乘法表
需求：在控制台输出一个九九乘法表
'''

for x in range(1,10): # 遍历范围[1,10)
    for y in range(1,10):
        print(x,'*',y,' = ',x*y,end="   ,   ")

    print("")

print('------小调整-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(x, 10):
        print(x, '*', y, ' = ', x * y, end="   ,   ")

    print("")
print('------调整2-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x):  # 输出打印是与书写流程一样（一行一行的，所以代表九九乘法表第一行应该只有一个元素，第二行有两个[这是循环]...）
        print(x, '*', y, ' = ', x * y,end="         " )

    print("")

print('------调整3-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x+1):
        print(x, '*', y, ' = ', x * y,end="             " )

    print("")

print('------调整4-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x+1):
        print('{0}*{1}={2}'.format(y,x,x*y),end="   ")

    print("")

print('------调整5-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x+1):
        print(' {0} *{1}={2}\t'.format(y,x,x*y),end="")
    print("")

print('------调整6-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x+1):
        print(' {0} *{1}={2} \t'.format(y,x,x*y),end="")
    print("")
print('------调整7-------')
for x in range(1, 10):  # 遍历范围[1,10)
    for y in range(1, x+1):
        print('{}*{}={}\t'.format(y,x,x*y),end="") # 发现：\t 前面的内容左右对称，则输出对称，\t	横向制表符，它的作用是对齐表格数据的各列
    print("")

# python \r \t \n 各种转义字符 https://blog.csdn.net/xufangfang5206/article/details/80030300

