package com.neuedu.common;

public class Const {

    public static final String CURRENTUSER = "current_user";

    //未登录时的状态码, 判断登录?
    /**
     * 登录时状态码枚举
     * 2 -> 需要登录
     * 3 -> 无权操作
     * */
    public enum ResponseCodeEnum {

        NEAD_LOGIN(2,"需要登录"),
        NO_PRIVILEGE(3,"无权限操作")
        ;

        private int code;
        private String desc;

        ResponseCodeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    //变量值有限的就可以定义成枚举
    /**
     * 用户角色枚举
     * 0 -> 管理员
     * 1 -> 普通用户
     * */
    public enum RoleEnum{

        ROLE_ADMIN(0,"管理员"),
        ROLE_CUSTOMER(1,"普通用户")
        ;

        private int code;
        private String desc;
        private RoleEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 商品状态枚举
     * 1 -> 在售
     * 2 -> 下架
     * 3 -> 删除
     * */
    public enum ProductStatusEnum {

        PRODUCT_ONLINE(1,"在售"),
        PRODUCT_OFLINE(2,"下架"),
        PRODUCT_DELETE(3,"删除")

        ;

        private Integer code;
        private String desc;

        private ProductStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 购物车是否选中商品的枚举
     * 1 -> 已勾选
     * 0 -> 未勾选
     * */
    public enum CartCheckedEnum{
        PRODUCT_CHECKED(1,"已勾选"),
        PRODUCT_UNCHECKED(0,"未勾选");
        ;

        private int code;
        private String desc;

        CartCheckedEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }



}
