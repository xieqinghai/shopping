package com.neuedu.controller.portal;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    IProductService productService;

    /**
     * 商品详情
     */
    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(Integer productId) {

        return productService.detail_portal(productId);
    }
    @RequestMapping(value = "/detail.do",method = RequestMethod.POST)
    public ServerResponse detail1(Integer productId) {

        return productService.detail_portal(productId);
    }
    /**
     *restful
     * http://localhost:8080/product/detail.do?productId=xxx
     * http://localhost:8080/product/detail.do/productId/xxx
     * */
    @RequestMapping(value="/detail/productId/{productId}")
    public ServerResponse detailRestful(@PathVariable("productId") Integer productId) {
        return productService.detail(productId);
    }

    /**
     * 前台-搜索商品并排序
     */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(@RequestParam(required = false) Integer categoryId,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "") String orderBy) {

        return productService.list_portal(categoryId, keyword, pageNum, pageSize, orderBy);
    }
    /**
     * restful
     * http://localhost:8080/product/list/{category}/{keyword}/{pageNum}/{pageSize}/{orderBy}
     * 解决参数 category和keyword没必要同时出现问题
     * 改进: 解决出现模棱两可方法问题
     * 自定义资源站位,将模棱两可的方法进行隔离区分
     * */
    @RequestMapping(value = "/list/categoryId/{category}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse listRestfulCatecoryId(@PathVariable("category") Integer categoryId,
                                      @PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize,
                                      @PathVariable("orderBy") String orderBy) {

        return productService.list_portal(categoryId, null, pageNum, pageSize, orderBy);
    }
    @RequestMapping(value = "/list/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse listRestfulKeyword(@PathVariable("keyword") String keyword,
                                      @PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize,
                                      @PathVariable("orderBy") String orderBy) {

        return productService.list_portal(null, keyword, pageNum, pageSize, orderBy);
    }

}
