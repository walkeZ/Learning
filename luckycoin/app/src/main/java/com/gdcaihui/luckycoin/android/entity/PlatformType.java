package com.gdcaihui.luckycoin.android.entity;

/**
 * 平台类型
 *
 * @author Xiongrui
 */
public enum PlatformType {
    /**
     * 刮刮易 value=0
     */
    GGY(0, "刮刮易", "ggy"),

    /**
     * 微信 value=1
     */
    WECHAT(1, "微信", "wx"),

    /**
     * 支付宝 value=2
     */
    ALIPAY(2, "支付宝", "ali");

    private Integer value;
    private String text;
    private String code;

    PlatformType(Integer value, String text, String code) {
        this.value = value;
        this.text = text;
        this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }

    public String getCode() {
        return this.code;
    }

    public static String getTextByValue(Integer value) {
        try {
            for (PlatformType val : PlatformType.values()) {
                if (val.value == value)
                    return val.text;
            }
        } catch (Exception e) {
        }
        return "";
    }
}
