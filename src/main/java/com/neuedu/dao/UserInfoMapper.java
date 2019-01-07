package com.neuedu.dao;

import com.neuedu.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_user
     *
     * @mbggenerated
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_user
     *
     * @mbggenerated
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_user
     *
     * @mbggenerated
     */
    List<UserInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * 校验用户名是否存在
     * */
    int checkUsername(String username);

    /**
     * 校验邮箱是否存在
     * */
    int checkEmail(String email);

    /**
     * 根据用户名和密码查询用户信息
     * */
    UserInfo selectUserInfoByUsernameAndPassword(@Param("username") String username,
                                                 @Param("password") String password);

    /**
     * 根据用户名查询密码
     * */
    String selectQuestionByUsername(String username);

    /**
     * 根据用户名和密保问题及答案查询
     * */
    int selectByUsernameAndQuestionAndAnswer(@Param("username") String username,
                                             @Param("question") String question,
                                             @Param("answer") String answer);

    /**
     * 修改用户密码的接口
     * */
    int updateUserPassword(@Param("username") String username,
                           @Param("password") String password);

    /**
     * 更新用户信息接口
     * */
    int updateUserBySelectActive(UserInfo userInfo);



}