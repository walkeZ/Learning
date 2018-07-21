'''     Python 斐波那契数列 Fibonacci
斐波那契数列：如：0，1，1，2，3，5，8，13...
第0项是0，第一项是1，从第3项开始，每一项都等于前两项之和
案例1:请输入一个自然数,求该数范围内的斐波那契数列，用list存起来
'''
num=int(input('请输入一个自然数'))

def getFibonacci1(num):
    list=[0,1,1,2]
    temp=2
    if num<0:
        print('请输入一个自然数')
        return
    elif num>2 :
        while True:
            n=len(list)
            b=list[n-2]
            temp+=b
            if temp>num:
                return list
            list.append(temp)
    return list

print(15, ' getFibonacci1: ',getFibonacci1(15))  # [0, 1, 1, 2, 3, 5, 8, 13]

def is_int(num):
    try:
        int(num)
        return True
    except ValueError:
        return False

def getFibonacci2(num):
    list=[0,1]
    temp=1
    if is_int(num)==False and num<0:  # python 中没有true=!false
        print('请输入一个自然数')
        return
    elif num == 0:
        print('无意义')
        return
    elif num == 1:
        return list
    elif num>1 :
        while True:
            n=len(list)
            b=list[n-2]
            temp+=b
            if temp>num:
                return list
            list.append(temp)
    return list
print(2, ' getFibonacci2: ', getFibonacci2(2))  # [0, 1, 1, 2]

def getFibonacci3(num):  # 这个比较优化
    list=[0]
    a,b=0,1;
    while b<num:
        list.append(b)
        a,b=b,a+b
    return list


print('10  getFibonacci3: ', getFibonacci3(10))     # [0, 1, 1, 2, 3, 5, 8]
print('1  getFibonacci3: ', getFibonacci3(1))       # [0]
print('2  getFibonacci3: ', getFibonacci3(2))       # [0, 1, 1]


# 2 求第几个斐波那契数
print("----------求第几个斐波那契数-----------")

def get_fibo(n):
    a,b=0,1
    count=2
    if n==1:
        return a
    if n==2:
        return b
    while count<n:
        a,b=b,a+b
        count+=1
    return b

print('1 : ',get_fibo(1))       # 0
print('2 : ',get_fibo(2))       # 1
print('3 : ',get_fibo(3))       # 1
print('5 : ',get_fibo(5))       # 3
print('7 : ',get_fibo(7))       # 8
print('27 : ',get_fibo(27))     # 131393





