
'''
需求：实现一个方法：传入三个边长，算出三角形面积
数学公式：边长：a,b,c；半周长：d；面积：s=((d/2)*(d/2-a)*(d/2-b)*(d/2-c))**0.5
**0.5:表示1/2次幂(即开根号)
程序： 1.边长输入
       2.边长都大于0不需无复数，不需导入cmath模块
        3.考虑拓展性、复用性等，封装为方法
'''
def getArea(a, b,c):
    d=a+b+c
    e=d/2
    s=(e*(e-a)*(e-b)*(e-c))**0.5
    print('三边分别为{0}、{1}、{2}的三角形面积是：{3}'.format(a,b,c,s))
    return s

a=float(input('请输入a:'))
b=float(input('请输入b:'))
c=float(input('请输入c:'))
getArea(a,b,c)

# 装饰器: inner

def outer(func):
    pass
    def inner(a,b,c):
        if((a+b)<=c or (a+c)<=b or (b+c)<=a):
            print("三边分别为{0}、{1}、{2}，两边之和不大于第三边，您输入的边长有误".format(a,b,c))
            return
        func(a,b,c)
    return inner        # 注意这里没有()

getArea=outer(getArea)  # 注意这里的参数仅为方法名
getArea(1,2,3)





