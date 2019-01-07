
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
    step3:项目打成war包
    step4:将war包上传到阿里云服务器的tomcat/webapps上
    step5:访问测试
