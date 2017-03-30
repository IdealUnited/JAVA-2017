package com.idealunited.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Quotation implements Serializable {
	private Long id;

	/**
	 * 物流渠道编号
	 */
	private Integer channelId;

	private String channelCode;

	private String channelName;

	/**
	 * 渠道通道编号
	 */
	private Integer channelItemId;

	private String channelItemName;

	/**
	 * 国家编号
	 */
	private String countryCode;

	/**
	 * 国家名称
	 */
	private String countryName;

	/**
	 * 类型：1-挂号，2-平邮，3-专线
	 */
	private Integer type;

	/**
	 * 计价单位：克重
	 */
	private Integer calculateUnit;

	/**
	 * 续重单位克重
	 */
	private Integer continuedUnit;

	/**
	 * 续重单价
	 */
	private BigDecimal continuedPrice;

	/**
	 * 起重（克）
	 */
	private Integer minWeight;

	/**
	 * 限重（克）
	 */
	private Integer maxWeight;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 挂号费或者处理费
	 */
	private BigDecimal fee;

	/**
	 * 是否含电报费：0-不含，1-包含
	 */
	private Integer flag;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 状态：0-失效，1-有效
	 */
	private Integer status;

	private Date createDate;

	/**
	 * 创建者
	 */
	private String creator;

	/**
	 * 时效性，工作日
	 */
	private String timeliness;

	/**
	 * 周长限制
	 */
	private BigDecimal circumference;

	/**
	 * 单边长
	 */
	private BigDecimal length;

	private static final long serialVersionUID = 1L;

	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <b>获取</b> 物流渠道编号
	 */
	public Integer getChannelId() {
		return channelId;
	}

	/**
	 * <b>设置</b> 物流渠道编号
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	/**
	 * <b>获取</b> 渠道通道编号
	 */
	public Integer getChannelItemId() {
		return channelItemId;
	}

	/**
	 * <b>设置</b> 渠道通道编号
	 */
	public void setChannelItemId(Integer channelItemId) {
		this.channelItemId = channelItemId;
	}

	/**
	 * <b>获取</b> 国家编号
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * <b>设置</b> 国家编号
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * <b>获取</b> 国家名称
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * <b>设置</b> 国家名称
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * <b>获取</b> 类型：1-挂号，2-平邮
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * <b>设置</b> 类型：1-挂号，2-平邮
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * <b>获取</b> 计价单位：克重
	 */
	public Integer getCalculateUnit() {
		return calculateUnit;
	}

	/**
	 * <b>设置</b> 计价单位：克重
	 */
	public void setCalculateUnit(Integer calculateUnit) {
		this.calculateUnit = calculateUnit;
	}

	/**
	 * <b>获取</b> 价格
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * <b>设置</b> 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * <b>获取</b> 挂号费或者处理费
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * <b>设置</b> 挂号费或者处理费
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * <b>获取</b> 是否含电报费：0-不含，1-包含
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * <b>设置</b> 是否含电报费：0-不含，1-包含
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * <b>获取</b> 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <b>设置</b> 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <b>获取</b> 状态：0-失效，1-有效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <b>设置</b> 状态：0-失效，1-有效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * <b>获取</b> 创建者
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * <b>设置</b> 创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * <b>获取</b> 时效性，工作日
	 */
	public String getTimeliness() {
		return timeliness;
	}

	/**
	 * <b>设置</b> 时效性，工作日
	 */
	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	/**
	 * <b>获取</b> 周长限制
	 */
	public BigDecimal getCircumference() {
		return circumference;
	}

	/**
	 * <b>设置</b> 周长限制
	 */
	public void setCircumference(BigDecimal circumference) {
		this.circumference = circumference;
	}

	/**
	 * <b>获取</b> 单边长
	 */
	public BigDecimal getLength() {
		return length;
	}

	/**
	 * <b>设置</b> 单边长
	 */
	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelItemName() {
		return channelItemName;
	}

	public void setChannelItemName(String channelItemName) {
		this.channelItemName = channelItemName;
	}

	public Integer getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Integer minWeight) {
		this.minWeight = minWeight;
	}

	public Integer getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Integer getContinuedUnit() {
		return continuedUnit;
	}

	public void setContinuedUnit(Integer continuedUnit) {
		this.continuedUnit = continuedUnit;
	}

	public BigDecimal getContinuedPrice() {
		return continuedPrice;
	}

	public void setContinuedPrice(BigDecimal continuedPrice) {
		this.continuedPrice = continuedPrice;
	}
}