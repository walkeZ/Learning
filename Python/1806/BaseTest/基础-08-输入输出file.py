
'''
http://www.runoob.com/python3/python3-inputoutput.html
'''
# 打开一个文件 参数1:文件路径(具体文件可以为空，当父级目录不能为空)，参数2：打开方式(w:写入，r:读取，r+、w+:读写，具体参考文档)
# 'w'打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。
f=open("D:\GIT\Learning\Python\\1806\BaseTest\\tmp\\foot.txt",'w')
f.write("Python 是一个非常好的语言。\n是的，的确非常好！！\n")

# 关闭打开的文件
f.close()

path="D:\GIT\Learning\Python\\1806\BaseTest\\tmp\\test.txt"
t=open(path,"w")
num=t.write("walke 会瘦下来的。\n是的，所有人都相信！！\n")
print('num = ',num)  # f.write(string) 将 string 写入到文件中, 然后返回写入的字符数。
# 关闭打开的文件
t.close()
# "a"打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入。
t=open(path,"a")
t.write("前提是：ta有坚持锻炼\n")

# 关闭打开的文件
t.close()

r=open(path,'r')
print('r.read()：',r.read())
print('r.readline()：',r.readline())         # 已经读取过了，此时指针在文件最末
r.close()

fr=open(path,'r')
print('fr.readline()：',fr.readline())
print('fr.read()：',fr.read())               # 从第二行开始
fr.close()

fr2=open(path,'r')
print('fr2.readlines()：',fr2.readlines())
fr2.close()

fr3=open(path,'r')
print('fr3.readlines(3)：',fr3.readlines(13))    #设置可选参数 sizehint, 则读取指定长度的字节, 并且将这些字节按行分割。
fr3.close()

# 如果要写入一些不是字符串的东西, 那么将需要先进行转换:
fw=open(path,'a')
s=str((('www.runoob.com',14),[23,'jack',True,('name',26)]))+'\nhi'
fw.write(s)
fw.close()

frw=open(path,'rb+')  # 以二进制格式打开一个文件用于读写。文件指针将会放在文件的开头。不会清除原有
write=frw.write(b'0123456789abcdef')
seek5=frw.seek(5)
read1=frw.read(1)
seek_3=frw.seek(-3,2)
read11=frw.read(1)
print('write=',write,',seek5=',seek5,',read1=',read1,',seek_3=',seek_3,',read11=',read11)