package com.kson.controllers;

import com.kson.core.annotations.RequestMapping;
import com.kson.core.enums.RequestMethodEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController {

    @RequestMapping(path = {"/test/index"}, method = RequestMethodEnum.GET)
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("hello");
        return "hello";
    }
}
