package com.upload.servlet.upload;


import com.upload.servlet.ApplicationContextHolder;

/**
 * 文件类服务工厂
 */
public class FileServiceFactory {

    public static FileService build() {
        UploadConfig uploadConfig = ApplicationContextHolder.getBean(UploadConfig.class);
        Integer config = uploadConfig.getConfig();

        if (config.equals(UploadFileEnum.ALI_OSS.getConfig())) {
            return new OssFileServiceImpl(uploadConfig.getOss());
        } else if (config.equals(UploadFileEnum.LOCAL.getConfig())) {
            return new LocalFileServiceImpl(uploadConfig.getLocal());
        }else if(config.equals(UploadFileEnum.ALI_COS.getConfig())){
            return new TencentFileServiceImpl(uploadConfig.getCos());
        }else if(config.equals(UploadFileEnum.ALI_QNC.getConfig())){
            return new QncFileServiceImpl(uploadConfig.getQnc());
        }
        return new LocalFileServiceImpl(uploadConfig.getLocal());
    }

}
