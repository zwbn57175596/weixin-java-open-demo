package com.github.binarywang.demo.wx.open.controller;


import com.github.binarywang.demo.wx.open.service.MaterialService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/wxopen/index")
public class IndexController {

    @Resource
    private MaterialService materialService;

    @RequestMapping("/authority")
    public String hello(){
        System.out.println("Hello!!!");
        return "AuthorityThired";
    }

    @RequestMapping("/batchGetNews")
    @ResponseBody
    public WxMpMaterialNewsBatchGetResult batchGetNew(@RequestParam("appId") String appId) {
        return materialService.getBatchMaterial(appId);
    }

}
