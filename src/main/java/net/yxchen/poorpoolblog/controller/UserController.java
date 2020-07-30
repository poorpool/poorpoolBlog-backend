package net.yxchen.poorpoolblog.controller;

import net.yxchen.poorpoolblog.bean.ReturnMessage;
import net.yxchen.poorpoolblog.bean.User;
import net.yxchen.poorpoolblog.service.TokenService;
import net.yxchen.poorpoolblog.service.UserService;
import org.apache.catalina.util.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/user/login")
    public ReturnMessage login(@RequestBody User user) {
        User checkUser = userService.login(user.getUserName(), user.getUserPassword());
        if (checkUser == null) {
            return ReturnMessage.fail().setMessage("登录失败，用户名和密码不匹配或不存在");
        } else {
            return ReturnMessage.success().setParam("token", tokenService.getToken(checkUser));
        }
    }

    @GetMapping("/user/name/{userId}")
    public ReturnMessage getUserNameByUserId(@PathVariable("userId") Long userId) {
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            return ReturnMessage.fail().setMessage("用户不存在");
        } else {
            return ReturnMessage.success().setMessage(user.getUserName());
        }
    }
}
