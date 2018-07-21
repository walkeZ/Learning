'''     Python 方法递归
递归：一个入口，一个出口。
控制方法一般表现为：有一个条件，当为true/false时再次调用方法(递归)，反之不调用方法(不递归)
'''

# 1.普通递归，计算1+2+3+...+100

def getSum1(n):
    print("递归---> n = ",n)
    if n>0:
        return getSum1(n - 1)
    else:
        print("递归结束:  ")
        return
getSum1(10)
print('---------------')
def getSum2(n):
    print("递归2---> n = ",n,end=' ,  ')
    if n>0:
        ss = n+getSum2(n-1)
        print("ss = ",ss)
        return ss
    else:
        print("递归结束2:")
        return 0
print("getSum2(10) = ",getSum2(10))
def getSum(n):
    if n<0:
        print('getSum() 递归出口')
        return 0        # 这个返回0是必要的，因为有调用到，在最后一次中有int类型的加运算
    else:
        ss = n + getSum(n - 1)
        return ss
print("getSum(10) = ",getSum(10))

# 2.递归生成斐波纳契数列

def myGetFibo(n):
    '''获取第几个斐波那契数'''
    if n==1:
        return 0
    if n == 2:
        return 1
    if n>2: # 进入递归
        temp = myGetFibo(n - 1) + myGetFibo(n - 2)
        return temp
    else:
      return "你传入参数不合法(小于1)"
print("getFibo(0) = ", myGetFibo(0))
print("getFibo(1) = ", myGetFibo(1))
print("getFibo(2) = ", myGetFibo(2))
print("getFibo(3) = ", myGetFibo(3))
print("getFibo(4) = ", myGetFibo(4))
print("getFibo(5) = ", myGetFibo(5))
print("getFibo(6) = ", myGetFibo(6))
print("getFibo(7) = ", myGetFibo(7))

def recur_fibo(n):
   """递归函数
   输出斐波那契数列"""
   if n <= 1:
       return n
   else:
       return(recur_fibo(n-1) + recur_fibo(n-2))

print("recur_fibo(0) = ", recur_fibo(0))
print("recur_fibo(1) = ", recur_fibo(1))
print("recur_fibo(2) = ", recur_fibo(2))
print("recur_fibo(3) = ", recur_fibo(3))
print("recur_fibo(7) = ", recur_fibo(7))
for i in range(7):
    print("-----recur_fibo(",i,') = ' ,recur_fibo(i))







