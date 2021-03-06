package com.github.binarywang.demo.wx.open.controller;

import com.github.binarywang.demo.wx.open.service.MaterialService;
import com.github.binarywang.demo.wx.open.service.WxOpenServiceDemo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Controller
@RequestMapping("/wxopen/api")
public class WechatApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WxOpenServiceDemo wxOpenServiceDemo;
    @Autowired
    private MaterialService materialService;

    @GetMapping("/auth/goto_auth_url_show")
    @ResponseBody
    public String gotoPreAuthUrlShow(){
        return "<a href='goto_auth_url'>go</a>";
    }
    @GetMapping("/auth/goto_auth_url")
    public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response){
        String host = request.getHeader("host");
        String url = "http://"+host+"/wxopen/api/auth/jump";
        try {
            url = wxOpenServiceDemo.getWxOpenComponentService().getPreAuthUrl(url);
            // 添加来源，解决302跳转来源丢失的问题
            response.addHeader("Referer", "http://"+host);
            response.sendRedirect(url);
        } catch (WxErrorException | IOException e) {
            logger.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/auth/jump")
    @ResponseBody
    public WxOpenQueryAuthResult jump(@RequestParam("auth_code") String authorizationCode){
        try {
            WxOpenQueryAuthResult queryAuthResult = wxOpenServiceDemo.getWxOpenComponentService().getQueryAuth(authorizationCode);
            logger.info("getQueryAuth", queryAuthResult);
            return queryAuthResult;
        } catch (WxErrorException e) {
            logger.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get_authorizer_info")
    @ResponseBody
    public WxOpenAuthorizerInfoResult getAuthorizerInfo(@RequestParam String appId){
        try {
            return wxOpenServiceDemo.getWxOpenComponentService().getAuthorizerInfo(appId);
        } catch (WxErrorException e) {
            logger.error("getAuthorizerInfo", e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/batchGetNews")
    @ResponseBody
    public WxMpMaterialNewsBatchGetResult batchGetNew(
            @RequestParam("appId") String appId, @RequestParam("adminUserId") Integer adminUserId) {
        return materialService.getBatchMaterial(appId, adminUserId);
    }
}
