package com.may.meta_study.entity;

import lombok.Data;

/**
 * 学生错题本实体类
 */
@Data
public class StudentQuestion {
    
    /**
     * 错题ID
     */
    private Long studentQuestionsId;
    
    /**
     * 学生ID
     */
    private Long studentId;
    
    /**
     * 问题ID（关联到assessments_id或teaching_cotents_id）
     */
    private Long questionsId;
    
    /**
     * 问题快照 (JSON)
     */
    private String questionsSnapshot;
    
    /**
     * 学生答案
     */
    private String studentAnswer;
    
    /**
     * 正确答案
     */
    private String questionAnswer;
    
    /**
     * 备注
     */
    private String notes;
}