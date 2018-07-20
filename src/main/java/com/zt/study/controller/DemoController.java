package com.zt.study.controller;

import com.zt.study.pojo.DsxhOrder;
import com.zt.study.pojo.ReactUser;
import com.zt.study.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试系统框架
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    private int demo2;

    public int getDemo2() {
        return demo2;
    }

    public void setDemo2(int demo2) {
        this.demo2 = demo2;
    }

    @PostMapping("/demo")
    @ResponseBody
    public List<DsxhOrder> demo(@RequestBody DsxhOrder dsxhOrder){
        List<DsxhOrder> list = demoService.findDataForPage(dsxhOrder);
        return list;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody ReactUser reactUser){

        return demoService.saveUser(reactUser);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public int updateUser(@RequestBody ReactUser reactUser){
        return demoService.updateUser(reactUser);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public int deleteUser(@RequestBody ReactUser reactUser){

        return demoService.deleteUser(reactUser);
    }

    @PostMapping("/findData")
    @ResponseBody
    public List<ReactUser> findData(@RequestBody ReactUser reactUser){
        return demoService.query(reactUser);
    }

}
