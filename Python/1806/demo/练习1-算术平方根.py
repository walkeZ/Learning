
# 该程序只适用于正数
num=float(input("请输入一个数字"))
result=num**0.5
print("%0.3f 的平方根为 %0.3f"%(num,result))

# 计算复数和负数的平方根
import cmath
num=int(input('请随意输入一个数(可为负数)'))
num_sqrt=cmath.sqrt(num)
print('{0}的平方根为{1:0.3f}+{2:0.3f}j'.format(num,num_sqrt.real,num_sqrt.imag))