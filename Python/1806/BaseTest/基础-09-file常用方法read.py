
'''
http://www.runoob.com/python3/python3-inputoutput.html
python 字符串string 开头r b u f 含义 str bytes 转换 format:
    https://blog.csdn.net/qq_16234613/article/details/79448203
    如：
    r'input\n' # 非转义原生字符，经处理'\n'变成了'\\'和'n'。也就是\n表示的是两个字符，而不是换行。
    输出：'input\\n'
'''
# 打开一个文件 参数1:文件路径(具体文件可以为空，当父级目录不能为空)，参数2：打开方式(w:写入，r:读取，r+、w+:读写，具体参考文档)
# 'w'打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。

f=open("D:\GIT\Learning\Python\\1806\BaseTest\\tmp\\fileTest2.txt",'w+')
f=open(r"D:\GIT\Learning\Python\1806\BaseTest\tmp\fileTest2.txt",'w+',encoding='utf-8') # 路径处理，r'路径'
f.write("这是第二个测试文件，用于体验Python中file相关方法\nwalke is a good man , "+
        "walke is a handsome man "
        "\nwalke is nice man\n"
        "wlake is a cool man")

''' 
注意，write和read方法，会将文件指针随之移动到最后操作(读写)的位置
f.tell() 返回文件指针对象当前所处的位置, 它是从文件开头开始算起的字节数。
如果要改变文件指针当前的位置, 可以使用 f.seek(offset, from_what) 函数。
from_what 的值, 如果是 0 表示开头, 如果是 1 表示当前位置, 2 表示文件的结尾，例如：
seek(x,0) ： 从起始位置即文件首行首字符开始移动 x 个字符
seek(x,1) ： 表示从当前位置往后移动x个字符
seek(-x,2)：表示从文件的结尾往前移动x个字符
'''
print('file.tell() = ',f.tell())
f.seek(0)
'''
读取文件内容
'''
# 1. read() 一次性读取全部 -----------------常用，小文件
str1=f.read()
print("str1:\n",str1)
#2. read(size) 按字符(暴扣中文字符)长度读取读取，负数为读取全部 ---------半常用
# f.seek() # TypeError: seek() takes at least 1 argument (0 given) 必须要一个参数
f.seek(0) # 不会报错
print("f.read(10): *",f.read(10),"*") # --：* 这是第二个测试文件， *
print("f.read(10): *",f.read(10),"*") # --：* 用于体验Python *  文件指针随着读取移动了
# 当seek(1)时会报错UnicodeDecodeError: 'gbk' codec can't decode byte 0xfe in position 6: illegal multibyte sequence，原因中文字体不仅占一个字符

# 3. 读取整行(包括"\n"字符)  -----------------常用，小文件
f.seek(0)
str3 = f.readline()
print('第一行的 f.tell()： ',f.tell())
print('f.readline():\n *',str3,"*")
'''  输出：
 * 这是第二个测试文件，用于体验Python中file相关方法
 *
'''
print("f.readline() : ",f.readline())
f.seek(0)

# 4. f.readline(size) Python3中：安字符数量，读取一行中的内容，当超出时该行字符数时，也仅读该行
print("f.readline(395): \n",f.readline(395))
f.seek(0)
print("f.readline(600): \n",f.readline(600),"**")
f.seek(0)
print("f.readline(2): \n",f.readline(2))  # --: '这是'
print("f.readline(25): \n",f.readline(45))  # --: '第二个测试文件，用于体验Python中file相关'
f.seek(0)
# 5. 	file.readlines([sizeint])  按字符数读取：返回list(元素为每行的内容),读取到指定字符数所在行
# 读取所有行并返回列表，若给定sizeint>0，返回总和大约为sizeint字节的行, 实际读取值可能比 sizeint 较大, 因为需要填充缓冲区。
print("f.readlines(2): \n",f.readlines(2))
f.seek(0)
print("f.readlines(50): \n",f.readlines(50))
f.seek(0)
print("f.readlines(55): \n",f.readlines(55))
f.seek(0)
print("f.readlines(): \n",f.readlines()) # 读取所有行，返回list


# .close : 关闭文件，调用后文件不能再进行读写操作
f.close()

# str=f.readline(1)  # ValueError: I/O operation on closed file.

'''
 读取文件完整个过程：
 加出错处理
'''
# 方式一
path=r"D:\GIT\Learning\Python\1806\BaseTest\tmp\fileTest2.txt"
try:
    file=open(path,'r+',encoding='utf-8')
    content=file.read()
    print('\n\n文件内容：\n',content)
except:     #
    print('读取文件失败')
finally: # 最终
    b=file!=None
    b2=file==None
    if file:    # 判断file是否为空，等价于 if file!file!=None
        file.close()

# 方式二 Python with as 的用法。https://www.cnblogs.com/DswCnblog/p/6126588.html
def readFile(filePath):
    with open(path,'r+',encoding='utf-8') as file:
        content2=file.read()
        print('\n\ncontent2:\n',content2)
        file.close()

readFile(path)