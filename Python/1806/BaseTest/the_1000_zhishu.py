#coding=utf-8  #���������ʽ ��#��Ҳ�ǵ���ע�͵ı�ʶ��
#�����������ĺ���
def getprim(n):
	#���Ǵ�3��ʼ������Ч�ʣ�΢����΢
	p=3
	x=0
	while(x<n):
		result = True
		for i in range(2,p-1): # range(2,p-1) ��ʾ  * [2,3...,p-2]
			if(p%i==0):
				result = False
		if result ==True:
			x=x+1
			rst=p
			print(p)
		p+=2 #ע��:�����2��Ϊ������Ч�ʣ���Ϊ�ܱ�˫���϶�������������Ϊ��ʼֵ��3,û��Ҫ��1��ż����Ȼ��������
	print(rst)

getprim(1000)

input()

	
	