package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 会员登录信息
 *
 * @author Xiongrui
 *
 */
public class LoginInfo extends VoBase {
    private String username; // 用户名
    private String password; // 密码
    private String validate; // 验证码(来自图片验证码)
    private Integer type; // 登录类型
    private Integer step; // 步聚


    private MemberInfo memberInfo; // 会员信息
    private String alipayLoginAuthInfo; // 支付宝登录所需的authInfo

    public String getAlipayLoginAuthInfo() {
        return alipayLoginAuthInfo;
    }

    public void setAlipayLoginAuthInfo(String alipayLoginAuthInfo) {
        this.alipayLoginAuthInfo = alipayLoginAuthInfo;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        return;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        return;
    }

    public String getValidate() {
        return this.validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
        return;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
        return;
    }

    public Integer getStep() {
        return this.step;
    }

    public void setStep(Integer step) {
        this.step = step;
        return;
    }

    public MemberInfo getMemberInfo() {
        return this.memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
        return;
    }
}
