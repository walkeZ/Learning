# _*_ coding:utf-8 _*_
# 视频地址：https://v.qq.com/x/cover/9v3r1r0sdbyv41o/h01266d7rgp.html
print(10) 						# 控制台输出10 			输出int数据
print(10.11) 					# 输出 10.11  			float数据
print('H') 						# 输出 H  				char(字符)数据
print('Hello, World!,学习') 	# 输出 Hello, World! 	String 数据
print("H")         				# 输出 Hello			char数据
print("Hello, World!，学习")	# 输出 Hello, World!   	string数据
print("换行: \n第二行")			# 输出 换行：(换行后第二行)
print("隔一行",'111111')		# 输出 隔一行 111111	两个参数的print函数第二个参数可不写，有个空格隔开
a=10							# "="(等号)：赋值语句,定义了一个变量a并给其赋值为10；此时a为int类型
print('a=',a)						# 语义：输出(打印) 变量a
b=12.34							# 定义了一个变量b并赋值为12.34；此时b为float类型
print("b=",b)
a='hello'						# 定义了一个遍a并赋值为'hello'；此时a为string类型，会冲掉原来的a=10 
print('a=',a)
print("\n\n")
# 格式化输出 print(format(val,format_modifier)) ;
# val(参数一) 一般为浮点(float[单精度],double[双精度])值,参数二：调节器(即为目标格式)
# format_modifier：【m.nf】,m:占位，n:小数个数，f:flaot类型，当m>格式后的数据长度则右对齐，反之靠左，若不够往后补
#当n<小数个数，四舍五入，n>则补0；没有f则默认返回double类型，格式化输出(带e)
z=12.345678
print(format(z,'6.2f'))			# 结果：“ 12.34”
print(format(z,"6.2f"))			# 结果：“ 12.34”
print(format(z,'6.2')) 			# 结果：“1.2e+01”
print(format(z,'6.5f')) 		# 结果：“12.34568”
print(format(z,'6.9f')) 		# 结果：“12.345678000”
print(format(z,'6.0f')) 		# 结果：“    12”
# %数据 具体输出规律同上
y=0.123456
print(format(y,'.2%'))			# “12.35%”
print(format(y,'7.2%'))			# “ 12.35%”

#输入input()，已enter结束输入
d=input() 						# 返回默认为string类型，故无法直接执行d+=10
print("input：d 为 ",d)
print('type(d): ',type(d))					# 查看d的类型
# d+=10		TypeError: must be str, not int		
d=int(d)						# 强转  注意输入类型，
#强转 输入“hello”报错：ValueError: invalid literal for int() with base 10: 'hello'
d+=10
print("input：d+=10 为 ",d)
age=int(input("请输入XX(age)"))
age-=1
print('age= ',age,'\n','再换两行','111\n','222\n')  # print函数参数估计不限个数
weight=float(input("请输入XX(weight[float])"))
weight-=1
print('weight= ',weight)
input() #输入 可实现python代码暂停执行，按enter键继续



