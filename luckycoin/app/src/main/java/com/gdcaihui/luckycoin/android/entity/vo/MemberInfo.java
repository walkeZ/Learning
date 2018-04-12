package com.gdcaihui.luckycoin.android.entity.vo;

import java.util.Date;

/**
 * 会员信息
 * 
 * @author Xiongrui
 * 
 */
public class MemberInfo extends VoBase {

	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = -6151502778476126648L;

	private Integer operateType; // 执行操作的类型：修改用户名、修改证件等

	private String maskAccount; // 加密处理的用户名
	private String nickname; // 用户昵称
	private Integer changeNickname; // 是否可以更改昵称 0-不可改 1-可改
	private String password; // 在修改用户名并开启登录时，需要使用，设置登录密码
	private String name; // 真实姓名
	private String email; // 邮箱
	private String headPortrait; // 头像信息
	private Integer certType; // 证件类型 1-身份证
	private String certNo; // 证件号码
	private Integer sex;
	private Date birthday;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private String address;

	//private Integer buyCoinAvailable; // 通过购买的彩票兑换的可用积分
	//private Integer buyCoinFreezed; // 通过购买的彩票兑换的冻结积分
	//private Integer awardCoinAvailable; // 通过中奖的彩票兑换的可用积分
	//private Integer awardCoinFreezed; // 通过中奖的彩票兑换的冻结积分
	//private Integer totalCoin; // 总积分

	private Integer coin1; // 大乐透购彩所得积分，用于兑换礼品
	private Integer coin2; // 竞彩奖金所得积分，用于排名
	private Integer coin3; // 保留
	private Integer coin4; // 保留

	private Long qualifying; // 排名

	/**
	 * 操作类型
	 * 
	 * @author Xiongrui
	 * 
	 */
	public enum OperateType {
		/**
		 * 加载信息 load_info
		 */
		LOAD_INFO(1),

		/**
		 * 修改昵称 modify_nickname
		 */
		MODIFY_NICKNAME(2),

		/**
		 * 修改证件 modify_cert
		 */
		MODIFY_CERT(3),
		/**
		 * 修改头像信息
		 */
		MODIFY_HEAD(4);

		private Integer value;

		private OperateType(Integer value) {
			this.value = value;
			return;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	public Integer getOperateType() {
		return this.operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
		return;
	}

	public String getMaskAccount() {
		return this.maskAccount;
	}

	public void setMaskAccount(String maskAccount) {
		this.maskAccount = maskAccount;
		return;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		return;
	}

	public Integer getChangeNickname() {
		return this.changeNickname;
	}

	public void setChangeNickname(Integer changeNickname) {
		this.changeNickname = changeNickname;
		return;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		return;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		return;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
		return;
	}

	public String getHeadPortrait() {
		return this.headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
		return;
	}

	public Integer getCertType() {
		return this.certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
		return;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
		return;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
		return;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		return;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
		return;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
		return;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
		return;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
		return;
	}

	public Integer getCoin1() {
		return coin1;
	}

	public void setCoin1(Integer coin1) {
		this.coin1 = coin1;
	}

	public Integer getCoin2() {
		return coin2;
	}

	public void setCoin2(Integer coin2) {
		this.coin2 = coin2;
	}

	public Integer getCoin3() {
		return coin3;
	}

	public void setCoin3(Integer coin3) {
		this.coin3 = coin3;
	}

	public Integer getCoin4() {
		return coin4;
	}

	public void setCoin4(Integer coin4) {
		this.coin4 = coin4;
	}
	/*public Integer getBuyCoinAvailable() {
		return this.buyCoinAvailable;
	}

	public void setBuyCoinAvailable(Integer buyCoinAvailable) {
		this.buyCoinAvailable = buyCoinAvailable;
		return;
	}

	public Integer getBuyCoinFreezed() {
		return this.buyCoinFreezed;
	}

	public void setBuyCoinFreezed(Integer buyCoinFreezed) {
		this.buyCoinFreezed = buyCoinFreezed;
		return;
	}

	public Integer getAwardCoinAvailable() {
		return this.awardCoinAvailable;
	}

	public void setAwardCoinAvailable(Integer awardCoinAvailable) {
		this.awardCoinAvailable = awardCoinAvailable;
		return;
	}

	public Integer getAwardCoinFreezed() {
		return this.awardCoinFreezed;
	}

	public void setAwardCoinFreezed(Integer awardCoinFreezed) {
		this.awardCoinFreezed = awardCoinFreezed;
		return;
	}

	public Integer getTotalCoin() {
		return this.totalCoin;
	}

	public void setTotalCoin(Integer totalCoin) {
		this.totalCoin = totalCoin;
		return;
	}*/


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getQualifying() {
		return qualifying;
	}

	public void setQualifying(Long qualifying) {
		this.qualifying = qualifying;
	}
}