package com.aisino.feign;

import com.aisino.config.FormParam;
import lombok.Data;

/**
 * @author: xiajun003
 * @Date: 2019/4/3 15:13
 * @Description:
 */
@Data
@FormParam
public class LoginReqDTO {

    private String appId;
    private String signCode;
    private Long mt;
    private String userName;
    private String password;

    private LoginReqDTO loginReqDTO;
}
