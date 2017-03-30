package com.idealunited.dto;

import java.util.Date;

public class ChannelOrder {
    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 渠道编号
     */
    private Integer channelId;

    /**
     * 通道编号
     */
    private Integer channelItemId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 完成时间
     */
    private Date completeDate;

    /**
     * 0-创建,1-成功,2-失败
     */
    private Integer status;

    /**
     * 物流单号
     */
    private String expressOrderNo;

    /**
     * 
     */
    private Date expressUpdateDate;

    /**
     *
     * @return the value of t_channel_order.CHANNEL_ORDER_NO
     */
    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    /**
     *
     */
    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    /**
     *
     * @return the value of t_channel_order.CHANNEL_ID
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
     * @return the value of t_channel_order.CHANNEL_ITEM_ID
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
     * @return the value of t_channel_order.CREATE_DATE
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
     * @return the value of t_channel_order.COMPLETE_DATE
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     *
     */
    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    /**
     *
     * @return the value of t_channel_order.STATUS
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
     * @return the value of t_channel_order.EXPRESS_ORDER_NO
     */
    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    /**
     *
     */
    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    /**
     *
     * @return the value of t_channel_order.EXPRESS_UPDATE_DATE
     */
    public Date getExpressUpdateDate() {
        return expressUpdateDate;
    }

    /**
     *
     */
    public void setExpressUpdateDate(Date expressUpdateDate) {
        this.expressUpdateDate = expressUpdateDate;
    }
}