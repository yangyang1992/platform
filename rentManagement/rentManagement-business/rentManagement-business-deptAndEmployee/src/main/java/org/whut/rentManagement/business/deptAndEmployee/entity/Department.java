package org.whut.rentManagement.business.deptAndEmployee.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liubei1203
 * Date: 14-10-12
 * Time: 下午8:40
 * To change this template use File | Settings | File Templates.
 */
public class Department {
    private long id;
    private String name;
    private String description;
    private Date createTime;
    private long appId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
