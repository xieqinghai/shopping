package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@RestController
@RequestMapping(value = "/manage/product")
public class ProductManageController {

    @Autowired
    IProductService productService;

    /**
     * 新增OR更新产品 权限判定定例子,其他的都挪到了拦截器里
     */
    @RequestMapping(value = "/save.do")
    public ServerResponse saveOrUpdate(HttpSession session, Product product) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NEAD_LOGIN.getCode(), Const.ResponseCodeEnum.NEAD_LOGIN.getDesc());
        }
        //判断权限
        if (userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
            return ServerResponse.createServerResponseByError(Const.ResponseCodeEnum.NO_PRIVILEGE.getCode(), Const.ResponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return productService.saveOrUpdate(product);
    }

    /**
     * 产品的上下架
     */
    @RequestMapping(value = "/set_sale_status.do")
    public ServerResponse set_sale_status(Integer productId, Integer status) {
        return productService.set_sale_status(productId, status);
    }
    // restful
    @RequestMapping(value = "/set_sale_status/{productId}/{status}")
    public ServerResponse set_sale_statusRestful(@PathVariable("productId") Integer productId,@PathVariable("status") Integer status) {
        return productService.set_sale_status(productId, status);
    }

    /**
     * 查看商品详情
     *
     */
    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(HttpSession session, Integer productId) {
        return productService.detail(productId);
    }
    //restful
    @RequestMapping(value = "/detail/{productId}")
    public ServerResponse detailRestful(@PathVariable("productId") Integer productId) {
        return productService.detail(productId);
    }
    /**
     * 查看商品列表
     */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return productService.list(pageNum, pageSize);
    }
    //restful
    @RequestMapping(value = "/list/pageNum/{pageNum}/{pageSize}")
    public ServerResponse listRestful(@PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {
        return productService.list(pageNum, pageSize);
    }
    @RequestMapping(value = "/list/pageNum/{pageNum}")
    public ServerResponse listPageNum(@PathVariable("pageNum") Integer pageNum) {
        return productService.list(pageNum, null);
    }
    @RequestMapping(value = "/list/pageSize/{pageSize}")
    public ServerResponse listPageSize(@PathVariable("pageSize") Integer pageSize) {
        return productService.list(null, pageSize);
    }

    /**
     * 产品搜索
     */
    @RequestMapping(value = "/search.do")
    public ServerResponse list(@RequestParam(value = "productId", required = false) Integer productId,
                               @RequestParam(value = "productName", required = false) String productName,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        return productService.search(productId, productName, pageNum, pageSize);
    }
    //restful
    //这个接口没必要有****************************
    @RequestMapping(value = "/search/{productId}/{productName}/{pageNum}/{pageSize}")
    public ServerResponse listRestful(@PathVariable("productId") Integer productId,
                                      @PathVariable("productName") String productName,
                                      @PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {

        return productService.search(productId, productName, pageNum, pageSize);
    }
    @RequestMapping(value = "/search/productId/{productId}/{pageNum}/{pageSize}")
    public ServerResponse listProductId(@PathVariable("productId") Integer productId,
                                          @PathVariable("pageNum") Integer pageNum,
                                          @PathVariable("pageSize") Integer pageSize) {

        return productService.search(productId, null, pageNum, pageSize);
    }
    @RequestMapping(value = "/search/productName/{productName}/{pageNum}/{pageSize}")
    public ServerResponse listProductName(@PathVariable("productName") String productName,
                                          @PathVariable("pageNum") Integer pageNum,
                                          @PathVariable("pageSize") Integer pageSize) {

        return productService.search(null, productName, pageNum, pageSize);
    }





}
