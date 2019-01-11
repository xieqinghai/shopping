package com.neuedu.dao;

import com.neuedu.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbggenerated
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbggenerated
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbggenerated
     */
    List<Cart> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdAndProductId(@Param("userId") Integer userId,
                                        @Param("productId") Integer productId);

    /**
     * 查询用户的购物车信息
     * */
    List<Cart> selectCartByUserId(Integer userId);

    /**
     * 统计用户购物信息是否全选
     * @Return >0 说明未全选
     * (如果说返回值>0 说明未全选)
     * */
    int isCheckedAll(Integer userId);

    /**
     * 删除购物车的某些商品
     * */
    int deleteByUseridAndProductIds(@Param("userId") Integer userId,
                                    @Param("productIdList") List<Integer> productIdList);

    /**
     * 操作购物车商品是否选中
     * @Param userId
     * @Param productId
     * @Param check 1:选中  0:未选中
     * */
    int selectOrUnselectProduct(@Param("userId") Integer userId,
                                @Param("productId") Integer productId,
                                @Param("check") Integer check);

    /**
     *  统计用户购物车中产品数量
     * */
    int get_cart_product_count(Integer userId);



}