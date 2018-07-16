
'''
# 二次方程式 ax**2 + bx + c = 0
# a、b、c 用户提供，为实数，a ≠ 0

'''
import cmath  # 复杂数学运算模块Python自带

a=float(input('输入a：'))
b=float(input('输入b：'))
c=float(input('输入c：'))
# 计算 d =(b**2-(4*a*c))
d =(b**2-(4*a*c))
print(d)
# 方程式两种求解方式
sol1=(-b-cmath.sqrt(d))/(2*a)
sol2=(-b+cmath.sqrt(d))/(2*a)
print('结果为{0}和{1}'.format(sol1,sol2))




