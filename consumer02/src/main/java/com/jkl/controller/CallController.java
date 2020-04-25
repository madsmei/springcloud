package com.jkl.controller;

import com.jkl.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ribbon")
public class CallController {
    @Autowired
    private CallService callService;

    @GetMapping("/server-hi/{name}")
    public String hello(@PathVariable("name") String name) {
        return callService.sayHello(name);
    }
}
