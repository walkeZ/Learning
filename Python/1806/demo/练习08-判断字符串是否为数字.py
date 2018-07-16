
'''     Python 判断字符串是否为数字
以下实例通过创建自定义函数is_number()方法来判断字符是否为数字
'''

def is_number(s):
    pass
    try:
        float(s)
        return True
    except ValueError:
        pass        # 占位 无语义使格式完整
    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError,ValueError):
        pass
    return False

# 测试字符串和数字
print("f -- ",is_number('f'))
print("1 -- ",is_number('1'))
print("3.14 -- ",is_number('3.14'))
print("-10 -- ",is_number('-10'))
print("1e3 -- ",is_number('1e3')) # 科学计数法 实数的指数形式 为1乘以10的三次方 e为阶码标志 大小写通用3为阶码
print('1e3 = ',1e3)               # 1000.0

# 测试 Unicode
# 阿拉伯语 5
print(is_number('٥'))  # True
# 泰语 2
print(is_number('๒'))  # True
# 中文数字
print(is_number('四')) # True
# 版权号
print(is_number('©'))  # False

# isdigit() 方法检测字符串是否只由数字组成。string 模块的方法
# isnumeric()  http://www.runoob.com/python3/python3-string-isnumeric.html
print('123456'.isdigit())                   # True
print('123456,'.isdigit())                  # False
print('123456.'.isdigit())                  # False
print('123456,hello,world'.isdigit())       # False