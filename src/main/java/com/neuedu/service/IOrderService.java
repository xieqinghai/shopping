package com.neuedu.service;

import com.neuedu.common.ServerResponse;

import java.util.Map;

public interface IOrderService {

    /**
     * 创建订单
     * */
    ServerResponse createOrder(Integer userId,Integer shippingId);

    /**
     * 取消订单
     * */
    ServerResponse cancle(Integer userId,Long orderNo);

    /**
     * 获取购物车中订单明细
     * */
    ServerResponse get_order_cart_product(Integer userId);

    /**
     * 订单列表
     * 传userId认为是用户调用
     * 不穿userId默认为管理员调用
     * */
    ServerResponse list(Integer userId,Integer pageNum,Integer pageSize);

    /**
     *订单详情detail
     * */
    ServerResponse detail(Long orderNo);

    /**
     * 支付接口
     * */
    ServerResponse pay(Integer userIf,Long orderNo);

    /**
     * 支付宝回调接口
     * */
    ServerResponse alipay_callback(Map<String,String> map);

    /**
     * 查询订单的支付状态
     * */
    ServerResponse query_order_pay_status(Long orderNo);


}
