package com.idealunited.dto;

import java.util.Date;

public class ProductType {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 中文类别
     */
    private String zhType;

    /**
     * 英文类别
     */
    private String enType;

    /**
     * 
     */
    private Date createDate;

    /**
     * 创建者
     */
    private String creator;

    /**
     *
     * @return the value of t_product_type.ID
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
     * @return the value of t_product_type.ZH_TYPE
     */
    public String getZhType() {
        return zhType;
    }

    /**
     *
     */
    public void setZhType(String zhType) {
        this.zhType = zhType;
    }

    /**
     *
     * @return the value of t_product_type.EN_TYPE
     */
    public String getEnType() {
        return enType;
    }

    /**
     *
     */
    public void setEnType(String enType) {
        this.enType = enType;
    }

    /**
     *
     * @return the value of t_product_type.CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return the value of t_product_type.CREATOR
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