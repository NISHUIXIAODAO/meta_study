package com.may.meta_study.mapper;

import com.may.meta_study.entity.Course;
import dev.langchain4j.agent.tool.P;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 课程Mapper接口
 */
@Mapper
public interface CourseMapper {
    
    /**
     * 分页查询学生的课程列表
     *
     * @param studentId 学生ID
     * @return 课程列表
     */
    @Select("SELECT c.* FROM courses c INNER JOIN student_courses sc ON c.courses_id = sc.courses_id WHERE sc.student_id = #{studentId}")
    List<Course> getStudentCourseList(@Param("studentId") Long studentId);
    
    /**
     * 获取学生课程总数
     *
     * @param studentId 学生ID
     * @return 课程总数
     */
    @Select("SELECT COUNT(*) FROM courses c INNER JOIN student_courses sc ON c.courses_id = sc.courses_id WHERE sc.student_id = #{studentId}")
    int getStudentCourseCount(@Param("studentId") Long studentId);
    
    /**
     * 根据ID查询课程
     *
     * @param id 课程ID
     * @return 课程信息
     */
    @Select("SELECT * FROM courses WHERE courses_id = #{id}")
    Course getById(@Param("id") Long id);
    
    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 影响行数
     */
    @Insert("INSERT INTO courses(courses_name, subject, description, teacher_id, knowledge_point, created_at) "
            + "VALUES(#{coursesName}, #{subject}, #{description}, #{teacherId}, #{knowledgePoint}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "coursesId")
    int createCourse(Course course);
    
    /**
     * 获取教师课程列表
     *
     * @param teacherId 教师ID
     * @return 课程列表
     */
    @Select("SELECT * FROM courses WHERE teacher_id = #{teacherId}")
    List<Course> getTeacherCourseList(@Param("teacherId") Long teacherId);
    
    /**
     * 获取教师课程总数
     *
     * @param teacherId 教师ID
     * @return 课程总数
     */
    @Select("SELECT COUNT(*) FROM courses WHERE teacher_id = #{teacherId}")
    int getTeacherCourseCount(@Param("teacherId") Long teacherId);

    @Select("select courses_name from courses where courses_id = #{coursesId}")
    String getCourseName(@Param("coursesId") Long courseId);
}