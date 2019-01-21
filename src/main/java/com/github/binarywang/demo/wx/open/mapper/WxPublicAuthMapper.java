package com.github.binarywang.demo.wx.open.mapper;

import com.github.binarywang.demo.wx.open.model.WxPublicAuth;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WxPublicAuthMapper {

    @Select("SELECT * FROM weixin_public_auth")
    List<WxPublicAuth> getAll();
}
