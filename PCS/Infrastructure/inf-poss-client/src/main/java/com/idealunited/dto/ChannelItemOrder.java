package com.idealunited.dto;

import java.util.Date;

public class ChannelItemOrder {
    /**
     * 渠道子订单
     */
    private String channelItemOrderNo;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 交易明细订单号
     */
    private String tradeItemOrderNo;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private Integer status;

    /**
     *
     * @return the value of t_channel_item_order.CHANNEL_ITEM_ORDER_NO
     */
    public String getChannelItemOrderNo() {
        return channelItemOrderNo;
    }

    /**
     *
     */
    public void setChannelItemOrderNo(String channelItemOrderNo) {
        this.channelItemOrderNo = channelItemOrderNo;
    }

    /**
     *
     * @return the value of t_channel_item_order.CHANNEL_ORDER_NO
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
     * @return the value of t_channel_item_order.TRADE_ITEM_ORDER_NO
     */
    public String getTradeItemOrderNo() {
        return tradeItemOrderNo;
    }

    /**
     *
     */
    public void setTradeItemOrderNo(String tradeItemOrderNo) {
        this.tradeItemOrderNo = tradeItemOrderNo;
    }

    /**
     *
     * @return the value of t_channel_item_order.CREATE_DATE
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
     * @return the value of t_channel_item_order.STATUS
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
}