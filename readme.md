
#接口文档格式

## 门户-支付接口
#### 1.支付
**/order/pay.do**
>request
```
orderNo
```
>response

sucess
```
{

}
```
fail
```
{

}
```



### 搭建框架
    1.依赖 generator插件
    2.配置generatorConfig.xml文件
    3.配置application.properties文件
    4 执行generator插件生成 pojo dao mapper
    5 创建一个TestController
    6 使用框架 跑通
### 服务端接口返回前端的统一对象
    calss ServerResponse<T> {
        int status; //接口返回状态码
        T data; //封装了接口的返回数据
        String msg; //封装了信息
    }

### 找回密码接口
    step1: 校验username --> 查询找回密码问题
    step2: 前端,提交问题答案
    step3: 校验答案 --> 修改密码
### 迭代开发-线上部署
    step1:在阿里云服务器上建库 建表
    step2:修改代码中的数据库的连接参数
    step3:项目打成war包  , springboot打包成jar
    step4:将war包上传到阿里云服务器的tomcat/webapps上 , 
        springboot放到服务器上即可同时关掉占用8080进程
    step5:访问测试加包名, springboot java -jar xxx.jar启动 
 ### 模块一 用户模块
 
### 模块二 类别模块
#### 1. 功能介绍
    获取节点
    增加节点
    修改名称
    获取分类
    递归子节点ID
#### 2. 学习目标
    如何设计及封装无限层级的树状结构
    递归算法的设计思路
    如何处理复杂对象重排
    重写hashcod和equals的注意事项
### 模块三 商品模块
#### 功能介绍
##### 前台功能
    产品搜索
    动态排序列表
    商品详情
##### 后台功能
    商品列表
    商品搜索
    图片上传
    复文本上传
    商品详情
    商品上下架
    增加商品
    更新商品
####学习目标
    FTP服务的对接
    SpringMVC文件上传
    流读取Properties配置文件
    抽象POJO、BO、VO对象之间的转换关系及解决思路
    joda-time快速入门
    静态块
    Mybatis-PageHelper高效准确地分页及动态排序
    Mybatis对List遍历的实现方法
    Mybatis对Where语句动态拼接
    POJO、BO Business object、VO view object
    POJO、VO 
      :BO->business object 业务逻辑层实体类
      :VO->view object 视图层实体类,业务较复杂POJO不能满足向前端显示要求,就需要在VO里创建实体类
   #### 企业级开发都会有:图片服务器 数据库服务器 单独业务逻辑服务器  
## 购物车模块
    要用: BigDecimal 的字符串类型构造方法
    BigDecimal bigDecimal = new BigDecimal("0.05");
    BigDecimal bigDecimal1 = new BigDecimal("0.01");
    System.out.println(bigDecimal.add(bigDecimal1));
    
>BigDecimal的API 
     
     .add() 加法
     .subtract() 减法
     .multiply() 乘法
     .divide() 除法





















####==========================
[MENU]

## 门户—支付接口

#### 1.支付
/order/pay.do

http://localhost:8080/order/pay.do?orderNo=1485158676346

> request
```
orderNo
```
> response

success

```
{
    "status": 0,
    "data": {
        "orderNo": "1485158676346",
        "qrPath": "http://img.business.com/qr-1492329044075.png"
    }
}
```
fail

```
{
    "status": 1,
    "msg": "支付宝生成订单失败"
}

```
#### 2.查询订单支付状态

/order/query_order_pay_status.do

http://localhost:8080/order/query_order_pay_status.do?orderNo=1485158676346

> request
```
orderNo
```
>response

success
```
{
    "status": 0,
    "data": true
}
```

fail
```
{
    "status": 1,
    "msg": "该用户并没有该订单,查询无效"
}
```

#### 3.支付宝回调

参考支付宝回调文档： https://support.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.mFogPC&treeId=193&articleId=103296&docType=1

/order/alipay_callback.do

>request
```
HttpServletRequest
```
>response

success
```
success
```

fail
```
failed
```

## 门户_订单模块

#### 1.创建订单

/order/create.do

引用已存在的收货地址id http://localhost:8080/order/create.do?shippingId=5

>request
```
shippingId
```
>response

success
```
{
    "status": 0,
    "data": {
        "orderNo": 1485158223095,
        "payment": 2999.11,
        "paymentType": 1,
        "postage": 0,
        "status": 10,
        "paymentTime": null,
        "sendTime": null,
        "endTime": null,
        "closeTime": null,
        "createTime": 1485158223095,
        "orderItemVoList": [
            {
                "orderNo": 1485158223095,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": null
            }
        ],
        "shippingId": 5,
        "shippingVo": null
    }
}
```
fail
```
{
    "status": 1,
    "msg": "创建订单失败"
}
```
#### 2.获取订单的商品信息

/order/get_order_cart_product.do

http://localhost:8080/order/get_order_cart_product.do

>request

无
>response

success
```
{
    "status": 0,
    "data": {
        "orderItemVoList": [
            {
                "orderNo": null,
                "productId": 1,
                "productName": "iphone7",
                "productImage": "business/aa.jpg",
                "currentUnitPrice": 7999,
                "quantity": 10,
                "totalPrice": 79990,
                "createTime": ""
            }
        ],
        "imageHost": "http://img.business.com/",
        "productTotalPrice": 79990
    }
}
```
fail
```
{
    "status": 1,
    "msg": "用户未登录"
}
```
#### 3.订单List

http://localhost:8080/order/list.do?pageSize=3

/order/list.do

>request
```
pageSize(default=10)
pageNum(default=1)
```
>response

success
```
{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 3,
    "size": 3,
    "orderBy": null,
    "startRow": 1,
    "endRow": 3,
    "total": 16,
    "pages": 6,
    "list": [
      {
        "orderNo": 1485158676346,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:36",
        "orderItemVoList": [
          {
            "orderNo": 1485158676346,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:36"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "geely",
        "shippingVo": null
      },
      {
        "orderNo": 1485158675516,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675516,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      },
      {
        "orderNo": 1485158675316,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675316,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 2,
    "lastPage": 6,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6
    ]
  }
}
```
fail
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}

```
或
```
{
  "status": 1,
  "msg": "没有权限"
}
```


#### 4.订单详情detail

http://localhost:8080/order/detail.do?orderNo=1480515829406

/order/detail.do

>request
```
orderNo
```
>response

success
```
{
  "status": 0,
  "data": {
    "orderNo": 1480515829406,
    "payment": 30000.00,
    "paymentType": 1,
    "paymentTypeDesc": "在线支付",
    "postage": 0,
    "status": 10,
    "statusDesc": "未支付",
    "paymentTime": "",
    "sendTime": "",
    "endTime": "",
    "closeTime": "",
    "createTime": "2016-11-30 22:23:49",
    "orderItemVoList": [
      {
        "orderNo": 1480515829406,
        "productId": 1,
        "productName": "iphone7",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 10000.00,
        "quantity": 1,
        "totalPrice": 10000.00,
        "createTime": "2016-11-30 22:23:49"
      },
      {
        "orderNo": 1480515829406,
        "productId": 2,
        "productName": "oppo R8",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 20000.00,
        "quantity": 1,
        "totalPrice": 20000.00,
        "createTime": "2016-11-30 22:23:49"
      }
    ],
    "imageHost": "http://img.business.com/",
    "shippingId": 3,
    "receiverName": "betty",
    "shippingVo": {
      "receiverName": "betty",
      "receiverPhone": "0100",
      "receiverMobile": "186",
      "receiverProvince": "北京",
      "receiverCity": "北京",
      "receiverDistrict": "昌平区",
      "receiverAddress": "矩阵小区",
      "receiverZip": "100000"
    }
  }
}
```
fail
```
{
  "status": 1,
  "msg": "没有找到订单"
}
```
#### 5.取消订单

http://localhost:8080/order/cancel.do?orderNo=1480515829406

/order/cancel.do

>request
```
orderNo
```
>response

success
```
{
  "status": 0
}
```
fail
```
{
  "status": 1,
  "msg": "该用户没有此订单"
}
```
或
```
{
  "status": 1,
  "msg": "此订单已付款，无法被取消"
}
```

## 后台_订单模块
#### 1.订单List

http://localhost:8080/manage/order/list.do?pageSize=3

/manage/order/list.do

>request
```
pageSize(default=10)
pageNum(default=1)
```
>response

success
```
{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 3,
    "size": 3,
    "orderBy": null,
    "startRow": 1,
    "endRow": 3,
    "total": 16,
    "pages": 6,
    "list": [
      {
        "orderNo": 1485158676346,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:36",
        "orderItemVoList": [
          {
            "orderNo": 1485158676346,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:36"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "shippingVo": null
      },
      {
        "orderNo": 1485158675516,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675516,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      },
      {
        "orderNo": 1485158675316,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675316,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 2,
    "lastPage": 6,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6
    ]
  }
}
```
fail
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}
```

或
```
{
  "status": 1,
  "msg": "没有权限"
}
```


#### 2.按订单号查询

http://localhost:8080/manage/order/search.do?orderNo=1480515829406

/manage/order/search.do

>request
```
orderNo
```
>response

success
```
{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 3,
    "size": 3,
    "orderBy": null,
    "startRow": 1,
    "endRow": 3,
    "total": 16,
    "pages": 6,
    "list": [
      {
        "orderNo": 1485158676346,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:36",
        "orderItemVoList": [
          {
            "orderNo": 1485158676346,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:36"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "shippingVo": null
      },
      {
        "orderNo": 1485158675516,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675516,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      },
      {
        "orderNo": 1485158675316,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1485158675316,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:35"
          }
        ],
        "imageHost": "http://img.business.com/",
        "shippingId": 5,
        "receiverName": "betty",
        "shippingVo": null
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 2,
    "lastPage": 6,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6
    ]
  }
}
```
fail
```
{
  "status": 1,
  "msg": "没有找到订单"
}
```
####3.订单详情

http://localhost:8080/manage/order/detail.do?orderNo=1480515829406

/manage/order/detail.do

>request
```
orderNo
```
>response

success
```
{
  "status": 0,
  "data": {
    "orderNo": 1480515829406,
    "payment": 30000.00,
    "paymentType": 1,
    "paymentTypeDesc": "在线支付",
    "postage": 0,
    "status": 10,
    "statusDesc": "未支付",
    "paymentTime": "",
    "sendTime": "",
    "endTime": "",
    "closeTime": "",
    "createTime": "2016-11-30 22:23:49",
    "orderItemVoList": [
      {
        "orderNo": 1480515829406,
        "productId": 1,
        "productName": "iphone7",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 10000.00,
        "quantity": 1,
        "totalPrice": 10000.00,
        "createTime": "2016-11-30 22:23:49"
      },
      {
        "orderNo": 1480515829406,
        "productId": 2,
        "productName": "oppo R8",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 20000.00,
        "quantity": 1,
        "totalPrice": 20000.00,
        "createTime": "2016-11-30 22:23:49"
      }
    ],
    "imageHost": "http://img.business.com/",
    "shippingId": 3,
    "receiverName": "betty",
    "shippingVo": {
      "receiverName": "betty",
      "receiverPhone": "0100",
      "receiverMobile": "186",
      "receiverProvince": "北京",
      "receiverCity": "北京",
      "receiverDistrict": "海淀区",
      "receiverAddress": "某小区",
      "receiverZip": "100000"
    }
  }
}
```
fail
```
{
  "status": 1,
  "msg": "没有找到订单"
}
```
#### 4.订单发货

http://localhost:8080/manage/order/send_goods.do?orderNo=1480515829406

/manage/order/send_goods.do

>request

```
orderNo
```
>response

success

```
{
  "status": 0,
  "data": "发货成功"
}
```
fail
```
{
  "status": 1,
  "msg": "发货失败"
}
```


## 门户_收货地址接口
#### 1.添加地址

/shipping/add.do

http://localhost:8080/shipping/add.do?userId=1&receiverName=geely&receiverPhone=010&receiverMobile=18688888888&receiverProvince=%E5%8C%97%E4%BA%AC&receiverCity=%E5%8C%97%E4%BA%AC%E5%B8%82&receiverAddress=%E4%B8%AD%E5%85%B3%E6%9D%91&receiverZip=100000

>request
```
userId=1
receiverName=zhangsan
receiverPhone=010
receiverMobile=18688888888
receiverProvince=北京
receiverCity=北京市
receiverAddress=中关村
receiverZip=100000
```
>response

success
```
{
    "status": 0,
    "msg": "新建地址成功",
    "data": {
        "shippingId": 28
    }
}
```
fail
```
{
    "status": 1,
    "msg": "新建地址失败"
}
```
#### 2.删除地址

/shipping/del.do

>request
```
shippingId
```
>response

success
```
{
    "status": 0,
    "msg": "删除地址成功"
}
```
fail
```
{
    "status": 1,
    "msg": "删除地址失败"
}
```
#### 3.登录状态更新地址

/shipping/update.do

http://localhost:8080/shipping/update.do?id=5&receiverName=AAA&receiverPhone=010&receiverMobile=18688888888&receiverProvince=%E5%8C%97%E4%BA%AC&receiverCity=%E5%8C%97%E4%BA%AC%E5%B8%82&receiverDistrict=%E6%B5%B7%E6%B7%80%E5%8C%BA&receiverAddress=%E4%B8%AD%E5%85%B3%E6%9D%91&receiverZip=100000

>request
```
id=1
receiverName=zhangsan
receiverPhone=010
receiverMobile=18688888888
receiverProvince=北京
receiverCity=北京市
receiverAddress=中关村
receiverZip=100000
```
>response

success
```
{
    "status": 0,
    "msg": "更新地址成功"
}
```
fail
```

{
    "status": 1,
    "msg": "更新地址失败"
}
```
#### 4.选中查看具体的地址

/shipping/select.do

>request
```
shippingId
```
>response

success
```
{
    "status": 0,
    "data": {
        "id": 4,
        "userId": 13,
        "receiverName": "zhangsan",
        "receiverPhone": "010",
        "receiverMobile": "18688888888",
        "receiverProvince": "北京",
        "receiverCity": "北京市",
        "receiverAddress": "中关村",
        "receiverZip": "100000",
        "createTime": 1485066385000,
        "updateTime": 1485066385000
    }
}
```
fail
```
{
    "status": 1,
    "msg": "请登录之后查询"
}
```
#### 5.地址列表

/shipping/list.do

http://localhost:8080/shipping/list.do

>request
```
pageNum(默认1),pageSize(默认10)
```
>response

success
```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "size": 2,
        "orderBy": null,
        "startRow": 1,
        "endRow": 2,
        "total": 2,
        "pages": 1,
        "list": [
            {
                "id": 4,
                "userId": 13,
                "receiverName": "zhangsan",
                "receiverPhone": "010",
                "receiverMobile": "18688888888",
                "receiverProvince": "北京",
                "receiverCity": "北京市",
                "receiverAddress": "中关村",
                "receiverZip": "100000",
                "createTime": 1485066385000,
                "updateTime": 1485066385000
            },
            {
                "id": 5,
                "userId": 13,
                "receiverName": "AAA",
                "receiverPhone": "010",
                "receiverMobile": "18688888888",
                "receiverProvince": "北京",
                "receiverCity": "北京市",
                "receiverAddress": "中关村",
                "receiverZip": "100000",
                "createTime": 1485066392000,
                "updateTime": 1485075875000
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 0,
        "lastPage": 1,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ]
    }
}
```
fail
```
{
    "status": 1,
    "msg": "请登录之后查询"
}
```

## 门户_购物车接口
#### 1.购物车List列表

/cart/list.do

http://localhost:8080/cart/list.do

>request

无参数,需要登录状态
>response

success
```

{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 1,
                "userId": 13,
                "productId": 1,
                "quantity": 1,
                "productName": "iphone7",
                "productSubtitle": "双十一促销",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 7199.22,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            },
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 10198.33
    }
}
```

fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 2.购物车添加商品

/cart/add.do

http://localhost:8080/cart/add.do?productId=1&count=10

请注意这个字段，超过数量会返回这样的标识"limitQuantity"

失败的：LIMIT_NUM_FAIL 成功的：LIMIT_NUM_SUCCESS

>request
```
productId,
count
```
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 1,
                "userId": 13,
                "productId": 1,
                "quantity": 12,
                "productName": "iphone7",
                "productSubtitle": "双十一促销",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 86390.64,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            },
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 89389.75
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 3.更新购物车某个产品数量

/cart/update.do

http://localhost:8080/cart/update.do?productId=1&count=2

>request
```
productId,
count
```
>response

响应同2
```
success

{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 1,
                "userId": 13,
                "productId": 1,
                "quantity": 12,
                "productName": "iphone7",
                "productSubtitle": "双十一促销",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 86390.64,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            },
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 89389.75
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 4.移除购物车某个产品

/cart/delete_product.do

http://localhost:8080/cart/delete_product.do?productIds=1,3

>request
```
productIds
```
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 2999.11
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 5.购物车选中某个商品

/cart/select.do

http://localhost:8080/cart/select.do?productId=1

>request
```
productId
```
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 1,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 2999.11
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 6.购物车取消选中某个商品

/cart/un_select.do

http://localhost:8080/cart/un_select.do?productId=2

注意返回值中的cartTotalPrice，如果反选之后总价的变化

>request
```
productId
```
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 0,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 0
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 7.查询在购物车里的产品数量

/cart/get_cart_product_count.do

http://localhost:8080/cart/get_cart_product_count.do

未登录返回0

>request

无
>response

success
```
{
    "status": 0,
    "data": 0
    
}
```
fail
```
{
    "status": 10,
    "msg": "出现异常"
}
```
#### 8.购物车全选

/cart/select_all.do

http://localhost:8080/cart/select_all.do

注意返回值中的cartTotalPrice的变化

>request

无
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 0,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 2999.11
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 9.购物车取消全选

/cart/un_select_all.do

http://localhost:8080/cart/un_select_all.do

注意返回值中的cartTotalPrice总价的变化

>request

无
>response

success
```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "id": 2,
                "userId": 13,
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "oppo促销进行中",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productChecked": 0,
                "limitQuantity": "LIMIT_NUM_SUCCESS"
            }
        ],
        "allChecked": true,
        "cartTotalPrice": 0
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```

## 后台_产品接口
#### 1.产品list

http://localhost:8080/manage/product/list.do

**/manage/product/list.do**

> request
```
pageNum(default=1)
pageSize(default=10)
```
>response

success
```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "size": 2,
        "orderBy": null,
        "startRow": 1,
        "endRow": 2,
        "total": 2,
        "pages": 1,
        "list": [
            {
                "id": 1,
                "categoryId": 3,
                "name": "iphone7",
                "subtitle": "双十一促销",
                "mainImage": "mainimage.jpg",
                "status":1,
                "price": 7199.22
            },
            {
                "id": 2,
                "categoryId": 2,
                "name": "oppo R8",
                "subtitle": "oppo促销进行中",
                "mainImage": "mainimage.jpg",
                "status":1,
                "price": 2999.11
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 0,
        "lastPage": 1,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ]
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 2.产品搜索

http://localhost:8080/manage/product/search.do?productName=p

http://localhost:8080/manage/product/search.do?productId=1

**/manage/product/search.do**

>request
```
productName
productId
pageNum(default=1)
pageSize(default=10)
```

>response

success
```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "size": 1,
        "orderBy": null,
        "startRow": 1,
        "endRow": 1,
        "total": 1,
        "pages": 1,
        "list": [
            {
                "id": 1,
                "categoryId": 3,
                "name": "iphone7",
                "subtitle": "双十一促销",
                "mainImage": "mainimage.jpg",
                "price": 7199.22
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 0,
        "lastPage": 1,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ]
    }
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
```
#### 3.图片上传

**/manage/product/upload.do**

>request

```
<form name="form2" action="/manage/product/upload.do" method="post"  enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="upload"/>
</form>
```
>response

success
```
{
    "status": 0,
    "data": {
        "uri": "e6604558-c0ff-41b9-b6e1-30787a1e3412.jpg",
        "url": "http://img.business.com/e6604558-c0ff-41b9-b6e1-30787a1e3412.jpg"
    }
}
```
fail
```
status!=0的时候
```
#### 4.产品详情

http://localhost:8080/manage/product/detail.do?productId=2

**/manage/product/detail.do**

>request
```
productId

```

> response

success
```
{
    "status": 0,
    "data": {
        "id": 2,
        "categoryId": 2,
        "parentCategoryId":1,
        "name": "oppo R8",
        "subtitle": "oppo促销进行中",
        "imageHost": "http://img.business.com/",
        "mainImage": "mainimage.jpg",
        "subImages": "[\"business/aa.jpg\",\"business/bb.jpg\",\"business/cc.jpg\",\"business/dd.jpg\",\"business/ee.jpg\"]",
        "detail": "richtext",
        "price": 2999.11,
        "stock": 71,
        "status": 1,
        "createTime": "2016-11-20 14:21:53",
        "updateTime": "2016-11-20 14:21:53"
    }
}
```
fail
```
{
    "status": 1,
    "msg": "没有权限"
}
```
#### 5.产品上下架

http://localhost:8080/manage/product/set_sale_status.do?productId=1&status=1

**/manage/product/set_sale_status.do**

>request
```
productId
status
```
>response

success
```
{
    "status": 0,
    "data": "修改产品状态成功"
}
```
fail
```
{
    "status": 1,
    "data": "修改产品状态失败"
}
```
#### 6.新增OR更新产品

新增

新增 http://localhost:8080/manage/product/save.do?categoryId=1&name=海尔空调&subtitle=海尔大促销&subImages=test.jpg,11.jpg,2.jpg,3.jpg&detail=detailtext&price=1000&stock=100&status=1

更新 http://localhost:8080/manage/product/save.do?categoryId=1&name=海尔洗衣机&subtitle=海尔大促销&subImages=test.jpg&detail=detailtext&price=1000&stock=100&status=1&id=3

**/manage/product/save.do**

>request
```
categoryId=1&name=海尔洗衣机&subtitle=海尔大促销&mainImage=sss.jpg&subImages=test.jpg&detail=detailtext&price=1000&stock=100&status=1&id=3
```
>response

success
```
{
    "status": 0,
    "data": "更新产品成功"
}

或


{
    "status": 0,
    "data": "新增产品成功"
}
```
fail
```
{
    "status": 1,
    "data": "更新产品失败"
}
```
#### 7.富文本上传图片

**/manage/product/richtext_img_upload.do**

request
```
<form name="form2" action="/manage/product/upload.do" method="post"  enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="upload"/>
</form>
```
>response

success
```
{
    "file_path": "http://img.happymmall.com/5fb239f2-0007-40c1-b8e6-0dc11b22779c.jpg",
    "msg": "上传成功",
    "success": true
}
```
fail
```
{
    "success": false,
    "msg": "error message",
    "file_path": "[real file path]"
}
```
## 前台_产品品接口
#### 1.产品搜索及动态排序List

**/product/list.do**

> request
```
categoryId
keyword
pageNum(default=1)
pageSize(default=10)
orderBy(default="")：排序参数：例如price_desc，price_asc
```
> response
success
```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "size": 2,
        "orderBy": null,
        "startRow": 1,
        "endRow": 2,
        "total": 2,
        "pages": 1,
        "list": [
            {
                "id": 1,
                "categoryId": 3,
                "name": "iphone7",
                "subtitle": "双十一促销",
                "mainImage": "mainimage.jpg",
                "status":1,
                "price": 7199.22
            },
            {
                "id": 2,
                "categoryId": 2,
                "name": "oppo R8",
                "subtitle": "oppo促销进行中",
                "mainImage": "mainimage.jpg",
                "status":1,
                "price": 2999.11
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 0,
        "lastPage": 1,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ]
    }
}

```
fail
```
{
    "status": 1,
    "msg": "参数错误"
}
```

#### 2.产品detail

**/product/detail.do**


> request
```
productId
response
```
success
```
{
  "status": 0,
  "data": {
    "id": 2,
    "categoryId": 2,
    "name": "oppo R8",
    "subtitle": "oppo促销进行中",
    "mainImage": "mainimage.jpg",
    "subImages": "[\"business/aa.jpg\",\"business/bb.jpg\",\"business/cc.jpg\",\"business/dd.jpg\",\"business/ee.jpg\"]",
    "detail": "richtext",
    "price": 2999.11,
    "stock": 71,
    "status": 1,
    "createTime": "2016-11-20 14:21:53",
    "updateTime": "2016-11-20 14:21:53"
  }
}
```
fail
```
{
    "status": 1,
    "msg": "该商品已下架或删除"
}
```


## 后台_品类接口
#### 1.获取品类子节点(平级)
**/manage/category/get_category.do**
> request

```
 categoryId
```
> response

success

```

{
    "status": 0,
    "data": [
        {
            "id": 2,
            "parentId": 1,
            "name": "手机",
            "status": true,
            "sortOrder": 3,
            "createTime": 1479622913000,
            "updateTime": 1479622913000
        },
        {
            "id": 4,
            "parentId": 1,
            "name": "移动座机",
            "status": true,
            "sortOrder": 5,
            "createTime": 1480059936000,
            "updateTime": 1480491941000
        }
    ]
}
```
fail
```
{
    "status": 10,
    "msg": "用户未登录,请登录"
}
或

{
    "status": 1,
    "msg": "未找到该品类"
}
```

#### 2.增加节点

**/manage/category/add_category.do**
> request

```
parentId(default=0)
categoryName
```
> response

success

```

{
    "status": 0,
    "msg": "添加品类成功"
}
```
fail
```
{
    "status":1,
    "msg": "添加品类失败"
}
```
#### 3.修改品类名字
**/manage/category/set_category_name.do**

> request

```
categoryId
categoryName
```
> response

success

```

{
    "status": 0,
    "msg": "更新品类名字成功"
}
```
fail
```
{
    "status": 1,
    "msg": "更新品类名字失败"
}
```
#### 4.获取当前分类id及递归子节点categoryId

**/manage/category/get_deep_category.do**

> request

```
categoryId
```
> response

success

```

{
    "status": 0,
    "data": [
        100009,
        100010,
        100001,
        100006,
        100007,
        100008
    ]
}
```
fail
```
{
    "status": 1,
    "msg": "无权限"
}
```


## 后台_用户接口
 <font size="4px">
 1. 后台管理员登录<br/>
 2. 用户列表<br/>
</font>

#### 1.后台管理员登录
**/manage/user/login.do**
> request
```
String username,
String password
```
> response
 
  success
```
{
    "status":0,
    "data":{
        "id":12,
         "username":"aaa",
          "email":"aaa@126.com", 
          "phone":null, 
          "role":0,
          "createTime":1222, "updateTime":1222
    }
}
```
fail
```
{
    "status":1,
    "msg":"密码错误"
}
```

#### 2.用户列表
**/manage/user/list.do**
> request
```
String pageSize(default=10),
String pageNum(default=1)
```
> response
 
  success
```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 3,
        "size": 3,
        "orderBy": null,
        "startRow": 1,
        "endRow": 3,
        "total": 16,
        "pages": 6,
        "list": [
            {
                "id":17,
                "username":"rosen",
                "password":"",
                "email":"rosen1@happymmall.com",
                "phone":"15011111111",
                "question":"啊哈哈",
                "answer":"服不服",
                "role":0,
                "createTime":1489719093000,
                "updateTime":1513682138000
            },
            {
                "id":17,
                "username":"rosen",
                "password":"",
                "email":"rosen1@happymmall.com",
                "phone":"15011111111",
                "question":"啊哈哈",
                "answer":"服不服",
                "role":0,
                "createTime":1489719093000,
                "updateTime":1513682138000
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 2,
        "lastPage": 6,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
          1,
          2,
          3,
          4,
          5,
          6
        ]
    }
}
```
fail
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}


或

{
  "status": 1,
  "msg": "没有权限"
}
```


## 门户_用户接口
 <font size="4px">
 1. 登录<br/>
 2. 注册<br/>
 3. 检查用户名是否有效<br/>
 4. 获取登录用户信息<br/>
 5. 忘记密码<br/>
 6. 提交问题答案<br/>
 7. 忘记密码的重设密码<br/>
 8. 登录中状态重置密码<br/>
 9. 登录状态更新个人信息<br/>
 10. 获取当前登录用户的详细信息，并强制登录<br/>
 11. 退出登录<br/>
</font>

#### 1.登录
**/user/login.do**
> request
```
String username,
String password
```
> response
 
  success
```
{
    "status":0,
    "data":{
        "id":12,
         "username":"aaa",
          "email":"aaa@126.com", 
          "phone":null, 
          "createTime":1222, "updateTime":1222
    }
}
```
fail
```
{
    "status":1,
    "msg":"密码错误"
}
```

#### 2.注册
**/user/register.do**
> request
```
String username,
String password,
String email,
String phone,
String question,
String answer
```
> response
 
  success
```
{
    "status":0,
    "msg":"校验成功"
}
```
fail
```
{
    "status":1,
    "msg":"用户已存在"
}
```

#### 3.检查用户名是否有效
**/user/check_valid.do**

/check_valid.do?str=admin&type=username就是检查用户名
> request
```
String str,
String type
str可以是用户名或邮箱，对应的type是username和email
```
> response
 
  success
```
{
    "status":0,
    "msg":"校验成功"
}
```
fail
```
{
    "status":1,
    "msg":"用户名已存在"
}
```

#### 4.获取登录用户信息
**/user/get_user_info.do**
> request
```
无参数
```
> response
 
  success
```
{
    "status":0,
    "data":{
        "id":12,
         "username":"aaa",
          "email":"aaa@126.com", 
          "phone":null, 
          "createTime":1222, "updateTime":1222
    }
}
```
fail
```
{
    "status":1,
    "msg":"用户未登录，无法获取当前用户信息"
}
```
#### 5.忘记密码
**/user/forget_get_question.do?username=admin**
> request
```
String username
```
> response
 
  success
```
{
    "status":0,
    "data":"这里是问题"
}
```
fail
```
{
    "status":1,
    "msg":"该用户未设置找回密码问题"
}
```
#### 6.提交问题答案
**/user/forget_check_answer.do**
> request
```
String username,
String question,
String answer
```
> response
 正确的返回值里有一个token,修改密码的时候需要用这个传递给下一个接口
 
  success
```
{
    "status":0,
    "data":"3235ffe-fewff-ff34534"
}
```
fail
```
{
    "status":1,
    "msg":"问题答案错误"
}
```
#### 7.忘记密码的重设密码
**/user/forget_reset_password.do**
> request
```
String username,
String passwordNew,
String forgetToken
```
> response
 
  success
```
{
    "status":0,
    "msg":"修改密码错误"
}
```
fail
```
{
    "status":1,
    "msg":"修改密码操作失效"
}
```
或
```
{
    "status":1,
    "msg":"token已经失效"
}
```
#### 8.登录中状态重置密码
**/user/reset_password.do**
> request
```
String passwordOld,
String passwordNew
```
> response
 
  success
```
{
    "status":0,
    "msg":"修改密码成功"
}
```
fail
```
{
    "status":1,
    "msg":"旧密码输入错误"
}
```
#### 9.登录状态更新个人信息
**/user/update_information.do**
> request
```
String email,
String phone,
String question,
String answer
```
> response
 
  success
```
{
    "status":0,
    "msg":"更新个人信息成功"
}
```
fail
```
{
    "status":1,
    "msg":"用户未登录"
}
```
#### 10.获取当前登录用户的详细信息
**/user/get_inforamtion.do**
> request
```
无参数
```
> response
 
  success
```
{
    "status":0,
    "data":{
        "id":12,
         "username":"aaa",
          "email":"aaa@126.com", 
          "phone":null, 
          "question":"xxx",
          "answer":"xxx",
          "role":1,
          "createTime":1222, "updateTime":1222
    }
}
```
fail
```
{
    "status":10,
    "msg":"用户未登录，无法获取当前用户信息,status=10强制退出"
}
```
#### 11.退出登录
**/user/logout.do**
> request
```
无
```
> response
 
  success
```
{
    "status":0,
    "msg":"退出成功"
}
```
fail
```
{
    "status":1,
    "msg":"服务端异常"
}
```


