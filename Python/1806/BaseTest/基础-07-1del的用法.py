# -*— coding=utf-8 -*-
" python 中del 的用法 https://blog.csdn.net/love1code/article/details/47276683 "

if __name__=='__main__':
    a=1             # 对象1被变量a引用，对象a的引用计数器为1个
    b=a             # 对象1被变量b引用，对象a的引用计数器+1
    c=a             # 对象1被变量c引用，对象a的引用计数器+1
    del a           # 删除变量a，解除a对 对象1的引用
    del b           # 删除变量b，解除b对 对象1的引用
    print(c)        # c仍然持有对象1的应用

    # 列表
    li=[1,2,3,4]    # 列表li本身不包含数据'1、2、3、4'，而是包函数变量li[0],li[1],li[2],li[3]
    first=li[0]     # 拷贝列表，不会有数据对象的复制，而是创建一个新的引用
    del li[0]       # 删除一个变量(引用)
    print(li)       # --: [2,3,4]
    print(first)    # --: 1
