package com.kson.controllers.user;

import com.kson.core.annotations.RequestMapping;
import com.kson.core.enums.RequestMethodEnum;
import com.kson.model.User;

public class userController {

    @RequestMapping(path = {"/user/index"}, method = RequestMethodEnum.GET)
    public User index() {
        User user = new User();
        user.setId(1);
        user.setUserName("lkxiaolou");
        return user;
    }
}
