package com.gdcaihui.luckycoin.android.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户端焦点图信息
 *
 * @author Xiongrui
 */
public class FocusImageInfo implements Serializable {

    private String title; // 标题
    private String filePath; // 图片路径
    private String url; // 链接地址
    private Date dateBegin; // 开始时间
    private Date dateEnd; // 结束时间

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        return;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        return;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
        return;
    }

    public Date getDateBegin() {
        return this.dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
        return;
    }

    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
        return;
    }

}