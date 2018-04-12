package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 会员注册信息
 *
 * @author Xiongrui
 */
public class RegisterInfo extends VoBase {

    private String username; // 手机号(用户名)
    private String password; // 密码
    private String validate; // 手机验证码
    private MemberInfo memberInfo; // 会员信息
    private Integer step; // 步骤
    private Integer time; // 倒计时间

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

    public MemberInfo getMemberInfo() {
        return this.memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
        return;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", validate='" + validate + '\'' +
                ", mMemberInfo=" + memberInfo +
                '}';
    }
}
