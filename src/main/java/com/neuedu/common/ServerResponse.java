package com.neuedu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 响应前端的高复用对象
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {


    private int status; //状态码 0:成功
    private T data; //当status=0时, data对应的接口响应的数据
    private String msg; //提示信息

    private ServerResponse() {

    }

    public ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 判断接口是否访问成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS;
    }


    /**
     * {"status":0}
     */
    public static ServerResponse createServerResponseBySuccess() {
        return new ServerResponse(ResponseCode.SUCCESS);
    }

    /**
     * {"status":0,"msg":"aaa"}
     */
    public static ServerResponse createServerResponseBySucess(String msg) {
        return new ServerResponse(ResponseCode.SUCCESS, msg);
    }

    /**
     * {"status":0,"msg":"aaa","data":{}} 泛型方法
     */
    public static <T> ServerResponse createServerResponseBySucess(String msg, T data) {
        return new ServerResponse(ResponseCode.SUCCESS, msg, data);
    }


    /**
     * "status":1
     */
    public static ServerResponse createServerResponseByError() {
        return new ServerResponse(ResponseCode.ERROR);
    }

    /**
     * {"status":custom} 自定义状态码
     */
    public static ServerResponse createServerResponseByError(Integer status) {
        return new ServerResponse(status);
    }

    /**
     * {"status":1,"msg":"aaa"} 配置类中拿状态码
     */
    public static ServerResponse createServerResponseByError(String msg) {
        return new ServerResponse(ResponseCode.ERROR, msg);
    }

    /**
     * {"status":custom,"msg":"aaa"} 自定义状态码
     */
    public static ServerResponse createServerResponseByError(Integer status, String msg) {
        return new ServerResponse(status, msg);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}











