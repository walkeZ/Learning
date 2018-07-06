

def fib(n):
    a,b=0,1
    print('fibo1')
    while b<n:
        print(b,end='  ')
        a,b=b,a+b

def fib2(n):
    result=[]
    a,b=0,1
    while b<n:
        result.append(b)
        a,b=b,a+b
    return result





