package com.aisino.feign;

import lombok.Data;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 18:02
 * @Description:
 */
@Data
public class Response<T> {

    private int errorCode;

    private String msg;

    private T data;

}
