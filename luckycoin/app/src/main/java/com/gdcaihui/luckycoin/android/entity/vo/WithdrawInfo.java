package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 提款信息
 *
 * @author Xiongrui
 */
public class WithdrawInfo extends VoBase {

    private Long id; // 提款申请提交成功返回的ID
    private Integer type; // 提款渠道： 1、微信 2、支付宝
    private Integer amount; // 提款金额
    private Integer step; // 步骤
    private String password; // 登录密码
    private String bankNo; // 提款账号

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
        return;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
        return;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        return;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
}