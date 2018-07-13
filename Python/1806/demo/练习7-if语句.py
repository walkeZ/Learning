
'''     Python  if 语句
使用 if...elif...else 语句判断数字是正数、负数或零：
'''
# 简单实现案例，用户输入数字
num=float(input('请输入一个数字：'))
if num>0:
    print('你输入的数字是正数。')
elif num==0:
    print('你输入的是0')
else:
    print('你输入的是负数')

# 内嵌 if 语句

print('-----内嵌if语句------')

num = float(input('请输入一个数字'))
if num>=0:
    pass
    if num==0:
        print("你输入的是0")
    else:
        print('你输入的是正数')
else:
    print('你输入的数是负数')