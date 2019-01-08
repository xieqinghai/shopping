
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

    
