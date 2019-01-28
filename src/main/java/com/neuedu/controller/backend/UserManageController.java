package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 后台用户控制器类
 */
@RestController
@RequestMapping(value = "/manage/user")
public class UserManageController {

    /* 将 IUserService 注入进来, 通过 @Autowired */
    @Autowired
    IUserService userService;

    /**
     * 管理员登录
     */
    @RequestMapping(value = "/login.do")
    public ServerResponse login(HttpSession session, String username, String password) {
        ServerResponse serverResponse = userService.login(username, password);
        if (serverResponse.isSuccess()) { //登录成功
            UserInfo userInfo = (UserInfo) serverResponse.getData();
            if (userInfo.getRole() == Const.RoleEnum.ROLE_CUSTOMER.getCode()) {
                return ServerResponse.createServerResponseByError("无权限登录");
            }
            session.setAttribute(Const.CURRENTUSER, userInfo);
        }
        return serverResponse;
    }
    @RequestMapping(value = "/login/{username}/{password}")
    public ServerResponse loginRestful(HttpSession session,
                                       @PathVariable("username") String username,
                                       @PathVariable("password") String password) {
        ServerResponse serverResponse = userService.login(username, password);
        if (serverResponse.isSuccess()) { //登录成功
            UserInfo userInfo = (UserInfo) serverResponse.getData();
            if (userInfo.getRole() == Const.RoleEnum.ROLE_CUSTOMER.getCode()) {
                return ServerResponse.createServerResponseByError("无权限登录");
            }
            session.setAttribute(Const.CURRENTUSER, userInfo);
        }
        return serverResponse;
    }


}
