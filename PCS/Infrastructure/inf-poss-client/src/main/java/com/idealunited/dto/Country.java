package com.idealunited.dto;

import java.io.Serializable;

public class Country implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private String zhName;

    private String enName;

    private String code;

    private static final long serialVersionUID = 1L;

    /**
     * <b>获取</b> 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * <b>设置</b> 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}