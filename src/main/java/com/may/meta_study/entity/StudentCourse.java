package com.may.meta_study.entity;

import lombok.Data;

/**
 * 学生课程关联实体类
 */
@Data
public class StudentCourse {
    
    /**
     * 学生ID
     */
    private Long studentId;
    
    /**
     * 课程ID
     */
    private Long coursesId;
    
    /**
     * 学生姓名
     */
    private String studentName;
    
    /**
     * 课程名称
     */
    private String coursesName;
}