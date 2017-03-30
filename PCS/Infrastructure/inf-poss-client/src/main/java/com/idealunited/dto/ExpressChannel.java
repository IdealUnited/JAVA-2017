package com.idealunited.dto;

public class ExpressChannel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 渠道编号
     */
    private String code;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 状态：0-失效,1-生效
     */
    private Integer status;

    /**
     * 物流商户号
     */
    private String merchantNo;

    /**
     * 创建者
     */
    private String creator;

    /**
     *
     * @return the value of t_express_channel.ID
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return the value of t_express_channel.CODE
     */
    public String getCode() {
        return code;
    }

    /**
     *
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return the value of t_express_channel.NAME
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the value of t_express_channel.STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return the value of t_express_channel.MERCHANT_NO
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     *
     * @return the value of t_express_channel.creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     *
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}