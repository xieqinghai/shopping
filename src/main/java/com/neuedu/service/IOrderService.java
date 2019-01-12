package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface IOrderService {

    /**
     * 创建订单
     * */
    ServerResponse createOrder(Integer userId,Integer shippingId);

}
