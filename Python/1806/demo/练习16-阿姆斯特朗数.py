'''     Python 阿姆斯特朗数 Fibonacci
如果一个n位正整数等于其各位数字的n次方之和,则称该数为阿姆斯特朗数。 例如1^3 + 5^3 + 3^3 = 153。
1000以内的阿姆斯特朗数： 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407。
以下代码用于检测用户输入的数字是否为阿姆斯特朗数：
'''


# 先写一个1000以内的s三位数方法
def amus1():
    list=[]
    for num in range(152,1000):
        g=num%10
        s=int(num/10%10)
        b=int(num/100%10)
        tag=g**3+s**3+b**3==num
        if tag:
            list.append(num)
    print('list:',list)

amus1()

print('-------实现-------')
num=int(input('请输入一个数'))
# 求一个范围内的阿姆斯特朗数,并放入一个list
def amus2(num):
    list=[]
    for i in range(num):        #  [0,num)
        n = len(str(i))          # 位数
        sum=0
        for j in range(0,n):     # 位数遍历，n**0=1,任何数的0次方都是1
            temp=i/10**j
            g=int(temp)%10
            sum+=g**n
        if sum==i:
            list.append(sum)

    print('list:',list)
amus2(num)

print('-------优化:使用 // 运算符-------')
def getAmust(num):
    list=[]
    for x in range(num):
        n=len(str(x))     # 位数
        temp=x
        sum=0
        while temp>0:
            ge=temp%10      # 个位数                   5               1
            sum+=ge**n      #                         ^               ^
            temp//=10       # // 取整除, 153//=10 -> 15-（15//=10）->1
        if sum==x:
            list.append(sum)
    return list

print("getAmust(num): ",getAmust(num))
