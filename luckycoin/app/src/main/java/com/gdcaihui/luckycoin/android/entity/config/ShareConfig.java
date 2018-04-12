package com.gdcaihui.luckycoin.android.entity.config;


import java.io.Serializable;

/**
 * 分享配置
 * 
 * @author Xiongrui
 *
 */
public class ShareConfig implements Serializable {
	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = -5559336945371522614L;

	public static final String WECHAT = "wechat";
	public static final String QQ = "qq";
	public static final String SINA = "sina";

	private String appId;
	private String key;
	private String url;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
		return;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
		return;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
		return;
	}
}