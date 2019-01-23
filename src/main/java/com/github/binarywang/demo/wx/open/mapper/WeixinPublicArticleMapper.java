package com.github.binarywang.demo.wx.open.mapper;

import com.github.binarywang.demo.wx.open.model.WeixinPublicArticle;
import com.github.binarywang.demo.wx.open.model.WeixinPublicAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WeixinPublicArticleMapper {

    @Select("SELECT * FROM weixin_public_auth")
    List<WeixinPublicAuth> getAll();

    @Insert("INSERT INTO weixin_public_article_sync " +
            " (app_id, media_id, thumb_media_id, thumb_url, title, content, author, digest, show_cover_pic, url, " +
            "     need_open_comment, only_fans_can_comment, create_time, update_time, linqubator_article_id)" +
            " VALUES(#{appId}, #{mediaId}, #{thumbMediaId}, #{thumbUrl}, #{title}, #{content}, #{author}, #{digest}, " +
            "    #{showCoverPic}, #{url}, #{needOpenComment}, #{onlyFansCanComment}, #{createTime}, #{updateTime}, " +
            "    #{linqubatorArticleId})")
    void insert(WeixinPublicArticle weixinPublicArticle);

    @Update("UPDATE weixin_public_article_sync " +
            "    SET linqubator_article_id = #{linqubatorArticleId} WHERE thumb_media_id = #{thumbMediaId}")
    void update(WeixinPublicArticle weixinPublicArticle);
}
