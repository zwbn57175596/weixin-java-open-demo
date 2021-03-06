package com.github.binarywang.demo.wx.open.service;

import com.github.binarywang.demo.wx.open.mapper.LinqubatorArticleMapper;
import com.github.binarywang.demo.wx.open.mapper.WeixinPublicArticleMapper;
import com.github.binarywang.demo.wx.open.model.LinqubatorArticle;
import com.github.binarywang.demo.wx.open.model.WeixinPublicArticle;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MaterialService {

    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Resource
    private WxOpenServiceDemo wxOpenService;

    @Resource
    private WeixinPublicArticleMapper weixinPublicArticleMapper;

    @Resource
    private LinqubatorArticleMapper linqubatorArticleMapper;

    public WxMpMaterialNewsBatchGetResult getBatchMaterial(String appId, int adminUserId) {
        try {
            WxMpMaterialNewsBatchGetResult result = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId)
                    .getMaterialService().materialNewsBatchGet(0, 20);
            if (null != result && result.getItemCount() > 0) {
                result.getItems().forEach(item -> {
                    if (null != item.getContent()) {
                        final String mediaId = item.getMediaId();
                        final Date createTime = item.getContent().getCreateTime();
                        final Date updateTime = item.getContent().getUpdateTime();
                        item.getContent().getArticles().forEach(article -> {
                            try {
                                WeixinPublicArticle weixinPublicArticle = WeixinPublicArticle.builder()
                                        .appId(appId).mediaId(mediaId).thumbMediaId(article.getThumbMediaId())
                                        .thumbUrl(article.getThumbUrl()).title(article.getTitle()).content(article.getContent())
                                        .author(article.getAuthor()).digest(article.getDigest()).url(article.getUrl())
                                        .createTime(createTime).updateTime(updateTime)
                                        .build();
                                weixinPublicArticleMapper.insert(weixinPublicArticle); // insert success

                                // insert local article
                                LinqubatorArticle linqubatorArticle = LinqubatorArticle.builder()
                                        .cover(article.getThumbUrl()).title(article.getTitle())
                                        .content(article.getContent()).author(article.getAuthor())
                                        .summary(article.getDigest()).createTime(createTime)
                                        .updateTime(updateTime)

                                        // default value set
                                        .userId(adminUserId).createBy(adminUserId).status(1).type(2).delFlag(0).userType(0)
                                        .build();
                                int linqInsRes = linqubatorArticleMapper.insert(linqubatorArticle);
                                log.info("weixinArticle insert linqubatorArticle result:{}", linqInsRes > 0);

                                if (linqInsRes > 0) {
                                    log.debug("weixinArticle insert linqubatorArticle success. save ID: {}",
                                            linqubatorArticle.getId());
                                    // update id
                                    weixinPublicArticle.setLinqubatorArticleId(linqubatorArticle.getId());
                                    weixinPublicArticleMapper.update(weixinPublicArticle);
                                }

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
