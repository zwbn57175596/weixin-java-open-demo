package com.github.binarywang.demo.wx.open.model;

import java.io.Serializable;
import java.util.Date;

public class WxPublicAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String openId;

    private String name;

    private int status;

    private Date createTime;

    private Date updateTime;

    public WxPublicAuth() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WxPublicAuth{" +
                "appId='" + appId + '\'' +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
