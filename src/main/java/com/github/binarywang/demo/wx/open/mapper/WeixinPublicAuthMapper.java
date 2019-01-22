package com.github.binarywang.demo.wx.open.mapper;

import com.github.binarywang.demo.wx.open.model.WeixinPublicAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WeixinPublicAuthMapper {

    @Select("SELECT * FROM weixin_public_auth")
    List<WeixinPublicAuth> getAll();

    @Insert("INSERT INTO weixin_public_auth" +
            " (appId, expired_time) VALUES(#{appId}, #{expiredTime})")
    void insert(WeixinPublicAuth weixinPublicAuth);
}
