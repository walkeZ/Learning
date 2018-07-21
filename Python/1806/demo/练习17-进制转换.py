'''     Python 进制转化
以下代码用于实现十进制转二进制、八进制、十六进制：
https://wenda.so.com/q/1459279146721182?src=140
'''

d=int(input('输入数字：'))
print('十进制为： ', d)                      # 8             12       其中：
e=bin(d)                                    # 0b1000        0b1100      0b是二进制前缀(标识)
print('转换为二进制： ', e)                  # 0b1000        0b1100         0b是二进制前缀(标识)
f=oct(d)
print('转换为八进制： ', f)                 # 0o10          0o14           0b是八进制前缀(标识)
print('转换为十六进制： ', hex(d))           # 0x8           0xc            0x是十六进制前缀(标识)
print('二进制转十进制',int(e,base=2))        # 8             12
print('八进制转十进制',int(f,base=8))        # 8             12

# ASCII码与字符相互转换

c = input('请输入一个字符：')
a = int(input('请输入一个ASCII码值：'))
print(c+' 的 ASCII 码为：',ord(c))
print('ASCII 码: ',a,' 的 对应值是：',chr(a))






