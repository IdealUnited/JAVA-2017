package com.idealunited.dto;

import java.util.Date;

public class ExpressChannelItem {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer channelId;

    /**
     * 0-失效，1-有效
     */
    private Integer status;

    /**
     * 
     */
    private Date createDate;

    /**
     * 物流子类
     */
    private String name;

    /**
     * 创建者
     */
    private String creator;

    /**
     *
     * @return the value of t_express_channel_item.ID
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
     * @return the value of t_express_channel_item.CHANNEL_ID
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
     * @return the value of t_express_channel_item.STATUS
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
     * @return the value of t_express_channel_item.CREATE_DATE
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
     * @return the value of t_express_channel_item.NAME
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
     * @return the value of t_express_channel_item.CREATOR
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