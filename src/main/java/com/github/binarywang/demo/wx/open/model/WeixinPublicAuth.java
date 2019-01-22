package com.github.binarywang.demo.wx.open.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@ToString
public class WeixinPublicAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private int status;

    private Date createTime;

    private Date updateTime;

    private Date expiredTime;
}
