package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * 创建订单
     * */
    @RequestMapping(value="/create.do")
    public ServerResponse createOrder(HttpSession session,Integer shippingId) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null) {
            return ServerResponse.createServerResponseByError("需要登录");
        }
        return orderService.createOrder(userInfo.getId(),shippingId);
    }


}
