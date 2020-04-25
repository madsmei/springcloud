package com.abc.controller;

import com.abc.entity.Yyuser;
import com.abc.servive.YYUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/hello")
public class Service01Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private YYUserService yyUserService;

    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String index(@PathVariable(value="id") Long id){
        List<Yyuser> list = yyUserService.selectAll();

        Yyuser yyuser = yyUserService.selectById(id);


        return "Hello World service01 "+list.size()+" name:"+yyuser.getName();
    }

    /******
     * 测试 post请求对象
     * @param yyuser
     * @return
     */
    @PostMapping("/testPost")
    public Yyuser testPostUser(@RequestBody  Yyuser yyuser){
        System.out.println(yyuser.toString());
        return yyuser;
    }
}
