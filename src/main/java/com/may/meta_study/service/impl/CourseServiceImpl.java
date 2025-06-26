package com.may.meta_study.service.impl;

import com.may.meta_study.entity.Course;
import com.may.meta_study.mapper.CourseMapper;
import com.may.meta_study.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程服务实现类
 */
@Service
public class CourseServiceImpl implements ICourseService {
    
    @Autowired
    private CourseMapper courseMapper;
    
    /**
     * 获取学生课程列表
     *
     * @param studentId 学生ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程列表
     */
    @Override
    public List<Course> getStudentCourseList(Long studentId, long page, long size) {
        // 计算偏移量
        long offset = (page - 1) * size;
        // 使用PageHelper进行分页
        com.github.pagehelper.Page<Course> pageResult = com.github.pagehelper.PageHelper.offsetPage((int)offset, (int)size)
                .doSelectPage(() -> courseMapper.getStudentCourseList(studentId));
        return pageResult;
    }
    
    /**
     * 获取学生课程总数
     *
     * @param studentId 学生ID
     * @return 课程总数
     */
    @Override
    public int getStudentCourseCount(Long studentId) {
        return courseMapper.getStudentCourseCount(studentId);
    }
    
    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程信息
     */
    @Override
    public Course getById(Long id) {
        return courseMapper.getById(id);
    }
    
    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 创建的课程信息
     */
    @Override
    public Course createCourse(Course course) {
        // 设置创建时间
        course.setCreatedAt(LocalDateTime.now());
        // 创建课程
        courseMapper.createCourse(course);
        // 返回创建后的课程信息（包含自动生成的ID）
        return course;
    }
    
    /**
     * 获取教师课程列表
     *
     * @param teacherId 教师ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程列表
     */
    @Override
    public List<Course> getTeacherCourseList(Long teacherId, long page, long size) {
        // 计算偏移量
        long offset = (page - 1) * size;
        // 使用PageHelper进行分页
        com.github.pagehelper.Page<Course> pageResult = com.github.pagehelper.PageHelper.offsetPage((int)offset, (int)size)
                .doSelectPage(() -> courseMapper.getTeacherCourseList(teacherId));
        return pageResult;
    }
    
    /**
     * 获取教师课程总数
     *
     * @param teacherId 教师ID
     * @return 课程总数
     */
    @Override
    public int getTeacherCourseCount(Long teacherId) {
        return courseMapper.getTeacherCourseCount(teacherId);
    }
}