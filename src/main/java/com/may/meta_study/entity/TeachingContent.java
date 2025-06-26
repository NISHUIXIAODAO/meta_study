package com.may.meta_study.entity;

import lombok.Data;

/**
 * 教学内容实体类
 */
@Data
public class TeachingContent {
    
    /**
     * 教学内容ID
     */
    private Long teachingContentsId;
    
    /**
     * 教学内容名称
     */
    private String teachingContentsName;
    
    /**
     * 课程ID
     */
    private Long courseId;
    
    /**
     * 类型（lecture/exercise）
     */
    private String type;
    
    /**
     * 内容主体 (JSON/Markdown)
     */
    private String body;
}