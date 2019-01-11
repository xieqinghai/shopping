package com.neuedu;

import com.neuedu.utils.BigDecimalUtils;

import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) {
        double d1 = 0.5;

        BigDecimal d2 = new BigDecimal(0.7);
        BigDecimal add = BigDecimalUtils.add(d2.intValue(),d1);
        System.out.println(d2.intValue());
        System.out.println(add);
    }


}
