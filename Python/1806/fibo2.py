

def fib(n):
    a,b=0,1
    print('fibo2')
    while b<n:
        print(b, end='  ')
        a,b=b,a+b
    print()


def fib2(n):
    result=[]
    a,b=0,1
    while b<n:
        result.append(b)
        a,b=b,a+b
    return result

def fib3(n):
    a,b=0,1
    print('fibo2----fib3')
    while b<n:
        print(b, end='  ')
        a,b=b,a+b
    print()





