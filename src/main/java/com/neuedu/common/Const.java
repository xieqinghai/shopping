package com.neuedu.common;

public class Const {

    public static final String CURRENTUSER = "current_user";

    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    public static final String AUTOLOGINTOKEN = "autoLoginToken";

    //未登录时的状态码, 判断登录?

    /**
     * 登录时状态码枚举
     * 2 -> 需要登录
     * 3 -> 无权操作
     */
    public enum ResponseCodeEnum {

        NEAD_LOGIN(2, "需要登录"),
        NO_PRIVILEGE(3, "无权限操作");

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
     */
    public enum RoleEnum {

        ROLE_ADMIN(0, "管理员"),
        ROLE_CUSTOMER(1, "普通用户");

        private int code;
        private String desc;

        private RoleEnum(int code, String desc) {
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
     */
    public enum ProductStatusEnum {

        PRODUCT_ONLINE(1, "在售"),
        PRODUCT_OFLINE(2, "下架"),
        PRODUCT_DELETE(3, "删除");

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
     */
    public enum CartCheckedEnum {
        PRODUCT_CHECKED(1, "已勾选"),
        PRODUCT_UNCHECKED(0, "未勾选");;

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

    /**
     * 订单状态的枚举
     */
    public enum OrderStatusEnum {
        // 0-已取消 10-未付款 20-已付款 40-已发货 50-交易成功 60-交易关闭
        ORDER_CANCELED(0, "已取消"),
        ORDER_UN_PAY(10, "未付款"),
        ORDER_PAYED(20, "已付款"),
        ORDER_SEND(40, "已发货"),
        ORDER_SUCCESS(50, "交易成功"),
        ORDER_CLOSED(60, "交易关闭");

        private int code;
        private String desc;

        private OrderStatusEnum(int code, String desc) {
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

        // 遍历枚举 通过values()
        //遍历自身, 返回自身
        public static OrderStatusEnum codeOf(Integer code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (code == orderStatusEnum.getCode()) {
                    return orderStatusEnum;
                }
            }
            return null;
        }

    }

    /**
     * 支付类型
     */
    public enum PaymentEnum {
        // 1-在线支付
        ONLINE(1, "线上支付"),


        ;

        private int code;
        private String desc;

        private PaymentEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        //遍历枚举 , 遍历自身返回自身
        public static PaymentEnum codeOf(Integer code) {
            for (PaymentEnum paymentEnum : values()) {
                if (code == paymentEnum.getCode()) {
                    return paymentEnum;
                }
            }
            return null;
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

    //支付平台
    public enum PaymentPlatformEnum {
        // 1-在线支付
        ALIPAY(1, "支付宝"),


        ;

        private int code;
        private String desc;

        private PaymentPlatformEnum(int code, String desc) {
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
