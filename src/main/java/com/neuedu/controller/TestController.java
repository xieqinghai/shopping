package com.neuedu.controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.json.ObjectMapperApi;
import com.neuedu.pojo.UserInfo;
import com.neuedu.redis.RedisApi;
import com.neuedu.redis.RedisPool;
import com.neuedu.redis.RedisProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    RedisProperties redisProperties;

    @RequestMapping("/user/{userId}")
    public ServerResponse<UserInfo> getById(@PathVariable int userId) {

        System.out.println(redisProperties.getMaxIdle());

        UserInfo u = userInfoMapper.selectByPrimaryKey(userId);

        if (u != null) {
            return ServerResponse.createServerResponseBySucess(null, u);
        } else {
            return ServerResponse.createServerResponseByError("fail");
        }
    }

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value="/redis")
    public String getJedis(){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.set("root", "root1");
        jedisPool.returnResource(jedis);
        return value;
    }

    @Autowired
    private RedisApi redisApi;

    @RequestMapping(value="/key/{key}/{value}")
    public String set(@PathVariable("key") String key,@PathVariable("value") String value){
        String result = redisApi.set(key, value);
        return result;
    }

    @Autowired
    ObjectMapperApi objectMapperApi;

    /**
     * 对象转json测试
     * */
    @RequestMapping(value="/user/json/{userId}")
    public ServerResponse<UserInfo> findUserByJson(@PathVariable int userId) {

        UserInfo u = userInfoMapper.selectByPrimaryKey(userId);

        String json = objectMapperApi.obj2str(u);
        System.out.println("u======="+json);

        return ServerResponse.createServerResponseBySucess(null,u);
    }

    /**
     * 对象转jsonPretty
     * json转对象
     * */
    @RequestMapping(value="/user/jsonPretty/{userId}")
    public ServerResponse<UserInfo> findUserByJsonPretty(@PathVariable int userId) {

        UserInfo u = userInfoMapper.selectByPrimaryKey(userId);
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(u);
        String json = objectMapperApi.obj2strPretty(userInfoList);
        List<UserInfo> list = objectMapperApi.str2Obj(json, new TypeReference<List<UserInfo>>() {});
        System.out.println("u======="+list);

        return ServerResponse.createServerResponseBySucess(null,u);
    }



}
