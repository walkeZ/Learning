package com.walker.mvvmlearn.net.retrofit2.base.vo;

/**
 * VO（Value Object，值对象）
 * > 1.概念：VO是用于表示视图（View）或界面上的数据对象，通常用于前端与后端交互的数据传递。
 * > 2.区别：VO与数据库表的字段一一对应，并且不包含业务逻辑，只包含属性及其对应的getter和setter方法。
 * > 3.作用：VO主要用于前端展示数据，将多个数据字段封装成一个对象，简化数据传递和展示。
 *
 * DTO（Data Transfer Object，数据传输对象）
 * > 1.概念：DTO也是用于数据传递的对象，但它通常用于服务层和控制层之间的数据传输。
 * > 2.区别：DTO与业务逻辑相关，可能包含一些数据处理方法，例如计算、转换等。
 * > 3.作用：DTO用于封装业务逻辑相关的数据，并进行数据传递，解耦服务层和控制层。
 * BO（Business Object，业务对象）
 * > 1.概念：BO是用于表示业务逻辑对象，包含了业务逻辑和数据处理方法。
 * > 2.区别：BO通常用于业务层，封装了业务处理的逻辑，对外提供服务接口。
 * > 3.作用：BO负责处理业务逻辑，执行业务操作，是业务层的核心对象。
 *
 * DO（Data Object，数据对象）
 * > 1.概念：DO用于表示数据库表中的记录对象，通常与数据库表的字段一一对应。
 * > 2.区别：DO是与持久层（数据库）相关的对象，通常包含数据库操作的方法。
 * > 3.作用：DO用于封装数据库表的数据，提供数据持久化操作的接口。
 * PO（Persistent Object，持久化对象）
 * > 1.概念：PO是持久化对象，它表示数据库中的一条记录。
 * > 2.PO与DO的概念类似，也是与数据库相关的对象，但通常更加偏向于ORM（对象-关系映射）框架的实体对象。
 * > 3.作用：PO用于映射数据库表的结构，通过ORM框架进行数据库操作。
 *
 * POJO（Plain Old Java Object，简单老式Java对象）
 * > 1.概念：POJO是一种简单的Java对象，没有任何特殊限制和要求，不继承特定的类或实现特定的接口。
 * > 2.区别：POJO是一种通用的概念，可以是任何普通的Java对象，不局限于特定的应用场景或用途。
 * > 3.作用：POJO用于表示普通的数据对象，没有业务逻辑，可用于各种不同的场景。
 *
 * Entity（实体类）
 * > 1.概念：Entity也是用于表示数据库表的对象，通常是ORM框架中的实体类，与数据库表的字段一一对应。
 * > 2.区别：Entity是一种特定于ORM框架的概念，用于映射数据库表的结构。
 * > 3.作用：Entity用于表示数据库中的实体对象，通过ORM框架进行数据库操作。
 * ————————————————
 * 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 *
 * 原文链接：https://blog.csdn.net/qq_22193961/article/details/137940492
 *
 * VoBase 前后端通信业务的最外层对象结构(json的最外层{})
 * 玩Android的首页banner接口https://www.wanandroid.com/banner/json
 * 样例如下：
 * {
 *   "data": [
 *     {
 *       "desc": "我们支持订阅啦~",
 *       "id": 30,
 *       "imagePath": "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
 *       "isVisible": 1,
 *       "order": 2,
 *       "title": "我们支持订阅啦~",
 *       "type": 0,
 *       "url": "https://www.wanandroid.com/blog/show/3352"
 *     },
 *     {
 *       "desc": "",
 *       "id": 6,
 *       "imagePath": "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
 *       "isVisible": 1,
 *       "order": 1,
 *       "title": "我们新增了一个常用导航Tab~",
 *       "type": 1,
 *       "url": "https://www.wanandroid.com/navi"
 *     },
 *     {
 *       "desc": "一起来做个App吧",
 *       "id": 10,
 *       "imagePath": "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
 *       "isVisible": 1,
 *       "order": 1,
 *       "title": "一起来做个App吧",
 *       "type": 1,
 *       "url": "https://www.wanandroid.com/blog/show/2"
 *     }
 *   ],
 *   "errorCode": 0,
 *   "errorMsg": ""
 * }
 */
public class VoBase<D> {
    public int errorCode; // public 免去get、set
    public String errorMsg;
    public D data;
}
