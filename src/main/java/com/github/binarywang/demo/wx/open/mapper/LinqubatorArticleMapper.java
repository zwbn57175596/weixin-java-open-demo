package com.github.binarywang.demo.wx.open.mapper;


import com.github.binarywang.demo.wx.open.model.LinqubatorArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LinqubatorArticleMapper {

    @Insert("INSERT INTO  " +
            " (cover, title, content, author, summary, create_time, update_time) " +
            " VALUES (#{cover}, #{title}, #{content}, #{author}, #{summary}, #{createTime}, #{updateTime}) ")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(LinqubatorArticle linqubatorArticle);
}
