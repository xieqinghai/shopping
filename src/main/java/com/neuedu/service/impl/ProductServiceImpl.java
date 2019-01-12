package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import com.neuedu.utils.DateUtils;
import com.neuedu.utils.FTPUtil;
import com.neuedu.utils.PropertiesUtils;
import com.neuedu.vo.ProductDetailVO;
import com.neuedu.vo.ProductListVO;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ICategoryService categoryService;

    @Override
    public ServerResponse saveOrUpdate(Product product) {
        //step1:参数非空校验  需不需要逐项校验????????
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

    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {

        //分页插件 原理:spring的aop
        PageHelper.startPage(pageNum,pageSize);

        //step1:查询商品数据 select * from product limit (pageNum-1)*pageSize,pageSize
        List<Product> productList = productMapper.selectAll();
        List<ProductListVO> productListVOList = Lists.newArrayList();
        if(productList != null && productList.size()>0) {
            for(Product product:productList) {
                ProductListVO productListVO = assembleProductListVO(product);
                productListVOList.add(productListVO);
            }
        }
        //step2:插件提供的分页对象: PageInfo
        PageInfo pageInfo = new PageInfo(productListVOList);
        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }

    private ProductListVO assembleProductListVO(Product product) {
        ProductListVO productListVO = new ProductListVO();
        productListVO.setId(product.getId());
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setName(product.getName());
        productListVO.setPrice(product.getPrice());
        productListVO.setStatus(product.getStatus());
        productListVO.setSubtitle(product.getSubtitle());

        return productListVO;
    }

    @Override
    public ServerResponse search(Integer productId, String productName,
                                 Integer pageNum, Integer pageSize) {

        //select * from product where productId ? and productName ? like %name%
        PageHelper.startPage(pageNum,pageSize);

        if(productName != null&&!productName.equals("")) {
            productName = "%"+productName+"%";
        }

        List<Product> productList = productMapper.findProductByProductIdAndProductName(productId, productName);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        if(productList != null && productList.size()>0) {
            for(Product product:productList) {
                ProductListVO productListVO = assembleProductListVO(product);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo = new PageInfo(productListVOList);
        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }

    @Override
    public ServerResponse upload(MultipartFile file, String path) {
        if(file == null) {
            return ServerResponse.createServerResponseByError();
        }
        // file 总不为空 以下为上边的补充 非空判断
        if(file.getOriginalFilename().equals("")) {
            return ServerResponse.createServerResponseByError();
        }
        //step1:获取图片名字
        String originalFilename = file.getOriginalFilename();
        //获取图片的扩展名
        String exName = originalFilename.substring(originalFilename.lastIndexOf(".")); // .jpg
        //为图片生成新的唯一的名字
        String newFilename = UUID.randomUUID().toString()+exName;

        File pathFile = new File(path);
        if(!pathFile.exists()) {
            pathFile.setWritable(true);
            pathFile.mkdirs();          //如果不存在目录则创建一个
        }

        File file1 = new File(path,newFilename);
        try {
            file.transferTo(file1);
           //后期上传到图片服务器
            FTPUtil.uploadFile(Lists.newArrayList(file1));
            //....
            Map<String,String> map = Maps.newHashMap();
            map.put("uri",newFilename);
            map.put("utl",PropertiesUtils.readByKey("imageHost")+"/"+newFilename);

            //删除应用服务器上的图片
            file1.delete();

            return ServerResponse.createServerResponseBySucess(null,map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 前台接口-商品详细
     * */
    @Override
    public ServerResponse detail_portal(Integer productId) {
        //step1:参数非空校验
        if(productId == null) {
            return ServerResponse.createServerResponseByError("商品id不能为空");
        }
        //step2: 查询product
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null) {
            return ServerResponse.createServerResponseByError("商品不存在");
        }
        //step3:校验商品状态
        if(product.getStatus() != Const.ProductStatusEnum.PRODUCT_ONLINE.getCode()) {
            return ServerResponse.createServerResponseByError("商品已下架或删除");
        }
        //step4:获取productDetailVO
        ProductDetailVO productDetailVO = assembleProductDetailVO(product);
        //step5:返回结果
        return ServerResponse.createServerResponseBySucess(null,productDetailVO);
    }

    @Override
    public ServerResponse list_portal(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        //step1: 参数非空校验 categoryId 和 keyword不能同时为空
        if(categoryId == null&&(keyword == null||keyword.equals(""))) {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:categoryId
        Set<Integer> integerSet = Sets.newHashSet();
        if(categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category==null && (keyword==null || keyword.equals(""))) {
                //说明没有商品数据
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVO> productListVOList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVOList);
                return ServerResponse.createServerResponseBySucess(null,pageInfo);
            }

            ServerResponse serverResponse = categoryService.get_deep_category(categoryId);
            if(serverResponse.isSuccess()) {
                integerSet = (Set<Integer>) serverResponse.getData();
            }
        }
        //step3:keyword
        if(keyword!=null && !keyword.equals("")) {
            keyword = "%"+keyword+"%";
        }

        if(orderBy.equals("")) {
            PageHelper.startPage(pageNum,pageSize);
        } else {
            String[] orderByArr = orderBy.split("_");
            if(orderByArr.length>1) {
                PageHelper.startPage(pageNum,pageSize,orderByArr[0]+" "+orderByArr[1]);
            } else {
                PageHelper.startPage(pageNum,pageSize);
            }
        }
        //step4: List<Product> --> List<ProductListVO>
        List<Product> productList = productMapper.searchProduct(integerSet, keyword, orderBy);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        if(productList != null&&productList.size()>0) {
            for(Product product:productList) {
                ProductListVO productListVO = assembleProductListVO(product);
                productListVOList.add(productListVO);
            }
        }
        //step5:分页
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(productListVOList);
        //step6:返回
        return ServerResponse.createServerResponseBySucess(null,pageInfo);
    }


}
