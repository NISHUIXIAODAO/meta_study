package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程信息实体类
 */
@Data
public class Course {
    
    /**
     * 课程ID
     */
    private Long coursesId;
    
    /**
     * 课程名称
     */
    private String coursesName;
    
    /**
     * 学科
     */
    private String subject;
    
    /**
     * 课程描述
     */
    private String description;
    
    /**
     * 教学大纲文档路径
     */
    private String syllabusDocPath;
    
    /**
     * 教师ID
     */
    private Long teacherId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 知识点
     */
    private String knowledgePoint;
}