package com.charlie.furn.controller;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.bean.Msg;
import com.charlie.furn.service.FurnService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 4. @Validated：对数据进行校验
     */
    @PostMapping("/save")
    @ResponseBody
    public Msg save(@Validated @RequestBody Furn furn, Errors errors) {
        Map<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        if (map.isEmpty()) {    // 没有错误，即后端校验通过
            furnService.save(furn);
            // 返回成功信息
            return Msg.success();
        } else {
            // 校验失败，把错误信息封装到errorMsg并返回
            return Msg.fail().add("errorMsg", map);
        }
    }

    // 请求显示所有家具信息
    @RequestMapping("/furns")
    @ResponseBody
    public Msg listFurns() {
        List<Furn> furnList = furnService.findAll();
        Msg msg = Msg.success();
        // 把家具信息封装到msg对象
        msg.add("furnList", furnList);
        return msg;
    }

    // 处理修改请求
    // 1. 前端发送过来json格式的furn数据，需要通过注解 @requestBody修饰参数
    @PutMapping("/update")
    @ResponseBody
    public Msg updateFurn(@RequestBody Furn furn) {
        furnService.update(furn);
        return Msg.success();
    }

    // 处理删除请求
    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Msg del(@PathVariable Integer id) {
        furnService.del(id);
        return Msg.success();
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Msg findById(@PathVariable Integer id) {
        Furn furn = furnService.findFurnById(id);
        return Msg.success().add("furn", furn);
    }

    /**
     * 分页请求接口
     *
     * @param pageNum：要显示第几页，默认为1
     * @param pageSize：每一页要显示几条记录，默认为5
     */
    @ResponseBody
    @RequestMapping("/furnsByPage")
    public Msg listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 1. 调用findAll完成查询，底层会进行物理分页，而不是逻辑分页
        // 2. 会根据分页参数来计算 limit ?, ? 在发出SQL语句时，会携带 limit
        // 3. select id, name, maker, price, sales, stock, img_path from furn LIMIT ?
        List<Furn> furnList = furnService.findAll();
        // 将分页查询的结果，封装到PageInfo中
        // PageInfo对象包含了分页的各个信息，如当前页面pageNum，共有多少条记录
        PageInfo pageInfo = new PageInfo(furnList, pageSize);
        // 将pageInfo封装到Msg对象，返回
        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 根据家具名进行分页查询-条件
     * @param pageNum：第几页
     * @param pageSize：每页多少项
     * @param search：查询条件(家具名)
     */
    @ResponseBody
    @RequestMapping("/furnsByConditionPage")
    public Msg listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        @RequestParam(defaultValue = "") String search) {
        PageHelper.startPage(pageNum, pageSize);
        // 这里只需要改变调用的方法即可
        List<Furn> furnList = furnService.findByCondition(search);
        PageInfo pageInfo = new PageInfo(furnList, pageSize);
        return Msg.success().add("pageInfo", pageInfo);
    }
}
