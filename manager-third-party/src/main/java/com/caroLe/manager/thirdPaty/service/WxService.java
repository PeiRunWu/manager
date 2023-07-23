package com.caroLe.manager.thirdPaty.service;

import com.caroLe.manager.common.result.Result;

/**
 * @author CaroLe
 * @Date 2023/7/9 21:47
 * @Description
 */
public interface WxService {
    /**
     * 获取永久授权Id
     * 
     * @param code 临时授权code
     * @return openId
     */
    Result<String> getOpenId(String code);

    /**
     * 获取token
     * 
     * @return token
     */
    Result<String> getAccessToken();
}
