package com.caroLe.manager.thirdPaty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.caroLe.manager.common.context.BaseContext;
import com.caroLe.manager.common.context.RequestContext;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.thirdPaty.service.WxService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author CaroLe
 * @Date 2023/7/9 21:47
 * @Description
 */
@Service
public class WxServiceImpl implements WxService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    /**
     * 获取永久授权Id
     *
     * @param code 临时授权code
     * @return openId
     */
    @Override
    public Result<String> getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> map = new HashMap<>(4);
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", RequestContext.AUTHORIZATION_CODE);
        String response = HttpUtil.get(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        String openId = json.getStr("openid");
        if (StrUtil.isEmpty(openId)) {
            throw new DataException(ErrorType.AUTHENTICATION_FAILED);
        }
        return Result.success(openId, SuccessType.SUCCESS);
    }

    /**
     * 获取token
     *
     * @return token
     */
    @Override
    public Result<String> getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> map = new HashMap<>(3) {
            {
                put("grant_type", BaseContext.CLIENT_CREDENTIAL);
                put("appid", appId);
                put("secret", appSecret);
            }
        };
        String response = HttpUtil.get(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        if (json.containsKey(BaseContext.ACCESS_TOKEN)) {
            String accessToken = json.getStr(BaseContext.ACCESS_TOKEN);
            return Result.success(accessToken, SuccessType.SUCCESS);
        } else {
            throw new DataException(ErrorType.AUTHENTICATION_FAILED);
        }
    }
}
