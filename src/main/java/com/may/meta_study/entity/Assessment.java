package com.may.meta_study.entity;

import lombok.Data;

/**
 * 课程教学考核实体类
 */
@Data
public class Assessment {
    
    /**
     * 考核ID
     */
    private Long assessmentsId;
    
    /**
     * 考核名称
     */
    private String assessmentsName;
    
    /**
     * 教学内容ID
     */
    private Long teachingContentId;
    
    /**
     * 类型（choice/programming/...）
     */
    private String type;
    
    /**
     * 描述
     */
    private String description;
}