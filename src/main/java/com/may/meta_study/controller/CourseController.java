package com.may.meta_study.controller;

import com.may.meta_study.entity.Course;
import com.may.meta_study.entity.vo.EnrollCourseRequest;
import com.may.meta_study.service.ICourseService;
import com.may.meta_study.service.IStudentCourseService;
import com.may.meta_study.entity.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程控制器
 */
@Tag(name = "课程管理", description = "课程相关接口")
@RestController
@RequestMapping("/api/v1")
public class CourseController {
    
    @Autowired
    private ICourseService courseService;
    
    @Autowired
    private IStudentCourseService studentCourseService;
    
    /**
     * 获取学生课程列表
     *
     * @param studentId 学生ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程分页列表
     */
    @Operation(summary = "获取学生课程列表")
    @GetMapping("/courses/student/list")
    public Result<Map<String, Object>> getStudentCourseList(
            @Parameter(description = "学生ID") @RequestParam Long studentId,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") long page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") long size) {
        List<Course> courseList = courseService.getStudentCourseList(studentId, page, size);
        int total = courseService.getStudentCourseCount(studentId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", courseList);
        result.put("total", total);
        result.put("size", size);
        result.put("current", page);
        result.put("pages", (total + size - 1) / size);
        
        return Result.success(result);
    }
    
    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 创建的课程信息
     */
    @Operation(summary = "创建课程")
    @PostMapping("/teacher/course")
    public Result<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return Result.success(createdCourse);
        } catch (Exception e) {
            return Result.fail("创建课程失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取教师课程列表
     *
     * @param teacherId 教师ID
     * @param page 页码
     * @param size 每页大小
     * @return 课程分页列表
     */
    @Operation(summary = "获取教师课程列表")
    @GetMapping("/course/teacher/list")
    public Result<Map<String, Object>> getTeacherCourseList(
            @Parameter(description = "教师ID") @RequestParam Long teacherId,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") long page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") long size) {
        List<Course> courseList = courseService.getTeacherCourseList(teacherId, page, size);
        int total = courseService.getTeacherCourseCount(teacherId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", courseList);
        result.put("total", total);
        result.put("size", size);
        result.put("current", page);
        result.put("pages", (total + size - 1) / size);
        
        return Result.success(result);
    }
    
    /**
     * 获取课程详情
     *
     * @param courseId 课程ID
     * @return 课程详情
     */
    @Operation(summary = "获取课程详情")
    @GetMapping("/course")
    public Result<Course> getCourseDetail(
            @Parameter(description = "课程ID") @RequestParam Long courseId) {
        try {
            Course course = courseService.getById(courseId);
            if (course == null) {
                return Result.fail("课程不存在");
            }
            return Result.success(course);
        } catch (Exception e) {
            return Result.fail("获取课程详情失败：" + e.getMessage());
        }
    }
    
    /**
     * 学生选课
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 选课结果
     */
    @Operation(summary = "学生选课")
    @PostMapping("/courses/student")
    public Result<Boolean> enrollCourse(@RequestBody EnrollCourseRequest enrollCourse) {
        try {
            // 获取课程信息
            Course course = courseService.getById(enrollCourse.getCourseId());
            if (course == null) {
                return Result.fail("课程不存在");
            }
            // 调用选课服务
            boolean success = studentCourseService.enrollCourse(enrollCourse.getStudentId(), enrollCourse.getCourseId());
            if (success) {
                return Result.success(true);
            } else {
                return Result.fail("选课失败");
            }
        } catch (Exception e) {
            return Result.fail("选课失败：" + e.getMessage());
        }
    }
}