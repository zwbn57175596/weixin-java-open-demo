package com.github.binarywang.demo.wx.open.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@ToString
public class WeixinPublicArticle implements Serializable {

    private String appId;

    private String mediaId;

    private String thumbMediaId;

    private String thumbUrl;

    private String title;

    private String content;

    private String author;

    private String digest;

    private int showCoverPic;

    private String url;

    private int needOpenComment;

    private int onlyFansCanComment;

    private Date createTime;

    private Date updateTime;

    private int linqubatorArticleId;

}
