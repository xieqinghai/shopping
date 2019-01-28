package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/shipping")
public class AddressController {

    @Autowired
    IAddressService addressService;

    /**
     * 添加地址
     */
    @RequestMapping(value = "/add.do")
    public ServerResponse add(HttpSession session, Shipping shipping) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }

        return addressService.add(userInfo.getId(), shipping);
    }

    /**
     * 删除地址
     */
    @RequestMapping(value = "/del.do")
    public ServerResponse del(HttpSession session, Integer shippingId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }

        return addressService.del(userInfo.getId(), shippingId);
    }
    @RequestMapping(value = "/del/{shippingId}")
    public ServerResponse delRestful(HttpSession session,@PathVariable("shippingId") Integer shippingId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return addressService.del(userInfo.getId(), shippingId);
    }

    /**
     * 登录状态更新地址
     */
    @RequestMapping(value = "/update.do")
    public ServerResponse update(HttpSession session, Shipping shipping) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        shipping.setUserId(userInfo.getId());
        return addressService.update(shipping);
    }

    /**
     * 选中查看具体的地址
     */
    @RequestMapping(value = "/select.do")
    public ServerResponse select(HttpSession session, Integer shippingId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return addressService.select(shippingId);
    }
    @RequestMapping(value = "/select/{shippingId}")
    public ServerResponse selectRestful(HttpSession session,@PathVariable("shippingId") Integer shippingId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return addressService.select(shippingId);
    }

    /**
     * 地址列表
     * 分页查询
     */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return addressService.list(pageNum, pageSize);
    }
    @RequestMapping(value = "/list/{pageNum}/{pageSize}")
    public ServerResponse listRestful(HttpSession session,
                                      @PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return addressService.list(pageNum, pageSize);
    }


}
