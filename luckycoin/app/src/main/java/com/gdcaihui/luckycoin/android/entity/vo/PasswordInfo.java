package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 密码信息
 *
 * @author Xiongrui
 */
public class PasswordInfo extends VoBase {

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 9215537541883930270L;

    private Integer step; // 步聚：修改密码时使用，1 获取信息 2 验证信息 3 提交信息
    private String validate; // 验证码
    private String mobile; // 手机号码
    private Integer time; // 倒计时间
    private String oldPassword; // 旧密码
    private String varyPassword; // 修改的密码
    private Integer result; // 结果 1.显示未绑定手机号码提示 2.显示校验手机号码界面 3.显示修改登录密码界面 4.旧密码输入错误

    public Integer getStep() {
        return this.step;
    }

    public void setStep(Integer step) {
        this.step = step;
        return;
    }

    public String getValidate() {
        return this.validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
        return;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
        return;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return;
    }

    public String getVaryPassword() {
        return this.varyPassword;
    }

    public void setVaryPassword(String varyPassword) {
        this.varyPassword = varyPassword;
        return;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
        return;
    }
}