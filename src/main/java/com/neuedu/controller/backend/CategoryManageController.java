package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/category")
public class CategoryManageController {

    @Autowired
    ICategoryService categoryService;

    /**
     * 获取类别子节点(平级)
     */
    @RequestMapping(value = "/get_category.do")
    public ServerResponse get_category( Integer categoryId) {
        int i = 5/0;    //错误信息,测试全局异常处理用
        return categoryService.get_category(categoryId);
    }
    @RequestMapping(value = "/get_category/{categoryId}")
    public ServerResponse get_categoryRestful(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.get_category(categoryId);
    }

    /**
     * 增加节点
     * categoryId是parent_id而类别自己的id是自动生成的
     */
    @RequestMapping(value = "/add_category.do")
    public ServerResponse add_category(@RequestParam(required = false, defaultValue = "0") Integer categoryId,
                                       String categoryName) {
        return categoryService.add_category(categoryId, categoryName);
    }
    @RequestMapping(value = "/add_category/categoryId/{categoryId}/{categoryName}")
    public ServerResponse add_categoryCategoryId(@PathVariable("categoryId") Integer categoryId,
                                                 @PathVariable("categoryName") String categoryName) {
        return categoryService.add_category(categoryId, categoryName);
    }
    @RequestMapping(value = "/add_category/{categoryName}")
    public ServerResponse add_category(@PathVariable("categoryName") String categoryName) {
        return categoryService.add_category(null, categoryName);
    }

    /**
     * 修改节点
     */
    @RequestMapping(value = "/set_category_name.do")
    public ServerResponse set_category_name(Integer categoryId,
                                            String categoryName) {
        return categoryService.set_category_name(categoryId, categoryName);
    }
    @RequestMapping(value = "/set_category_name/{categoryId}/{categoryName}")
    public ServerResponse set_category_nameRestful(@PathVariable("categoryId") Integer categoryId,
                                                    @PathVariable("categoryName") String categoryName) {
        return categoryService.set_category_name(categoryId, categoryName);
    }

    /**
     * 获取当前分类id及递归子节点categoryId
     */
    @RequestMapping(value = "/get_deep_category.do")
    public ServerResponse get_deep_category(Integer categoryId) {
        return categoryService.get_deep_category(categoryId);
    }
    @RequestMapping(value = "/get_deep_category/{categoryId}")
    public ServerResponse get_deep_categoryRestful(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.get_deep_category(categoryId);
    }


}
