# _*_ coding=utf-8 _*_  	# 编码声明 
# 参考： 
#	python初步学习-import和datetime模块 ：https://www.cnblogs.com/pingqiang/p/7812137.html
from datetime import datetime , timedelta

now=datetime.now() 	# 获取当前的datetime ：
print ("now= ",now)			# 2018-06-08 10:33:53.238822

input()

now+=timedelta(hours=5)
print("now+=timedelta(hours=5): ",now)

input()

now+=timedelta(hours=-2)
print("now+=timedelta(hours=-2): ",now)

input()

now-=timedelta(hours=1)
print("now-=timedelta(hours=1): ",now)

input()

now-=timedelta(days=1)
print("now+=timedelta(days=1): ",now)

# 获取指定日期和时间 SyntaxError: invalid token
#dt=datetime(2018,06,08,10,55)
dt=datetime(2018,6,8,10,55)
print(dt)

print("\ndatetime转换为str",now.strftime('%a,%b,%Y-%m-%d %H:%I:%M'))
print("\ndatetime转换为str",now.strftime('%a,%b,%Y年%m月%d %H:%I:%M'))	# 报错了
# UnicodeEncodeError: 'locale' codec can't encode character '\u5e74' in position 8: Illegal byte sequence
'''  duohang
python中时间日期格式化符号：
%y 两位数的年份表示（00-99）
%Y 四位数的年份表示（000-9999）
%m 月份（01-12）
%d 月内中的一天（0-31）
%H 24小时制小时数（0-23）
%I 12小时制小时数（01-12）
%M 分钟数（00=59）
%S 秒（00-59）

%a 本地简化星期名称
%A 本地完整星期名称
%b 本地简化的月份名称
%B 本地完整的月份名称
%c 本地相应的日期表示和时间表示
%j 年内的一天（001-366）
%p 本地A.M.或P.M.的等价符
%U 一年中的星期数（00-53）星期天为星期的开始
%w 星期（0-6），星期天为星期的开始
%W 一年中的星期数（00-53）星期一为星期的开始
%x 本地相应的日期表示
%X 本地相应的时间表示
%Z 当前时区的名称
%% %号本身

'''

input()







