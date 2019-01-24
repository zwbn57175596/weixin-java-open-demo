package com.github.binarywang.demo.wx.open.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Builder
public class LinqubatorArticle {

    private int id;

    private String title;

    private String content;

    private int type;

    private String summary;

    private String author;

    private String cover;

    private int userId;

    private int status;

    private String tag;

    private int views;

    private int agrees;

    private int shares;

    private int recommend;

    private int sort;

    private int createBy;

    private Date createTime;

    private int updateBy;

    private Date updateTime;

    private int delFlag;

    private int userType;
}
