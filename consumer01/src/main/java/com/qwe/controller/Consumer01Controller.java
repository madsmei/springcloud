package com.qwe.controller;

import com.qwe.feign.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer01Controller {

    public static boolean canVisiDB = true;

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi() {
        return schedualServiceHi.sayHiFromClientOne();
    }

    /******
     *
     * 检测 数据库是否是可用状态
     * @return
     */
    @RequestMapping(value = "/canVisiDB", method = RequestMethod.GET)
    public String canVisiDB() {
        //调用服务中的检测数据库的可用状态逻辑代码
        return "";
    }
}
