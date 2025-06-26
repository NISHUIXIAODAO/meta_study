package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生提交实体类
 */
@Data
public class StudentSubmission {
    
    /**
     * 提交ID
     */
    private Long studentSubmissionsId;
    
    /**
     * 考核ID
     */
    private Long assessmentId;
    
    /**
     * 学生ID
     */
    private Long studentId;
    
    /**
     * 提交内容
     */
    private String submitBody;
    
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    
    /**
     * 运行结果 (JSON)
     */
    private String runResult;
}