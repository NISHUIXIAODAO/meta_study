package com.may.meta_study.entity;

import lombok.Data;

/**
 * 学生练习题目实体类
 */
@Data
public class PracticeSession {
    
    /**
     * 练习会话ID
     */
    private Long practiceSessionsId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 课程ID
     */
    private Long coursesId;
    
    /**
     * 练习内容
     */
    private String body;
}