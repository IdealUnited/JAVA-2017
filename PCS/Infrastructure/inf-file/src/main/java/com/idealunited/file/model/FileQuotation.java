package com.idealunited.file.model;

import java.math.BigDecimal;

public class FileQuotation {
	/**
     * 
     */
	private Long id;

	/**
	 * 物流渠道编号
	 */
	private Integer channelId;

	/**
	 * 渠道编号
	 */
	private String channelCode;

	/**
	 * 渠道通道编号
	 */
	private Integer channelItemId;

	/**
	 * 物流渠道下通道名称
	 */
	private String channelItemName;

	/**
	 * 国家英文名称
	 */
	private String countryName;

	/**
	 * 国家编号
	 */
	private String countryCode;

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
	 * 类型：1-挂号，2-平邮，3-专线
	 */
	private Integer type;

	/**
	 * 是否含电报费：0-不含，1-包含
	 */
	private Integer flag;

	/**
	 * 状态：0-失效，1-有效
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

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

	private String title;
	
	/**
	 *
	 * @return the value of t_quotation.id
	 */
	public Long getId() {
		return id;
	}

	/**
     *
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *
	 * @return the value of t_quotation.CHANNEL_ID
	 */
	public Integer getChannelId() {
		return channelId;
	}

	/**
     *
     */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	/**
	 *
	 * @return the value of t_quotation.CHANNEL_ITEM_ID
	 */
	public Integer getChannelItemId() {
		return channelItemId;
	}

	/**
     *
     */
	public void setChannelItemId(Integer channelItemId) {
		this.channelItemId = channelItemId;
	}

	/**
	 *
	 * @return the value of t_quotation.COUNTRY_CODE
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
     *
     */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 *
	 * @return the value of t_quotation.TYPE
	 */
	public Integer getType() {
		return type;
	}

	/**
     *
     */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 *
	 * @return the value of t_quotation.FLAG
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
     *
     */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 *
	 * @return the value of t_quotation.remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
     *
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getChannelItemName() {
		return channelItemName;
	}

	public void setChannelItemName(String channelItemName) {
		this.channelItemName = channelItemName;
	}

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	public BigDecimal getCircumference() {
		return circumference;
	}

	public void setCircumference(BigDecimal circumference) {
		this.circumference = circumference;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public Integer getCalculateUnit() {
		return calculateUnit;
	}

	public void setCalculateUnit(Integer calculateUnit) {
		this.calculateUnit = calculateUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("countryName:" + countryName);
		sb.append("\ncalculateUnit:" + calculateUnit);
		sb.append("\nprice:" + price);
		sb.append("\nfee:" + fee);
		sb.append("\ntype:" + type);
		sb.append("\nminWeight:" + minWeight);
		sb.append("\ncontinuedPrice:" + continuedPrice);

		return sb.toString();
	}
}