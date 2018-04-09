package com.gdcaihui.luckycoin.android.entity.vo;

/**
 * 执行兑奖信息
 *
 * @author Xiongrui
 */
public class RewardInfo extends VoBase {

    private String ticketNo; // 票号
    private String ticketPwd; // 票密码
    private Integer way; // 兑奖方式 1-扫码兑奖,2-手工兑奖
    private Integer cashed; // 是否兑过奖 0-未兑,1-已兑
    private Integer result; // 兑奖结果 1-未知,2-已中奖,3-未中奖,4-大奖
    private Integer rewardAmount; // 中奖金额,单位:分
    private Integer extraAmount; // 加奖奖金,单位:分

    public Integer getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(Integer extraAmount) {
        this.extraAmount = extraAmount;
    }

    public String getTicketNo() {
        return this.ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
        return;
    }

    public String getTicketPwd() {
        return this.ticketPwd;
    }

    public void setTicketPwd(String ticketPwd) {
        this.ticketPwd = ticketPwd;
        return;
    }

    public Integer getWay() {
        return this.way;
    }

    public void setWay(Integer way) {
        this.way = way;
        return;
    }

    public Integer getCashed() {
        return this.cashed;
    }

    public void setCashed(Integer cashed) {
        this.cashed = cashed;
        return;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
        return;
    }

    public Integer getRewardAmount() {
        return this.rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
        return;
    }

    /**
     * 兑奖方式
     *
     * @author Xiongrui
     */
    public enum Way {
        /**
         * 扫描兑奖 - scan
         *
         * @value 1
         */
        SCAN(1, "扫描兑奖"),

        /**
         * 手工兑奖 - manual
         *
         * @value 2
         */
        MANUAL(2, "手工兑奖");

        private Integer value;
        private String text;

        private Way(Integer value, String text) {
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
     * 中奖结果
     *
     * @author Xiongrui
     */
    public enum Result {
        /**
         * 未兑奖 - null
         *
         * @value 1
         */
        NULL(1, "未兑奖"),

        /**
         * 已中奖 - winning
         *
         * @value 2
         */
        WINNING(2, "已中奖"),

        /**
         * 未中奖 - not_winning
         *
         * @value 3
         */
        NOT_WINNING(3, "未中奖"),

        /**
         * 大奖 - big_winning
         *
         * @value 4
         */
        BIG_WINNING(4, "大奖");

        private Integer value;
        private String text;

        private Result(Integer value, String text) {
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

        public static Result getByValue(Integer value) {
            try {
                for (Result val : Result.values()) {
                    if (val.value.equals(value))
                        return val;
                }
            } catch (Exception e) {
            }
            return Result.NULL;
        }
    }
}