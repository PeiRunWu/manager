package com.caroLe.manager.thirdPaty.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.StatusType;
import com.caroLe.manager.thirdPaty.service.TxService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;

/**
 * @author CaroLe
 * @Date 2023/8/6 15:32
 * @Description
 */
@Service
public class TxServiceImpl implements TxService {

    @Value("${tencentCloud.secretId}")
    private String secretId;

    @Value("${tencentCloud.secretKey}")
    private String secretKey;

    @Value("${tencentCloud.face.groupName}")
    private String groupName;

    @Value("${tencentCloud.cosRegion}")
    private String cosRegion;

    /**
     * 创建人员
     *
     * @param map 参数
     * @return faceId
     */
    @Override
    public Result<String> createPerson(Map<String, Object> map) {
        Credential cred = new Credential(secretId, secretKey);
        IaiClient client = new IaiClient(cred, cosRegion);
        try {
            CreatePersonRequest req = new CreatePersonRequest();
            req.setGroupId(groupName);
            req.setPersonId(map.get("personId").toString());
            long gender = "男".equals(map.get("sex")) ? 1L : 2L;
            req.setGender(gender);
            req.setQualityControl(4L);
            req.setUniquePersonControl(4L);
            req.setPersonName(map.get("personName").toString());
            req.setImage(map.get("image").toString());
            CreatePersonResponse resp = client.CreatePerson(req);
            return Result.success(resp.getFaceId(), StatusType.SUCCESS);
        } catch (TencentCloudSDKException e) {
            throw new DataException(ErrorType.AUTHENTICATION_FAILED);
        }
    }
}
