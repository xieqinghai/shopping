package com.neuedu.dao;

import com.neuedu.pojo.Product;
import com.neuedu.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbggenerated
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbggenerated
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbggenerated
     */
    List<Product> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_product
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Product record);

    /**
     * 更新商品
     * */
    int updateProductKeySelective(Product product);

    /**
     * 按照productId或productName查询
     * */
    List<Product> findProductByProductIdAndProductName(@Param(value="productId") Integer productId,
                                                       @Param(value="productName") String productName);

    /**
     * 前台接口-搜索商品
     * */
    List<Product> searchProduct(@Param("integerSet") Set<Integer> integerSet,
                                @Param("keyword") String keyword,
                                @Param("orderBy") String orderBy);



}