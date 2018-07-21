'''     Python 检测用户输入是否为质数
质数：一个大于1的自然数，除了1和自身外，没有其他因数，即只能被1和自身整除
质数也称素数
'''
num=int(input('请输入一个大于1的自然数(质数将循环)'))

def is_prime_number(num):
    pass
    for x in range(2,num):
        if num%x==0:
            print('遍历 num%x==0 x = ', x,end=' , ')
            return False
    else:
        return True

# tag=is_prime_number(num)
# print('tag = ',tag)
while is_prime_number(num):
    num=int(input('\n请再次输入一个大于1的自然数'))

# 2. 找出用户输入的数字范围内的质数，并存到list(列表中)

num=int(input('请输入一个范围'))

def find_prime_number(num):
    list=[]
    for x in range(2,num+1):
        pass
        if is_prime_number(x):
            list.append(x)

    print('\n',list)
find_prime_number(num)
find_prime_number(int(input('请输入一个范围')))




