package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Cart;

import java.util.List;

public interface ICartService {
    /**
     * 购物车中添加商品
     * */
    ServerResponse add(Integer userId,Integer productId,Integer count);

    /**
     * 购物车列表
     * */
    ServerResponse list(Integer userId);

    /**
     * 更新购物车某个商品数量
     * */
    ServerResponse update(Integer userId,Integer productId,Integer count);

    /**
     * 移除购物车某个产品
     * */
    ServerResponse delete_product(Integer userId,String productIds);

    /**
     * 购物车中选中某个商品
     * */
    ServerResponse select(Integer userId,Integer productId,Integer check);

    /**
     * 查询在购物车里的产品
     * */
    ServerResponse get_cart_product_count(Integer userId);





}
