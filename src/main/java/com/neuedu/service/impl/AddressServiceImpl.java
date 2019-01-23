package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        //step1:参数非空校验
        if (userId == null) {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:添加
        shipping.setUserId(userId);
        shippingMapper.insert(shipping);
        //step3:返回结果
        Map<String, Integer> map = Maps.newHashMap();
        map.put("shippingId", shipping.getId());
        return ServerResponse.createServerResponseBySucess(null, map);
    }

    @Override
    public ServerResponse del(Integer userId, Integer shippingId) {
        //step1:非空校验
        if (shippingId == null) {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:删除
        int result = shippingMapper.deleteByUserIdAndShippingId(userId, shippingId);
        //step3:返回结果
        if (result > 0) {
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError("删除失败");
    }

    @Override
    public ServerResponse update(Shipping shipping) {
        //step1:非空校验
        if (shipping == null) {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:更新
        int result = shippingMapper.updateBySelectiveKey(shipping);
        //step3:返回结果
        if (result > 0) {
            return ServerResponse.createServerResponseBySucess("更新地址成功");
        }
        return ServerResponse.createServerResponseByError("更新失败");
    }

    @Override
    public ServerResponse select(Integer shippingId) {
        //step1:参数非空校验
        if (shippingId == null) {
            return ServerResponse.createServerResponseByError("参数错误");
        }
        //step2:查询
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);
        //step3:返回结果
        return ServerResponse.createServerResponseBySucess(null, shipping);
    }

    /**
     * 分页查询
     */
    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {
        //step1: 查询
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectAll();
        PageInfo pageInfo = new PageInfo(shippingList);

        return ServerResponse.createServerResponseBySucess(null, pageInfo);
    }


}
