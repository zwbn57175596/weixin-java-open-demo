package com.github.binarywang.demo.wx.open.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wxopen/index")
public class IndexController {

    @RequestMapping("/authority")
    public String hello(){
        System.out.println("Hello!!!");
        return "AuthorityThired";
    }

}
