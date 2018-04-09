package com.gdcaihui.luckycoin.android.entity.vo;


/**
 * 会员关联的第三方平台信息
 *
 * @author Xiongrui
 */
public class MemberJoinInfo extends VoBase {

    private Integer thirdPartyId; // 第三方平台id
    private String thirdPartyName; // 第三方平台名称
    private String nickName; // 第三平台昵称
    private String headPortrait; // 第三方平台头像信息
    private Integer canCancelBind; // 能否取消绑定
    private String openId;

    public Integer getThirdPartyId() {
        return this.thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
        return;
    }

    public String getThirdPartyName() {
        return this.thirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
        return;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        return;
    }

    public String getHeadPortrait() {
        return this.headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
        return;
    }

    public Integer getCanCancelBind() {
        return this.canCancelBind;
    }

    public void setCanCancelBind(Integer canCancelBind) {
        this.canCancelBind = canCancelBind;
        return;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}