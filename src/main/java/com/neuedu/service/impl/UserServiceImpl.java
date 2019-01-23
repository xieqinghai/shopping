package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.redis.RedisApi;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    // 将接口的实现类注入到本类中来
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    RedisApi redisApi;

    @Override
    public ServerResponse login(String username, String password) {

        //step1:参数的非空校验
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServerResponse.createServerResponseByError("密码不能为空");
        }

        //step2:检查用户名是否存在
        int result = userInfoMapper.checkUsername(username);
        if (result == 0) {
            return ServerResponse.createServerResponseByError("用户名不存在");
        }

        //step3:根据用户名和密码查找用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("密码错误");
        }

        //step4:返回结果
        userInfo.setPassword("");
        return ServerResponse.createServerResponseBySucess(null, userInfo);
    }

    @Override
    public ServerResponse register(UserInfo userInfo) {
        //step1:参数非空校验
        //if(userInfo == null) {
        if (userInfo.getUsername() == null ||
                userInfo.getPassword() == null ||
                userInfo.getEmail() == null ||
                userInfo.getPhone() == null ||
                userInfo.getQuestion() == null ||
                userInfo.getAnswer() == null) {
            return ServerResponse.createServerResponseByError("参数必须");
        }

        //step2:校验用户名
        int result = userInfoMapper.checkUsername(userInfo.getUsername());
        if (result > 0) {
            return ServerResponse.createServerResponseByError("用户名已存在");
        }
        //step3:校验邮箱
        int result_email = userInfoMapper.checkEmail(userInfo.getEmail());
        if (result_email > 0) {//邮箱存在
            return ServerResponse.createServerResponseByError("邮箱已存在");
        }

        //step4:注册
        userInfo.setRole(Const.RoleEnum.ROLE_CUSTOMER.getCode());
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        int count = userInfoMapper.insert(userInfo);
        if (count > 0) {
            return ServerResponse.createServerResponseBySucess("注册成功");
        }

        //step5:返回结果
        return ServerResponse.createServerResponseByError("注册失败");
    }

    @Override
    public ServerResponse forget_get_question(String username) {
        //step1:参数校验
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }

        //step2:校验username
        int result = userInfoMapper.checkUsername(username);
        if (result == 0) {
            return ServerResponse.createServerResponseByError("用户名不存在,请重新输入");
        }

        //step3:查找密保问题
        String question = userInfoMapper.selectQuestionByUsername(username);
        if (question == null || question.equals("")) {
            return ServerResponse.createServerResponseByError("密保问题为空");
        }
        return ServerResponse.createServerResponseBySucess(null, question);
    }

    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        //step1:参数校验
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (question == null || question.equals("")) {
            return ServerResponse.createServerResponseByError("问题不能为空");
        }
        if (answer == null || answer.equals("")) {
            return ServerResponse.createServerResponseByError("答案不能为空");
        }

        //step2:根据username,question,answer查询
        int result = userInfoMapper.selectByUsernameAndQuestionAndAnswer(username, question, answer);
        if (result == 0) {
            //答案错误
            return ServerResponse.createServerResponseByError("答案错误");
        }

        //step3:服务端生成一个token保存并将token返回给客户端
        String forgetToken = UUID.randomUUID().toString();
        //谷歌的 guava cache
        //TokenCache.set(username, forgetToken);
        // 改成 redis 了
        //RedisPoolUtil.setex(username,forgetToken,60*3);
        redisApi.setex(username,1000*60,forgetToken);

        return ServerResponse.createServerResponseBySucess(null, forgetToken);
    }

    @Override
    public ServerResponse forget_reset_password(String username, String passwordNew, String forgetToken) {
        //step1:参数校验
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (passwordNew == null || passwordNew.equals("")) {
            return ServerResponse.createServerResponseByError("密码不能为空");
        }
        if (forgetToken == null || forgetToken.equals("")) {
            return ServerResponse.createServerResponseByError("token不能为空");
        }

        //step2:token校验
        //String token = TokenCache.get(username);
        //String token = RedisPoolUtil.get(username);
        String token = redisApi.get(username);
        if (token == null) {
            return ServerResponse.createServerResponseByError("token过期");
        }
        if (!token.equals(forgetToken)) {
            return ServerResponse.createServerResponseByError("无效的token");
        }

        //step3:修改密码
        int result = userInfoMapper.updateUserPassword(username, MD5Utils.getMD5Code(passwordNew));
        if (result > 0) {
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError("修改失败");
    }

    @Override
    public ServerResponse check_valid(String str, String type) {

        //step1:参数非空校验
        if (str == null || str.equals("")) {
            return ServerResponse.createServerResponseByError("用户名或者邮箱不能为空");
        }
        if (type == null || type.equals("")) {
            return ServerResponse.createServerResponseByError("校验的类型参数不能为空");
        }

        //step2: type : username ->校验用户名str
        //            : email ->校验邮箱 str
        if (type.equals("username")) {
            int result = userInfoMapper.checkUsername(str);
            if (result > 0) {
                //用户名已存在
                return ServerResponse.createServerResponseByError("用户名已存在");
            } else {
                return ServerResponse.createServerResponseBySuccess();
            }
        } else if (type.equals("email")) {
            int result = userInfoMapper.checkEmail(str);
            if (result > 0) {
                //用邮箱已存在
                return ServerResponse.createServerResponseByError("邮箱已存在");
            } else {
                return ServerResponse.createServerResponseBySuccess();
            }
        } else {
            return ServerResponse.createServerResponseByError("参数类型错误");
        }

        //step3:返回结果

    }

    @Override
    public ServerResponse reset_password(String username, String passwordOld, String passwordNew) {
        //step1:参数的非空校验
//        if(username == null || username.equals("")) {
//            return ServerResponse.createServerResponseByError("用户名不能为空");
//        }
        if (passwordOld == null || passwordOld.equals("")) {
            return ServerResponse.createServerResponseByError("旧密码不能为空");
        }
        if (passwordNew == null || passwordNew.equals("")) {
            return ServerResponse.createServerResponseByError("新密码不能为空");
        }

        //step2:根据username 和 passwordOld 查询用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsernameAndPassword(username, MD5Utils.getMD5Code(passwordOld));
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError("旧密码错误");
        }

        //step3:修改密码
        userInfo.setPassword(MD5Utils.getMD5Code(passwordNew));
        int result = userInfoMapper.updateByPrimaryKey(userInfo);
        if (result > 0) {
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError("密码修改失败");
    }

    @Override
    public ServerResponse update_information(UserInfo user) {
        //step1: 参数校验
        if (user == null) {
//        if(user.getEmail() == null ||
//                user.getPhone() == null ||
//                user.getQuestion() == null ||
//                user.getAnswer() == null) {
            return ServerResponse.createServerResponseByError("参数不能为空");
        }

        //step2: 更新用户信息
        int result = userInfoMapper.updateUserBySelectActive(user);
        if (result > 0) {
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError("更新个人信息失败");
    }

    @Override
    public UserInfo findUserInfoByUserid(Integer userId) {

        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateTokenByUserId(Integer userId, String token) {
        return userInfoMapper.updateTokenByUserId(userId,token);
    }

    @Override
    public UserInfo findUserInfoByToken(String token) {
        //step1:参数非空判断
        if(token == null || token.equals("")) {
            return null;
        }
        //step1:返回结果
        return userInfoMapper.findUserInfoByToken(token);
    }


}
