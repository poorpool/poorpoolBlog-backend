package net.yxchen.poorpoolblog.test;

import net.yxchen.poorpoolblog.PoorpoolblogApplication;
import net.yxchen.poorpoolblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)//指定用谁来单元测试
@SpringBootTest(classes = {PoorpoolblogApplication.class})
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void login() {
        System.out.println(userService.login("poorpool", "1947e096709324e5977c9315aa9e9853b83a8ed22d383c18e15f7e5810d8ee0eec294ee8c4463d52683359d350b7450e7937"));
    }
}