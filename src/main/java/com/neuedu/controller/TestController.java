package com.neuedu.controller;

import com.neuedu.common.ServerResponse;
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
    public ServerResponse<UserInfo> getById(@PathVariable int userInfo) {

        UserInfo u = userInfoMapper.selectByPrimaryKey(userInfo);

        if(u != null) {
            return ServerResponse.createServerResponseBySucess(null,u);
        } else {
            return ServerResponse.createServerResponseByError("fail");
        }
    }


}
