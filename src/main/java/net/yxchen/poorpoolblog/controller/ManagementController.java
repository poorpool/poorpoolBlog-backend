package net.yxchen.poorpoolblog.controller;

import net.yxchen.poorpoolblog.annotation.UserLoginToken;
import net.yxchen.poorpoolblog.bean.ReturnMessage;
import net.yxchen.poorpoolblog.bean.User;
import net.yxchen.poorpoolblog.service.TokenService;
import net.yxchen.poorpoolblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @UserLoginToken
    @GetMapping("/user/{id}")
    public ReturnMessage getUserWithoutPassword(@PathVariable String id) {
        User user = userService.getUserWithoutPassword(Long.parseLong(id));
        if (user == null) {
            return ReturnMessage.fail().setMessage("用户不存在");
        } else {
            return ReturnMessage.success().setParam("user", user);
        }
    }
}
