package com.may.meta_study.entity;

import lombok.Data;

/**
 * 自动批改结果实体类
 */
@Data
public class SubmissionFeedback {
    
    /**
     * 反馈ID
     */
    private Long submissionFeedbacksId;
    
    /**
     * 学生提交ID
     */
    private Long studentSubmissionsId;
    
    /**
     * 评分
     */
    private Double grade;
    
    /**
     * 错误报告
     */
    private String errorReporting;
}