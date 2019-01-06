package com.neuedu.common;

public class Const {

    public static final String CURRENTUSER = "current_user";

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
