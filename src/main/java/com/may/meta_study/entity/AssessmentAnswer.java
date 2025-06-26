package com.may.meta_study.entity;

import lombok.Data;

/**
 * 标准答案实体类
 */
@Data
public class AssessmentAnswer {
    
    /**
     * 答案ID
     */
    private Long assessmentAnswersId;
    
    /**
     * 考核ID
     */
    private Long assessmentId;
    
    /**
     * 答案内容 (text/code)
     */
    private String answerBody;
}