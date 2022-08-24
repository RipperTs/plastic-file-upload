package com.upload.servlet.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 上传文件配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "servlet.upload")
public class UploadConfig {
    /**
     * 上传类型 1 本地 2 阿里云OSS 3 腾讯云COS 4 七牛云QNC
     */
    private Integer config;

    /**
     * oss配置
     */
    private OssConfig oss;

    /**
     * 腾讯云配置
     */
    private CosConfig cos;

    /**
     * 七牛云配置
     */
    private QncConfig qnc;

    /**
     * 本地配置
     */
    private LocalConfig local;

    @Data
    public static class OssConfig {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private Map<String,String> bucketName;
        private String publicUrl;
        private String dir;

    }

    @Data
    public static class CosConfig{
        private String region;
        private String secretId;
        private String secretKey;
        private Map<String,String> bucketName;
        private String publicUrl;
        private String dir;
    }

    @Data
    public static class QncConfig{
        private String accessKey;
        private String secretKey;
        private Map<String,String> bucketName;
        private String publicUrl;
        private String privateUrl;
        private String dir;
    }



    @Data
    public static class LocalConfig {
        private Map<String,String> uploadPath;
        private String publicUrl;
        private String dir;
    }

}
