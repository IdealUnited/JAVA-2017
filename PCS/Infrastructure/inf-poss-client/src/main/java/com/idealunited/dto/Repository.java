package com.idealunited.dto;

import java.util.Date;

public class Repository {
    /**
     * 仓库编号
     */
    private Integer id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 
     */
    private Date createDate;

    /**
     *
     * @return the value of t_repository.ID
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
     * @return the value of t_repository.name
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
     * @return the value of t_repository.create_date
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
}