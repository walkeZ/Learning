# -*- coding:utf-8 -*-

'''
斐波纳契数列：两个元素的和确定下一个数
1,1,2,3,5,8
'''
# a = 0;
# b = 1
# while b < 10:
#     print(b)
#     a, b = b, a + b

''' 这是我按自己java习惯写的方案 '''
print('-----这是我按自己java习惯写的方案-----')
num1 = 0
num2 = 1
temp = 1
while temp < 10:
    print(temp, end=' , ')
    temp = num1 + num2
    num1 = num2
    num2 = temp

print('\n-----java方案优化失败-----')
num1 = 0
num2 = 1
while num1 < 10:
    print(num2, end=' , ')
    num1 = num2
    num2 = num1 + num2

print('\n-------网上方案-------')
num1 = 0;
num2 = 1
while num2 < 10:
    print(num2)
    num1, num2 = num2, num1 + num2

# 发现num1, num2 = num2, num1 + num2中表达式：num1 + num2会先执行并存到'_'中，然后在执行赋值，num1=num2，num2=_;

print('-----关键字end可以用于将结果输出到同一行------')
a = 0
b = 1
while b < 500:
    print(b, end=' , ')
    a, b = b, a + b
