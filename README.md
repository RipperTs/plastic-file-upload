# 文件上传统一处理
- []: # Framework: Spring Boot 2.7.x  
- []: # Language: java  
- []: # Path: com.upload.servlet.upload  


### 配置说明
```yaml
servlet:
  upload:
    #上传类型 1 本地 2 阿里云OSS 3 腾讯云COS 4 七牛云QNC
    config: 1
    oss:
      endpoint: oss-cn-shenzhen.aliyuncs.com
      accessKeyId: w1xxxxxxxxxxxPf
      accessKeySecret: PO5xxxxxxxxxxxxxxxxxxxxTPAl
      publicUrl: https://exmplate.com/
      # 桶名称 如：bucket-name 0和1表示一个公有一个私有  可以都一样     
      bucketName:
        0: wemark
        1: wemark
      # 实际上传的路径, 注意不能以 `/` 结尾 如：upload/public     
      dir: upload/public
    local:
      publicUrl:
      uploadPath:
        0: D:/private
        1: D:/public
      dir: upload
    cos:
      region: ap-chengdu
      secretId: AKIxxxxxxxxxxxxxxxxxxxxxxxxj3J
      secretKey: kxxxxxxxxxxxxxxxxxxxWZUEnCQTM5e3ng
      publicUrl: https://image-125182919.cos.ap-chengdu.myqcloud.com/
      bucketName:
        0: image-1251212321
        1: image-1251212321
      dir: upload/public
    qnc:
      accessKey: 9gbxxxxxxxxxxxxxxxxxxxxxxxxxxxxxtPWahWvV
      secretKey: kOTmgxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxplQ5x
      publicUrl: https://oss.qiniu.emxos.com/
      privateUrl:
      bucketName:
        0: imagesall
        1: imagesall
      dir: upload/public
```

### 注意
本地上传可能找不到路径  具体需要改下spring boot的静态资源配置