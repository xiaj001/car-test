package com.aisino.feign;

import lombok.Data;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 18:12
 * @Description:
 */
@Data
public class LoginRep {

    private String grantToken;

    private Long expireTime;

}
