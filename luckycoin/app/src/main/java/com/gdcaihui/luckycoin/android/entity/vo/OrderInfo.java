package com.gdcaihui.luckycoin.android.entity.vo;


import com.gdcaihui.luckycoin.android.entity.PlatformType;

import java.util.List;

/**
 * 订单信息
 * 
 * @author Xiongrui
 * 
 */
public class OrderInfo extends VoBase {

	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = 8829598327653805853L;

	private Long id; // 订单ID
	private Integer terminalId; // 所属终端
	private Integer amount; // 订单总金额
	private Integer count; // 订单总数量
	private List<OrderDetailInfo> detailInfoList; // 子项信息集合

	private Integer payWay; // 支付方式
	private Integer setp; // 步骤
	private String requestURL; // 支付调用url
	private String password; // 支付密码
	private String payResult;// 支付结果

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
		return;
	}

	public Integer getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
		return;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
		return;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
		return;
	}

	public List<OrderDetailInfo> getDetailInfoList() {
		return this.detailInfoList;
	}

	public void setDetailInfoList(List<OrderDetailInfo> detailInfoList) {
		this.detailInfoList = detailInfoList;
		return;
	}

	public Integer getPayWay() {
		return this.payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
		return;
	}

	public Integer getSetp() {
		return this.setp;
	}

	public void setSetp(Integer setp) {
		this.setp = setp;
		return;
	}

	public String getRequestURL() {
		return this.requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
		return;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		return;
	}

	public String getPayResult() {
		return this.payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
		return;
	}

	@Override
	public String toString() {
		return "OrderInfo{" +
				"id=" + id +
				", terminalId=" + terminalId +
				", amount=" + amount +
				", count=" + count +
				", detailInfoList=" + detailInfoList +
				", payWay=" + payWay +
				", setp=" + setp +
				", requestURL='" + requestURL + '\'' +
				", password='" + password + '\'' +
				", payResult='" + payResult + '\'' +
				'}';
	}

	/**
	 * 支付方式
	 *
	 * @author Xiongrui
	 *
	 */
	public enum PayWay {
		/**
		 * 状态 - wechat
		 *
		 * @value 1
		 */
		WECHAT(1, "微信", PlatformType.WECHAT),

		/**
		 * 支付宝 - alipay
		 *
		 * @value 2
		 */
		ALIPAY(2, "支付宝", PlatformType.WECHAT),

		/**
		 * 奖金换购
		 *
		 * @value 3
		 */
		EXCHANGE(3, "奖金换购", PlatformType.GGY),

		/**
		 * 钱包余额 wallet
		 *
		 * @value 4
		 */
		WALLET(4, "钱包余额", PlatformType.GGY);

		private Integer value;
		private String text;
		private PlatformType platformType;

		private PayWay(Integer value, String text, PlatformType platformType) {
			this.value = value;
			this.text = text;
			this.platformType = platformType;
			return;
		}

		public Integer getValue() {
			return this.value;
		}

		public String getText() {
			return this.text;
		}

		public PlatformType getPlatformType() {
			return this.platformType;
		}

		public static PayWay getByValue(Integer value) {
			try {
				for (PayWay val : PayWay.values()) {
					if (val.value.equals(value))
						return val;
				}
			} catch (Exception e) {
			}
			return null;
		}

		public static String getNameByValue(Integer value) {
			PayWay payWay = PayWay.getByValue(value);
			if (payWay != null)
				return payWay.getText();
			return "";
		}
	}


}