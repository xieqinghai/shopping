package com.neuedu.controller;

import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping("/user/{userInfo}")
    public UserInfo getById(@PathVariable int userInfo) {

        return userInfoMapper.selectByPrimaryKey(userInfo);
    }


}
