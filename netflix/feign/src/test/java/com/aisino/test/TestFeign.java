package com.aisino.test;

import feign.Feign;
import feign.Target;

/**
 * @author: xiajun003
 * @Date: 2019/4/15 15:02
 * @Description:
 */
public class TestFeign {

    public static void main(String[] args) {
        Feign feign = new Feign() {
            @Override
            public <T> T newInstance(Target<T> target) {
                return null;
            }
        };
    }
}
