package com.caroLe.manager.thirdPaty.service;

import com.caroLe.manager.common.result.Result;

import java.util.Map;

/**
 * @author CaroLe
 * @Date 2023/8/6 15:32
 * @Description
 */
public interface TxService {
    /**
     * 创建人员
     * 
     * @param map 参数
     * @return faceId
     */
    Result<String> createPerson(Map<String, Object> map);
}
