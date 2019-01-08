package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.utils.DateUtils;
import com.neuedu.utils.PropertiesUtils;
import com.neuedu.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse saveOrUpdate(Product product) {
        //step1:参数非空校验
        if(product == null) {
            return ServerResponse.createServerResponseByError("参数为空");
        }
        //step2:设置商品的主图 sub_imges -->1.jpg,2.jpg,3.png
        String subImages = product.getSubImages();
        if(subImages != null && !subImages.equals("")) {
            String[] subImageArr = subImages.split(",");
            if(subImageArr.length > 0) {
                //商品的主图
                product.setMainImage(subImageArr[0]);
            }
        }
        //step3:商品save or update  并返回结果
        if(product.getId() == null) {
            //添加
            int result = productMapper.insert(product);
            if(result > 0) {
                return ServerResponse.createServerResponseBySuccess();
            } else {
                return ServerResponse.createServerResponseByError("添加失败");
            }
        } else {
            //更新
            int result = productMapper.updateByPrimaryKey(product);
            if(result > 0) {
                return ServerResponse.createServerResponseBySuccess();
            } else {
                return ServerResponse.createServerResponseByError("更新失败");
            }
        }
    }

    @Override
    public ServerResponse set_sale_status(Integer productId, Integer status) {
        //step1:参数非空校验
        if(productId == null) {
            return ServerResponse.createServerResponseByError("商品id参数不能为空");
        }
        if(status == null) {
            return ServerResponse.createServerResponseByError("商品状态参数不能为空");
        }
        //step2:更新商品状态
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int result = productMapper.updateProductKeySelective(product);
        //step3:返回结果
        if(result > 0) {
            return ServerResponse.createServerResponseBySuccess();
        } else {
            return ServerResponse.createServerResponseByError("更新失败");
        }
    }

    @Override
    public ServerResponse detail(Integer productId) {
        //step1: 参数非空校验
        if(productId == null) {
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        //step2: 根据商品id查询商品
        Product product = productMapper.selectByPrimaryKey(productId);
        //非空判断
        if(product == null) {
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        //step3:product-->productDetailVO
        ProductDetailVO productDetailVO = assembleProductDetailVO(product);
        //step4:返回结果
        return ServerResponse.createServerResponseBySucess(null,productDetailVO);
    }

    private ProductDetailVO assembleProductDetailVO(Product product) {

        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setImageHost(PropertiesUtils.readByKey("imageHost"));
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setId(product.getId());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category != null) {
            productDetailVO.setParentCategoryId(category.getParentId());
        } else {
            //默认根节点
            productDetailVO.setParentCategoryId(0);
        }
       return productDetailVO;
    }


}
