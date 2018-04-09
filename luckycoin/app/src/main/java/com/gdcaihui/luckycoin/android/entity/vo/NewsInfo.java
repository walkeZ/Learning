package com.gdcaihui.luckycoin.android.entity.vo;

import java.io.Serializable;

/**
 * 客户端资讯信息
 *
 * @author Xiongrui
 */
public class NewsInfo implements Serializable {

    private String title; // 标题
    private String summary; // 摘要
    private String cover; // 封面
    private String type; // 类型：公告,体彩动态
    private String author; // 作者
    private String url; // 访问路径
    private String dateView; // 显示日期

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        return;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        return;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
        return;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
        return;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
        return;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
        return;
    }

    public String getDateView() {
        return this.dateView;
    }

    public void setDateView(String dateView) {
        this.dateView = dateView;
        return;
    }
}