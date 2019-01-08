package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@RestController
@RequestMapping(value="/manage/product")
public class ProductManageController {

    @Autowired
    IProductService productService;

    /**
     * 新增OR更新产品
     * */
    @RequestMapping(value="/save.do")
    public ServerResponse saveOrUpdate(HttpSession session, Product product) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return productService.saveOrUpdate(product);
    }

    /**
     * 产品的上下架
     * */
    @RequestMapping(value="/set_sale_status.do")
    public ServerResponse set_sale_status(HttpSession session, Integer productId,Integer status) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return productService.set_sale_status(productId,status);
    }

    /**
     * 查看商品详情
     * */
    @RequestMapping(value="/detail.do")
    public ServerResponse detail(HttpSession session, Integer productId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(),Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return productService.detail(productId);
    }




}
