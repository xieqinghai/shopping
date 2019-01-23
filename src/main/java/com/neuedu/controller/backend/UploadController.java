package com.neuedu.controller.backend;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/manage/product")
public class UploadController {

    @Autowired
    IProductService productService;

    /**
     * springboot框架 通过提交方式不同 区分调用哪个方法
     * get方式提交, 是通过浏览器访问的
     * post方式提交,是通过页面提交来的
     * <p>
     * 如果form表单的 action为空 会提交到浏览器当前浏览器对应的url下,其实就是这里的upload()方法
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {

        return "upload"; //逻辑视图     前缀+逻辑视图+后缀 -->/templates/upload.html
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload2(@RequestParam(value = "upload_file", required = false) MultipartFile file) {

        //   String path = "D:\\ftpfile";
        //  /tmp/tomcat.4049254527875428741.8080/work/Tomcat/localhost/ROOT/image
        String path = "/tmp/tomcat.4049254527875428741.8080/work/Tomcat/localhost/ROOT/image";
        return productService.upload(file, path);

    }


}
