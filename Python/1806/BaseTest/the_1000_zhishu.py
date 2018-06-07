#coding=utf-8  #声明编码格式 “#”也是单行注释的标识符
#定义求质数的函数
def getprim(n):
	#我们从3开始，提升效率，微乎其微
	p=3
	x=0
	while(x<n):
		result = True
		for i in range(2,p-1): # range(2,p-1) 表示  * [2,3...,p-2]
			if(p%i==0):
				result = False
		if result ==True:
			x=x+1
			rst=p
			print(p)
		p+=2 #注意:这里加2是为了提升效率，因为能被双数肯定不是质数。因为初始值是3,没必要加1，偶数必然不是质数
	print(rst)

getprim(1000)

input()

	
	