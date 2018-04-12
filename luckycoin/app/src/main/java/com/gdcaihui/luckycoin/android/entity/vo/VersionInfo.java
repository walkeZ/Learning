package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 客户端版本信息
 * Created by Xiongrui on 2017/2/21.
 */
public class VersionInfo extends VoBase {
    private String versionNum;  // 版本名称
    private Integer status; // 状态
    private String updateVersionNum; // 更新版本名称
    private String updateVersionRemark; //更新描述
    private String updateDownloadUrl; // 更新地址

    public String getVersionNum() {
        return this.versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
        return;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        return;
    }

    public String getUpdateVersionNum() {
        return this.updateVersionNum;
    }

    public void setUpdateVersionNum(String updateVersionNum) {
        this.updateVersionNum = updateVersionNum;
        return;
    }

    public String getUpdateVersionRemark() {
        return this.updateVersionRemark;
    }

    public void setUpdateVersionRemark(String updateVersionRemark) {
        this.updateVersionRemark = updateVersionRemark;
        return;
    }

    public String getUpdateDownloadUrl() {
        return this.updateDownloadUrl;
    }

    public void setUpdateDownloadUrl(String updateDownloadUrl) {
        this.updateDownloadUrl = updateDownloadUrl;
        return;
    }
}