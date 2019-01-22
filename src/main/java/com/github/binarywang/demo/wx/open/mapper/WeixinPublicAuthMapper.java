package com.github.binarywang.demo.wx.open.mapper;

import com.github.binarywang.demo.wx.open.model.WeixinPublicAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WeixinPublicAuthMapper {

    @Select("SELECT * FROM weixin_public_auth")
    List<WeixinPublicAuth> getAll();

    @Insert("INSERT INTO userweixin_public_auths" +
            " (appId, expired_time) VALUES(#{appId}, #{expiredTime})")
    void insert(WeixinPublicAuth weixinPublicAuth);
}
