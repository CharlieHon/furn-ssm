package com.charlie.furn.controller;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.bean.Msg;
import com.charlie.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class FurnController {

    // 注入/配置FurnService
    @Resource
    private FurnService furnService;

    /**
     * 响应客户端的添加请求
     * 1. 响应客户端的添加请求
     * 2. @RequestBody：使用SpringMVC的注解将客户端提交的json数据封装成Furn对象
     * 3. @ResponseBody：返回json格式数据，底层是按照http协议进行协商的
     */
    @PostMapping("/save")
    @ResponseBody
    public Msg save(@RequestBody Furn furn) {
        furnService.save(furn);
        // 返回成功信息
        Msg success = Msg.success();
        return success;
    }
}
