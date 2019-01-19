package com.github.binarywang.demo.wx.open.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    protected WxOpenServiceDemo wxOpenService;

    public WxMpMaterialNewsBatchGetResult getBatchMaterial(String appId) {
        try {
            WxMpMaterialNewsBatchGetResult result = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId)
                    .getMaterialService().materialNewsBatchGet(0, 20);
            log.info("wechat materialService batchGetNews, appId: {}, result: {}", appId, result);

        } catch (WxErrorException e) {
            log.error("wechat materialService batchGetNews error, appId: {}", appId);
            log.error("", e);
        }
        return null;
    }
}
