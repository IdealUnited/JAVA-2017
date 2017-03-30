package com.idealunited.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ProductStock implements Serializable {
	
	private final Log logger = LogFactory.getLog(getClass());

	public static final String[] properties = new String[] { "thumbnails",
			"name", "stockCode", "stockNum", "prices", "specifications", "l",
			"w", "h", "weight", "typeZh", "typeEn", "material", "suppliers",
			"declarePrices", "batteryFlg", "remark" };

	private Long id;

	private String stockCode;

	private String name;

	/**
	 * 库存数量
	 */
	private Integer stockNum;

	/**
	 * 进货价
	 */
	private BigDecimal prices;

	private String specifications;

	/**
	 * 长
	 */
	private BigDecimal l;

	/**
	 * 宽
	 */
	private BigDecimal w;

	/**
	 * 高
	 */
	private BigDecimal h;

	/**
	 * 重量
	 */
	private BigDecimal weight;

	private BigDecimal weightStart;
	private BigDecimal weightEnd;

	/**
	 * 产品中文类别
	 */
	private String typeZh;

	/**
	 * 产品英文类别
	 */
	private String typeEn;

	/**
	 * 材质
	 */
	private String material;

	/**
	 * 供应商
	 */
	private String suppliers;

	/**
	 * 海关申报价格
	 */
	private BigDecimal declarePrices;

	/**
	 * 是否含电
	 */
	private Integer batteryFlg;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建者
	 */
	private String creator;

	/**
	 * 状态：0-创建,1-已复核,2-失效
	 */
	private Integer status;

	/**
	 * 复核者
	 */
	private String reaudite;

	/**
	 * 安全库存
	 */
	private Integer alertStockNum;

	/**
	 * 缩略图路径
	 */
	private String thumbnails;

	/**
	 * sku
	 */
	private String sku;

	private String repositoryId;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <b>获取</b> 库存数量
	 */
	public Integer getStockNum() {
		return stockNum;
	}

	/**
	 * <b>设置</b> 库存数量
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	/**
	 * <b>获取</b> 进货价
	 */
	public BigDecimal getPrices() {
		return prices;
	}

	/**
	 * <b>设置</b> 进货价
	 */
	public void setPrices(BigDecimal prices) {
		this.prices = prices;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	/**
	 * <b>获取</b> 长
	 */
	public BigDecimal getL() {
		return l;
	}

	/**
	 * <b>设置</b> 长
	 */
	public void setL(BigDecimal l) {
		this.l = l;
	}

	/**
	 * <b>获取</b> 宽
	 */
	public BigDecimal getW() {
		return w;
	}

	/**
	 * <b>设置</b> 宽
	 */
	public void setW(BigDecimal w) {
		this.w = w;
	}

	/**
	 * <b>获取</b> 高
	 */
	public BigDecimal getH() {
		return h;
	}

	/**
	 * <b>设置</b> 高
	 */
	public void setH(BigDecimal h) {
		this.h = h;
	}

	/**
	 * <b>获取</b> 重量
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	/**
	 * <b>设置</b> 重量
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/**
	 * <b>获取</b> 产品中文类别
	 */
	public String getTypeZh() {
		return typeZh;
	}

	/**
	 * <b>设置</b> 产品中文类别
	 */
	public void setTypeZh(String typeZh) {
		this.typeZh = typeZh;
	}

	/**
	 * <b>获取</b> 产品英文类别
	 */
	public String getTypeEn() {
		return typeEn;
	}

	/**
	 * <b>设置</b> 产品英文类别
	 */
	public void setTypeEn(String typeEn) {
		this.typeEn = typeEn;
	}

	/**
	 * <b>获取</b> 材质
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * <b>设置</b> 材质
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * <b>获取</b> 供应商
	 */
	public String getSuppliers() {
		return suppliers;
	}

	/**
	 * <b>设置</b> 供应商
	 */
	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}

	/**
	 * <b>获取</b> 海关申报价格
	 */
	public BigDecimal getDeclarePrices() {
		return declarePrices;
	}

	/**
	 * <b>设置</b> 海关申报价格
	 */
	public void setDeclarePrices(BigDecimal declarePrices) {
		this.declarePrices = declarePrices;
	}

	/**
	 * <b>获取</b> 是否含电
	 */
	public Integer getBatteryFlg() {
		return batteryFlg;
	}

	/**
	 * <b>设置</b> 是否含电
	 */
	public void setBatteryFlg(Integer batteryFlg) {
		this.batteryFlg = batteryFlg;
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
	 * <b>获取</b> 状态：0-创建,1-已复核,2-失效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <b>设置</b> 状态：0-创建,1-已复核,2-失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <b>获取</b> 复核者
	 */
	public String getReaudite() {
		return reaudite;
	}

	/**
	 * <b>设置</b> 复核者
	 */
	public void setReaudite(String reaudite) {
		this.reaudite = reaudite;
	}

	/**
	 * <b>获取</b> 安全库存
	 */
	public Integer getAlertStockNum() {
		return alertStockNum;
	}

	/**
	 * <b>设置</b> 安全库存
	 */
	public void setAlertStockNum(Integer alertStockNum) {
		this.alertStockNum = alertStockNum;
	}

	/**
	 * <b>获取</b> 缩略图路径
	 */
	public String getThumbnails() {
		return thumbnails;
	}

	/**
	 * <b>设置</b> 缩略图路径
	 */
	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	/**
	 * <b>获取</b> sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * <b>设置</b> sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getWeightStart() {
		return weightStart;
	}

	public void setWeightStart(BigDecimal weightStart) {
		this.weightStart = weightStart;
	}

	public BigDecimal getWeightEnd() {
		return weightEnd;
	}

	public void setWeightEnd(BigDecimal weightEnd) {
		this.weightEnd = weightEnd;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	/**
	 * 设置值
	 * 
	 * @param properties
	 * @param value
	 */
	public void setValue(String properties, Object value) {

		// 例外处理
		if ("batteryFlg".equals(properties)) {
			if ("否".equals(value)) {
				setBatteryFlg(0);
			} else {
				setBatteryFlg(1);
			}
		} else {
			BeanWrapper beanWrapper = new BeanWrapperImpl(this);
			Class clz = beanWrapper.getPropertyType(properties);
			
			logger.info("propertyName:" + properties + ",propertyValue:" + value + ",class type:" + clz);
			if("name".equals(properties)){
				value = String.valueOf(value);
			}
			if("stockCode".equals(properties)){
				value = String.valueOf(value);
			}
			
			beanWrapper.setPropertyValue(properties, value);
		}

	}
}