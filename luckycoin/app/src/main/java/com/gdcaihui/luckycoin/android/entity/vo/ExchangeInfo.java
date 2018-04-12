package com.gdcaihui.luckycoin.android.entity.vo;

public class ExchangeInfo extends VoBase {

	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = 8745182282846613414L;

	private String lotteryCode; // 彩票兑奖号

	//private Integer buyCoin; // 购彩所得积分

	//private Integer awardCoin; // 中奖所得积分

	private Integer coin; // 所得积分

	private Integer result; // 兑奖结果

	private String title; // 显示标题

	private String detail; // 显示详情


	public String getLotteryCode() {
		return this.lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
		return;
	}

	/*public Integer getBuyCoin() {
		return this.buyCoin;
	}

	public void setBuyCoin(Integer buyCoin) {
		this.buyCoin = buyCoin;
		return;
	}

	public Integer getAwardCoin() {
		return this.awardCoin;
	}

	public void setAwardCoin(Integer awardCoin) {
		this.awardCoin = awardCoin;
		return;
	}*/

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public enum Result {
		/**
		 * 兑换大乐透积分成功
		 */
		SUCCESS1(1),

		/**
		 * 兑换竞彩积分成功
		 */
		SUCCESS2(2),

		/**
		 * 登记成功
		 */
		RECORD(3),

		/**
		 * 没有奖励
		 */
		NO_REWARD(4),

		/**
		 * 重复扫描，票号已被扫描
		 */
		DUPLICATE(5),

		/**
		 * 彩票已过期
		 */
		OVERDUE(6);
		private Integer value;

		private Result(Integer value) {
			this.value = value;
			return;
		}
		public Integer getValue() {
			return this.value;
		}

	}
}
