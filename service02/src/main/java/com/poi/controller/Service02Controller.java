package com.poi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class Service02Controller {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        return "Hello World service02";
    }
}
