package com.gdcaihui.luckycoin.android.entity.vo;

import java.util.List;

/**
 * 分页
 *
 * @author Xiongrui
 */
public class Page<T> extends VoBase {

    private Integer currentPage; // 当前页数
    private Integer totalPage; // 总页数
    private Integer orderType; // 升序、降序
    private Object param; // 参数
    private List<T> list; // 返回数据集合

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
        return;
    }

    public Integer getOrderType() {
        return this.orderType;
    }

    public Object getParam() {
        return this.param;
    }

    public void setParam(Object param) {
        this.param = param;
        return;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
        return;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
        return;
    }
}
