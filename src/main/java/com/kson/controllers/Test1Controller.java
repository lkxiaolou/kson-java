package com.kson.controllers;

import com.kson.core.annotations.Get;
import com.kson.core.annotations.Post;
import com.kson.core.annotations.RenderType;
import com.kson.core.annotations.RequestMapping;
import com.kson.core.enums.RenderEnum;
import com.kson.core.enums.RequestMethodEnum;
import com.kson.model.User;

public class Test1Controller {

    @RequestMapping(path = {"/test1/index"}, method = RequestMethodEnum.GET)
    @RenderType(RenderEnum.JSON_RENDER)
    public String index(@Get("uid") String uid,
                        @Get("page") Integer page,
                        @Get("pageSize") Integer pageSize,
                        @Post User user) {
        return "uid=" + uid + ";page=" + page + ";pageSize=" + pageSize + ";user=" + user;
    }
}
