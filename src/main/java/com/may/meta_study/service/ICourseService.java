package com.may.meta_study.service;

import com.may.meta_study.entity.Course;

import java.util.List;

/**
 * 课程服务接口
 */
public interface ICourseService {
    
    /**
     * 获取学生课程列表
     *
     * @param studentId 学生ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程列表
     */
    List<Course> getStudentCourseList(Long studentId, long page, long size);
    
    /**
     * 获取学生课程总数
     *
     * @param studentId 学生ID
     * @return 课程总数
     */
    int getStudentCourseCount(Long studentId);
    
    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程信息
     */
    Course getById(Long id);
    
    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 创建的课程信息
     */
    Course createCourse(Course course);
    
    /**
     * 获取教师课程列表
     *
     * @param teacherId 教师ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程列表
     */
    List<Course> getTeacherCourseList(Long teacherId, long page, long size);
    
    /**
     * 获取教师课程总数
     *
     * @param teacherId 教师ID
     * @return 课程总数
     */
    int getTeacherCourseCount(Long teacherId);
}