package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 资金记录
 *
 * @author Xiongrui
 */
public class FundRecord extends VoBase {
    private Long fid; // 记录ID
    private Integer type; // 类型(参考枚举)： 1、充值 2、购买 3、提现 4、兑奖
    private Integer amount; // 金额数 +增加 -减少
    private Integer balance; // 余额，单位：分
    private Integer freeze; // 冻结金额，单位：分
    private String dateAdded; // 发生时间
    private Long outId; // 外部单号
    private String remark; // 备注
    private String typeText; // 类型名称
    private Integer openDetail; // 是否可查看详情

    public Long getFid() {
        return this.fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
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

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
        return;
    }

    public Integer getFreeze() {
        return this.freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
        return;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
        return;
    }

    public Long getOutId() {
        return this.outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
        return;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        return;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Integer getOpenDetail() {
        return openDetail;
    }

    public void setOpenDetail(Integer openDetail) {
        this.openDetail = openDetail;
    }

    public enum Type {
        /**
         * 1、充值 recharge
         */
        RECHARGE(1, "充值"),

        /**
         * 2、购买 buy
         */
        BUY(2, "购买"),

        /**
         * 3、提现 withdraw
         */
        WITHDRAW(3, "提现"),

        /**
         * 4、兑奖 reward
         */
        REWARD(4, "兑奖"),

        /**
         * 5、冻结 freeze
         */
        FREEZE(5, "冻结"),

        /**
         * 6、冲正 correct
         */
        CORRECT(6, "冲正"),

        /**
         * 7、赠送 credit
         */
        CREDIT(7, "赠送"),
        /**
         * 8、合并 merge
         */
        MERGE(8, "合并"),//, "/images/mobile/funds_details_credit.png"

        /**
         * 9、消户 cancel_x
         */
        CANCEL(9, "销户");//, "/images/mobile/funds_details_freeze.png"

        private Integer value;
        private String text;

        Type(Integer value, String text) {
            this.value = value;
            this.text = text;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getText() {
            return this.text;
        }

        public static String getTextByValue(Integer value) {
            try {
                for (Type val : Type.values()) {
                    if (val.value == value)
                        return val.text;
                }
            } catch (Exception e) {
            }
            return "";
        }
    }
}