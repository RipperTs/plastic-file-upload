package com.upload.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.upload.servlet.upload.FileServiceFactory;
import com.upload.servlet.upload.UploadEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = "文件上传模块")
public class UploadController {

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public UploadEntity upload(@RequestPart @RequestParam("file") MultipartFile file,
                               @ApiParam("文件ID,不传为速记一个uuid") @RequestParam(required = false) String batchId,
                               @ApiParam("是否公开 1公开 0私有") @RequestParam(required = false, defaultValue = "1") String isPublic) throws IOException {
        if (StrUtil.isEmpty(batchId)) {
            batchId = IdUtil.simpleUUID();
        }
        // 雪花算法生成唯一ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        UploadEntity entity = new UploadEntity(snowflake.nextId() + "", file.getOriginalFilename(), file.getSize(), batchId, isPublic);
        entity = FileServiceFactory.build().uploadFile(file.getInputStream(), entity);
        return entity;
    }
}
