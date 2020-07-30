package net.yxchen.poorpoolblog.service;

import net.yxchen.poorpoolblog.bean.User;
import net.yxchen.poorpoolblog.bean.UserExample;
import net.yxchen.poorpoolblog.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String userName, String userPassword) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andUserPasswordEqualTo(userPassword);
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? null : users.get(0);
    }

    public User getUserWithoutPassword(long id) {
        User user =  userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        user.setUserPassword("");
        return user;
    }

    public User getUserByUserId(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
