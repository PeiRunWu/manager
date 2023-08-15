package com.caroLe.manager.thirdPaty.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.caroLe.manager.common.context.BaseContext;
import com.caroLe.manager.common.enums.ImageTypeEnum;
import com.caroLe.manager.common.exception.DataException;
import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.common.type.ErrorType;
import com.caroLe.manager.common.type.SuccessType;
import com.caroLe.manager.thirdPaty.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingRequest;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingResponse;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:55
 * @Description
 */
@Slf4j
@Service
public class CosServiceImpl implements CosService {

    @Value("${tencentCloud.secretId}")
    private String secretId;

    @Value("${tencentCloud.secretKey}")
    private String secretKey;

    @Value("${tencentCloud.bucketName}")
    private String bucketName;

    @Value("${tencentCloud.cosRegion}")
    private String cosRegion;

    /**
     * 上传到腾讯云cos
     *
     * @param file 文件
     * @param path 路径
     * @return cos路径
     */
    @Override
    public Result<String> uploadOssFile(MultipartFile file, String path) {
        COSClient cosClient = createCosClient();
        String fileName =
            path + "/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID() + file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata);
            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new DataException(ErrorType.FILE_UPLOAD_FAILED);
        }
        path = "https://" + bucketName + ".cos." + cosRegion + ".myqcloud.com" + "/" + fileName;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (ObjectUtil.isNotEmpty(ImageTypeEnum.getFileType(fileType))) {
            ImageAuditingRequest request = new ImageAuditingRequest();
            request.setBucketName(bucketName);
            request.setDetectType("porn,terrorist,politics,ads");
            request.setObjectKey(fileName);
            ImageAuditingResponse response = cosClient.imageAuditing(request);
            if (!StrUtil.equals(response.getPornInfo().getHitFlag(), BaseContext.ZERO)
                || !StrUtil.equals(response.getTerroristInfo().getHitFlag(), BaseContext.ZERO)
                || !StrUtil.equals(response.getAdsInfo().getHitFlag(), BaseContext.ZERO)) {
                deleteCosFile(path);
                throw new DataException(ErrorType.IMAGE_CONTENT_ILLEGAL);
            }
        }
        cosClient.shutdown();
        return Result.success(path, SuccessType.SUCCESS);
    }

    /**
     * 删除文件cos
     *
     * @param fileName 文件路径
     * @return 成功
     */
    @Override
    public Result<String> deleteCosFile(String fileName) {
        COSClient cosClient = createCosClient();
        try {
            URL url = new URL(fileName);
            String path = url.getPath();
            if (path.startsWith(BaseContext.SEPARATOR)) {
                path = path.substring(1);
            }
            cosClient.deleteObject(bucketName, path);
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new DataException(ErrorType.FILE_DELETE_FAILED);
        } finally {
            cosClient.shutdown();
        }
        return Result.success(SuccessType.SUCCESS);
    }

    private COSClient createCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(cosRegion));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        return new COSClient(cred, clientConfig);
    }

}
