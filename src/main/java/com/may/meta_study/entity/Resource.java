package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 教师教学资料实体类
 */
@Data
public class Resource {
    
    /**
     * 资源ID
     */
    private Long resourcesId;
    
    /**
     * 课程ID
     */
    private Long courseId;
    
    /**
     * 上传者ID
     */
    private Long uploaderId;
    
    /**
     * 资源类型 (ppt/pdf/code)
     */
    private String resourceType;
    
    /**
     * 文件路径
     */
    private String filePath;
    
    /**
     * 上传时间
     */
    private LocalDateTime uploadedTime;
}