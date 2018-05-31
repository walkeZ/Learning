package com.walke.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by walke.Z on 2018/5/30.
 * 新建一个bean类，类名加注解@Entry 告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
 * 定义好属性后采AS的Build->MakeProject生成对应的构造方法和getset方法
 * 注意当类实现Serializable时必须要有对应的serialVersionUID，不然会报错
 * 其他常用注解：
 *      @Id         对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
 *      @Property   可以自定义字段名，注意外键不能使用该属性
 *      @NotNull    属性不能为空
 *      @Transient  使用该注释的属性不会被存入数据库的字段中
 *      @Unique     该属性值必须在数据库中是唯一值
 *      @Generated  编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
 */
@Entity  //表
public class Goods implements Serializable {


    private static final long serialVersionUID = -4147717857652909875L;

    /**
     * 表示为购物车列表
     */
    public static final int TYPE_CART = 0x01;

    /**
     * 表示为收藏列表
     */
    public static final int TYPE_LOVE = 0x01;

    @Id(autoincrement = true)  //主键、自增
    private Long id;//不能用int

    @Unique  //唯一
    private String name;//商品名

    @Property(nameInDb = "price")  //表的字段名
    private String price;//价格

    private int sell_num;//已售数量

    private String img_url;//图片地址

    private String address;//商家地址

    private int type;//商品列表类型

    @Generated(hash = 1484816620)
    public Goods(Long id, String name, String price, int sell_num, String img_url,
            String address, int type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sell_num = sell_num;
        this.img_url = img_url;
        this.address = address;
        this.type = type;
    }

    @Generated(hash = 1770709345)
    public Goods() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSell_num() {
        return this.sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
