package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;

import javax.servlet.http.HttpSession;

public interface IProductService {

    /**
     * 新增OR更新产品
     * */
    ServerResponse saveOrUpdate(Product product);

    /**
     * 商品上下架
     * */
    ServerResponse set_sale_status(Integer productId,Integer status);

    /**
     * 商品详情
     * */
    ServerResponse detail(Integer productId);



}
