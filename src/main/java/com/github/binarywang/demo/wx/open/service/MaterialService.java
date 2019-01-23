package com.github.binarywang.demo.wx.open.service;

import com.github.binarywang.demo.wx.open.mapper.WeixinPublicArticleMapper;
import com.github.binarywang.demo.wx.open.model.WeixinPublicArticle;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.Random;

@Service
public class MaterialService {

    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    protected WxOpenServiceDemo wxOpenService;

    @Autowired
    private WeixinPublicArticleMapper weixinPublicArticleMapper;

    public WxMpMaterialNewsBatchGetResult getBatchMaterial(String appId) {
        try {
            WxMpMaterialNewsBatchGetResult result = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId)
                    .getMaterialService().materialNewsBatchGet(0, 20);
            if (null != result && result.getItemCount() > 0) {
                result.getItems().forEach(item -> {
                    if (null != item.getContent()) {
                        final String mediaId = item.getMediaId();
                        final Date createTime = item.getContent().getCreatedTime();
                        final Date updateTime = item.getContent().getUpdatedTime();
                        item.getContent().getArticles().forEach(article -> {
                            try {
                                weixinPublicArticleMapper.insert(WeixinPublicArticle.builder()
                                        .appId(appId).mediaId(mediaId).thumbMediaId(article.getThumbMediaId())
                                        .thumbUrl(article.getThumbUrl()).title(article.getTitle()).content(article.getContent())
                                        .author(article.getAuthor()).digest(article.getDigest()).url(article.getUrl())
                                        .createTime(createTime).updateTime(updateTime)
                                        .build()); // insert success
                                // insert local article
                                // todo rest
                            } catch (Exception e) {
                                // duplicate key skip
                                log.error("wechat materialService insert local db error, appId: {}, thumbMediaId: {}"
                                        , appId, article.getThumbMediaId());
                                log.error("", e);
                            }
                        });
                    }
                });
            }
            log.info("wechat materialService batchGetNews, appId: {}, result: {}", appId, result);

        } catch (WxErrorException e) {
            log.error("wechat materialService batchGetNews error, appId: {}", appId);
            log.error("", e);
        }
        return null;
    }
}
