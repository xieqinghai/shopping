package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface IProductService {

    /**
     * 后台-新增OR更新产品
     * */
    ServerResponse saveOrUpdate(Product product);

    /**
     * 后台-商品上下架
     * @Param productId 商品id
     * @Param status 商品状态
     * */
    ServerResponse set_sale_status(Integer productId,Integer status);

    /**
     * 后台-商品详情
     * */
    ServerResponse detail(Integer productId);

    /**
     * 后台-商品列表,分页
     * */
    ServerResponse list(Integer pageNum,Integer pageSize);

    /**
     * 后台-产品搜索
     * */
    ServerResponse search(Integer productId,String productName,Integer pageNum,Integer pageSize);

    /**
     * 图片上传
     * */
    ServerResponse upload(MultipartFile file,String path);

    /**
     * 前台-商品详情
     * */
    ServerResponse detail_portal(Integer productId);

    /**
     * 前台-商品搜索
     * @Param categoryId
     * @Param keyword
     * @Param pageNum
     * @Parma pageSize
     * @Param orderBy   排序字段
     * */
    ServerResponse list_portal(Integer categoryId, String keyword, Integer PageNum, Integer pageSize, String orderBy );



}
