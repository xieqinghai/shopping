package com.neuedu.common;

public class Const {

    public static final String CURRENTUSER = "current_user";

    //未登录时的状态码, 判断登录?
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



}
