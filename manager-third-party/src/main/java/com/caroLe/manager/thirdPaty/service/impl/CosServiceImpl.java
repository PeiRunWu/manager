package com.caroLe.manager.thirdPaty.service.impl;

import cn.hutool.core.io.FileUtil;
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
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingRequest;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingResponse;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.MultipleImageAuditingImpl;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

    @Autowired
    private ThreadPoolExecutor threadPool;

    /**
     * 上传到腾讯云cos
     *
     * @param file
     * @param path
     * @return
     */
    @Override
    public Result<String> uploadOssFile(MultipartFile file, String path) {
        TransferManager transferManager = createTransferManager();
        String fileName =
            path + "/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID() + file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata);
            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            Upload upload = transferManager.upload(putObjectRequest);
            upload.waitForUploadResult();
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
            COS cosClient = transferManager.getCOSClient();
            ImageAuditingResponse response = cosClient.imageAuditing(request);
            if (!StrUtil.equals(response.getPornInfo().getHitFlag(), BaseContext.ZERO)
                || !StrUtil.equals(response.getTerroristInfo().getHitFlag(), BaseContext.ZERO)
                || !StrUtil.equals(response.getAdsInfo().getHitFlag(), BaseContext.ZERO)) {
                deleteCosFile(path);
                throw new DataException(ErrorType.IMAGE_CONTENT_ILLEGAL);
            }
        }
        transferManager.shutdownNow(true);
        return Result.success(path, SuccessType.SUCCESS);
    }

    /**
     * 删除文件cos
     *
     * @param fileName
     * @return
     */
    @Override
    public Result<String> deleteCosFile(String fileName) {
        TransferManager transferManager = createTransferManager();
        try {
            URL url = new URL(fileName);
            String path = url.getPath();
            if (path.startsWith(BaseContext.SEPARATOR)) {
                path = path.substring(1);
            }
            COS cosClient = transferManager.getCOSClient();
            cosClient.deleteObject(bucketName, path);
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new DataException(ErrorType.FILE_DELETE_FAILED);
        } finally {
            transferManager.shutdownNow(true);
        }
        return Result.success(SuccessType.SUCCESS);
    }

    private TransferManager createTransferManager() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(cosRegion));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        COSClient cosClient = new COSClient(cred, clientConfig);
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5 * 1024 * 1024);
        transferManagerConfiguration.setMinimumUploadPartSize(1024 * 1024);
        transferManager.setConfiguration(transferManagerConfiguration);
        return transferManager;
    }

}
