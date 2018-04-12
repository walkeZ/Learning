package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 手机验证码信息
 *
 * @author Xiongrui
 */
public class MobileValidateCode extends VoBase {

    private Integer type; // 类型：1-注册验证码、2-绑定手机验证码
    private String mobile; // 手机号码

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
        return;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        return;
    }

    public enum Type {
        /**
         * 注册 register
         */
        REGISTER(1, "注册"),

        /**
         * 绑定手机号 bind_mobile
         */
        BIND_MOBILE(2, "绑定手机号"),

        /**
         * 修改时验证手机号 modify_validate_mobile
         */
        MODIFY_VALIDATE_MOBILE(3, "修改手机号验证"),
        /**
         * 修改登录密码验证手机号 modify_login_password
         */
        MODIFY_LOGIN_PASSWORD(4, "修改登录密码"),

        /**
         * 修改支付密码验证手机号 modify_pay_password
         */
        MODIFY_PAY_PASSWORD(5, "修改支付密码");

        private Integer value;
        private String text;

        private Type(Integer value, String text) {
            this.value = value;
            this.text = text;
            return;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getText() {
            return this.text;
        }
    }
}
