package com.github.binarywang.demo.wx.open.model;

import java.io.Serializable;
import java.util.Date;

public class WeixinPublicAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private int status;

    private Date createTime;

    private Date updateTime;

    private Date expiredTime;

    public WeixinPublicAuth() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "WeixinPublicAuth{" +
                "appId='" + appId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", expiredTime=" + expiredTime +
                '}';
    }
}
