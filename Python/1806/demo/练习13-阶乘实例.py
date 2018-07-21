'''     Python 阶乘实例
整数的阶乘（英语：factorial）是所有小于及等于改数的正整数的乘积，
0的阶乘为1，n的阶乘为 n!=1*2*3*...*n.
'''
num=int(input('请输入一个数'))

def factorial(num):
    pass
    product=1
    for x in range(1,num+1):
        product*=x
    print('product = ',product)
    return product

factorial(num)

if __name__=='__main__':
    factorial(int(input('__name__请输入一个数')))

'''
请输入一个数2
product =  2
__name__请输入一个数10
product =  3628800

'''





