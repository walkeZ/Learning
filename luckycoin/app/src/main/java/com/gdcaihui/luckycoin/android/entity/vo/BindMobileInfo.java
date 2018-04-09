package com.gdcaihui.luckycoin.android.entity.vo;


/**
 * 绑定手机号码信息
 *
 * @author Xiongrui
 */
public class BindMobileInfo extends VoBase {

    private Integer type; // 绑定、解绑
    private Integer step; // 步聚：修改手机号码时使用，0，获取信息 1，验证信息
    private String mobile; // 手机号码
    private String validate; // 绑定时是手机验证码，解绑时为登录密码
    private Integer time; // 倒计时间
    private Integer intent; // 意图：1.设置登录密码 2.设置支付密码

    public Integer getIntent() {
        return intent;
    }

    public void setIntent(Integer intent) {
        this.intent = intent;
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

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        return;
    }

    public String getValidate() {
        return this.validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
        return;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
        return;
    }

    /**
     * 手机号码操作类型：绑定、解绑
     *
     * @author Xiongrui
     */
    public enum Type {
        /**
         * 绑定 bind
         */
        BIND(1, "绑定"),

        /**
         * 修改 modify
         */
        MODIFY(2, "修改");

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

    /**
     * 绑定手机号码是通过哪种意图触发
     *
     * @author Xiongrui
     *
     */
    public enum Intent {
        /**
         * 修改登录密码 login_password
         */
        LOGIN_PASSWORD(1, "修改登录密码"),

        /**
         * 修改支付密码 pay_password
         */
        PAY_PASSWORD(2, "修改支付密码");

        private Integer value;
        private String text;

        private Intent(Integer value, String text) {
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