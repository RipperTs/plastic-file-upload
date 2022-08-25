package com.upload.servlet.upload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传文件entity对象
 *
 */
@Data
public class UploadEntity {

    public UploadEntity() {

    }

    public UploadEntity(String fileId, String name, Long size, String batchId,String isPublic) {
        this.fileId = fileId;
        this.name = name;
        this.size = size;
        this.batchId = batchId;
        this.isPublic = isPublic;
    }

    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID")
    private String fileId;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Long size;

    /**
     * 批次ID
     */
    @ApiModelProperty(value = "批次ID")
    private String batchId;

    /**
     * 上传类型
     */
    @ApiModelProperty(value = "上传类型")
    private Integer type;

    /**
     * 上传路径
     */
    @ApiModelProperty(value = "上传路径")
    private String path;

    @ApiModelProperty(value = "文件访问地址(含域名)")
    private String url;

    @ApiModelProperty(value = "无前缀的url地址")
    private String notPrefixUrl;

    @ApiModelProperty(value = "是否公开")
    private String isPublic;

    public String getUrl() {
        if ("1".equals(isPublic)){
            return path;
        }else {
            return "/files/down/" + fileId;
        }
    }

    public void setUrl(String url) {
        this.path = url;
    }

    public void setPath(String path) {
        this.setNotPrefixUrl(path);
        this.path = path;
    }

    public void setNotPrefixUrl(String notPrefixUrl) {
        String pattern = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(notPrefixUrl);
        if (m.find()) {
            //匹配结果
            System.out.println("=" + m.group());
        }
        //替换
        this.notPrefixUrl =  notPrefixUrl.replaceAll(pattern, "");
    }
}
