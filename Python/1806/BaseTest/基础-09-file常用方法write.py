'''
http://www.runoob.com/python3/python3-inputoutput.html
python 字符串string 开头r b u f 含义 str bytes 转换 format:
    https://blog.csdn.net/qq_16234613/article/details/79448203
    如：
    r'input\n' # 非转义原生字符，经处理'\n'变成了'\\'和'n'。也就是\n表示的是两个字符，而不是换行。
    输出：'input\\n'
'''
import _thread
import time

import os

path = r"./tmp/fileTest3.txt"    # ./ 表示当前模块所在文件夹
# w+: 打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。
file1 = open(path, "w+", encoding="utf-8")  #
for x in range(5):
    ln=file1.write("have a nice day!\n")  # 将字符串写入文件，返回的是写入的字符长度(包括换行符)。
    print('ln = ',ln)

input("file1任意输入，主要测试理解write缓冲区和flush方法:")  # 类似中断(挂起)功能，
        # 发现此时file创建了，但未有文字内容，即write方法没有即时写入文件，其实write方法只是将内容写入到缓存区，
file1.close()   # 被动调起flush方法(功能：刷新缓存区，将缓存区的内容写入文件(读取出来)),
input("任意输入,删除文件")  # 发现当当前脚本(/模块/文件)执行完。才会在文件夹中出现新建的文件，--：检测后发现，只是没刷新而已。
os.remove(path)  # 使用os模块删除文件,file找到自清理、删除方法

input("next,任意输入")

file2 = open(path, "w+", encoding="utf-8")
for x in range(5):
    ln=file2.write("Good luck to you!\n")  # 将字符串写入文件，返回的是写入的字符长度(包括换行符)。
    print('ln = ',ln)

file2.flush()           # 调用后有数据了
input("file2任意输入，主要测试理解write缓冲区和flush方法: ")
file2.close()

input("next2,任意输入")

# w+,if文件存在，原有内容会被删除，文件指针为0
file3 = open(path, "w+", encoding="utf-8") #
for x in range(5):
    ln=file3.write("hello world\n")  # 将字符串写入文件，返回的是写入的字符长度(包括换行符)。
    print('ln = ',ln)
    file3.flush() # 调用后有数据了，一般是这样用写一个就flush一次(实时)

input("file3 任意输入，主要测试理解write缓冲区和flush方法: ")

file3.close()
