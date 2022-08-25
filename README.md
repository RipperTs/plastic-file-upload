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

#### 使用

将 `com.upload.servlet`包直接复制到你的项目目录下然后在`yaml`中添加相应配置即可,调用方式demo

```java
    @PostMapping("/upload")
public UploadEntity upload(MultipartFile file,String batchId,String isPublic)throws IOException{
        if(StrUtil.isEmpty(batchId)){
        batchId=IdUtil.simpleUUID();
        }
        // 雪花算法生成唯一ID
        Snowflake snowflake=IdUtil.getSnowflake(1,1);
        UploadEntity entity=new UploadEntity(snowflake.nextId()+"",file.getOriginalFilename(),file.getSize(),batchId,isPublic);
        entity=FileServiceFactory.build().uploadFile(file.getInputStream(),entity);
        return entity;
        }
```

#### 依赖

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<!--oss文件上传-->
<dependency>
<groupId>com.aliyun.oss</groupId>
<artifactId>aliyun-sdk-oss</artifactId>
<version>3.8.0</version>
</dependency>
<dependency>
<groupId>com.aliyun</groupId>
<artifactId>aliyun-java-sdk-core</artifactId>
<version>4.1.0</version>
</dependency>
<!--腾讯云文件上传-->
<dependency>
<groupId>com.qcloud</groupId>
<artifactId>cos_api</artifactId>
<version>5.6.24</version>
</dependency>
<!--七牛云-->
<dependency>
<groupId>com.qiniu</groupId>
<artifactId>qiniu-java-sdk</artifactId>
<version>7.3.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp -->
<dependency>
<groupId>com.squareup.okhttp3</groupId>
<artifactId>okhttp</artifactId>
<version>4.10.0</version>
</dependency>
<!--引入Knife4j的官方start包,Swagger2基于Springfox2.10.5项目-->
<dependency>
<groupId>com.github.xiaoymin</groupId>
<!--使用Swagger2-->
<artifactId>knife4j-spring-boot-starter</artifactId>
<version>3.0.3</version>
</dependency>
<!--工具包-->
<dependency>
<groupId>cn.hutool</groupId>
<artifactId>hutool-all</artifactId>
<version>5.8.5</version>
</dependency>
<!--   FileUtils     -->
<dependency>
<groupId>commons-io</groupId>
<artifactId>commons-io</artifactId>
<version>2.11.0</version>
</dependency>
<!--   Configuring the Annotation Processor     -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-configuration-processor</artifactId>
<optional>true</optional>
</dependency>
```

### 注意

本地上传可能找不到路径 具体需要改下spring boot的静态资源配置  
同时因考虑到有本地上传方式,未封装maven包,有兴趣的自己尝试下  