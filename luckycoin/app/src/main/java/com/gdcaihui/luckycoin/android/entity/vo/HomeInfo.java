package com.gdcaihui.luckycoin.android.entity.vo;

import java.util.List;

/**
 * 客户端资讯信息
 *
 * @author Xiongrui
 */
public class HomeInfo extends VoBase {

    private MemberInfo memberInfo; // 会员信息
    private String homeURL; // 首页url
    private String promotionsURL; // 活动宣传url
    private String scanHint; // 扫描界面提示文本
    private String areaText; // 当前地区
    private String areaIco; // 图标
    private String scanImg; // 扫描界面提示图
    private List<FocusImageInfo> focusImageInfoList; // 焦点图集合
    private List<NewsInfo> newsInfoList; // 资讯信息集合 最多30条

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public String getHomeURL() {
        return homeURL;
    }

    public void setHomeURL(String homeURL) {
        this.homeURL = homeURL;
    }

    public String getPromotionsURL() {
        return promotionsURL;
    }

    public void setPromotionsURL(String promotionsURL) {
        this.promotionsURL = promotionsURL;
    }

    public String getScanHint() {
        return scanHint;
    }

    public String getAreaText() {
        return areaText;
    }

    public void setAreaText(String areaText) {
        this.areaText = areaText;
    }

    public String getAreaIco() {
        return areaIco;
    }

    public String getScanImg() {
        return scanImg;
    }

    public void setScanImg(String scanImg) {
        this.scanImg = scanImg;
    }

    public void setAreaIco(String areaIco) {
        this.areaIco = areaIco;
    }

    public void setScanHint(String scanHint) {
        this.scanHint = scanHint;
    }

    public List<FocusImageInfo> getFocusImageInfoList() {
        return this.focusImageInfoList;
    }

    public void setFocusImageInfoList(List<FocusImageInfo> focusImageInfoList) {
        this.focusImageInfoList = focusImageInfoList;
        return;
    }

    public List<NewsInfo> getNewsInfoList() {
        return this.newsInfoList;
    }

    public void setNewsInfoList(List<NewsInfo> newsInfoList) {
        this.newsInfoList = newsInfoList;
        return;
    }
}