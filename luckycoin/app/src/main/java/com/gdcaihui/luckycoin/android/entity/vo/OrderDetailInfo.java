package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 支付订单子项信息
 *
 * @author Xiongrui
 */
public class OrderDetailInfo extends VoBase {
    private Integer lotteryId; // 彩种ID
    private String lotteryCode; // 彩种代码
    private String lotteryName; // 彩种名称
    private String lotteryImage; // 彩种图片
    private Integer quantityBuy; // 购买数量
    private Integer price; // 单价
    private Integer amount; // 小计金额

    public Integer getLotteryId() {
        return this.lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
        return;
    }

    public String getLotteryCode() {
        return this.lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
        return;
    }

    public String getLotteryName() {
        return this.lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
        return;
    }

    public String getLotteryImage() {
        return this.lotteryImage;
    }

    public void setLotteryImage(String lotteryImage) {
        this.lotteryImage = lotteryImage;
        return;
    }

    public Integer getQuantityBuy() {
        return this.quantityBuy;
    }

    public void setQuantityBuy(Integer quantityBuy) {
        this.quantityBuy = quantityBuy;
        return;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
        return;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        return;
    }
}