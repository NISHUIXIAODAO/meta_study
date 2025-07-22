package com.may.meta_study.mapper;

import com.may.meta_study.entity.StudentCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学生课程关联Mapper接口
 */
@Mapper
public interface StudentCourseMapper {
    
    /**
     * 添加学生课程关联
     *
     * @param studentCourse 学生课程关联信息
     * @return 影响行数
     */
    @Insert("INSERT INTO student_courses (student_id, courses_id, student_name, courses_name) VALUES (#{studentId}, #{coursesId}, #{studentName}, #{coursesName})")
    int addStudentCourse(StudentCourse studentCourse);
}