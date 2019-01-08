package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/manage/category")
public class CategoryManageController {

    @Autowired
    ICategoryService categoryService;

    /**
     * 获取类别子节点(平级)
     * */
    @RequestMapping(value="/get_category.do")
    public ServerResponse get_category(HttpSession session, Integer categoryId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return categoryService.get_category(categoryId);
    }

    /**
     * 增加节点
     * categoryId是parent_id而类别自己的id是自动生成的
     * */
    @RequestMapping(value="/add_category.do")
    public ServerResponse add_category(HttpSession session,
                                       @RequestParam(required = false,defaultValue = "0") Integer categoryId,
                                       String categoryName) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return categoryService.add_category(categoryId,categoryName);
    }

    /**
     * 修改节点
     * */
    @RequestMapping(value="/set_category_name.do")
    public ServerResponse set_category_name(HttpSession session,
                                            Integer categoryId,
                                            String categoryName) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return categoryService.set_category_name(categoryId,categoryName);
    }

    /**
     * 获取当前分类id及递归子节点categoryId
     * */
    @RequestMapping(value="/get_deep_category.do")
    public ServerResponse get_deep_category(HttpSession session,
                                            Integer categoryId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return categoryService.get_deep_category(categoryId);
    }






}
