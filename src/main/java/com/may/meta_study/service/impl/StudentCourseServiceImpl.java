package com.may.meta_study.service.impl;

import com.may.meta_study.entity.StudentCourse;
import com.may.meta_study.mapper.CourseMapper;
import com.may.meta_study.mapper.StudentCourseMapper;
import com.may.meta_study.mapper.UserMapper;
import com.may.meta_study.service.IStudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生课程关联服务实现类
 */
@Service
public class StudentCourseServiceImpl implements IStudentCourseService {
    
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    
    /**
     * 学生选课
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 是否选课成功
     */
    @Override
    public boolean enrollCourse(Long studentId, Long courseId) {
        //获取用户姓名
        String studentName = userMapper.getUserName(studentId);
        //获取课程名
        String courseName = courseMapper.getCourseName(courseId);

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId(studentId);
        studentCourse.setCoursesId(courseId);
        studentCourse.setStudentName(studentName);
        studentCourse.setCoursesName(courseName);
        
        return studentCourseMapper.addStudentCourse(studentCourse) > 0;
    }
}