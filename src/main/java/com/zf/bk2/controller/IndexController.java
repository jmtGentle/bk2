package com.zf.bk2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @ModelAttribute
    public  void  init(Model model){
        System.out.println("init...公共数据-初始化");
        model.addAttribute("ts",System.currentTimeMillis());

    }

    @RequestMapping("/")
    public  String toIndex(){
        System.out.println("toIndex");
        return "index";
    }
}
