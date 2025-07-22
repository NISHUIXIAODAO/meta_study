package com.may.meta_study.service;

import com.may.meta_study.entity.StudentCourse;

/**
 * 学生课程关联服务接口
 */
public interface IStudentCourseService {
    
    /**
     * 学生选课
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param studentName 学生姓名
     * @param courseName 课程名称
     * @return 是否选课成功
     */
    boolean enrollCourse(Long studentId, Long courseId);
}