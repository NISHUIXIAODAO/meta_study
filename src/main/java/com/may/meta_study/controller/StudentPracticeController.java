package com.may.meta_study.controller;

import com.may.meta_study.entity.PracticeSession;
import com.may.meta_study.entity.vo.PracticeSessionRequest;
import com.may.meta_study.entity.vo.Result;
import com.may.meta_study.service.IPracticeSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import reactor.core.publisher.Mono;

/**
 * 学生练习控制器
 */
@Tag(name = "学生练习", description = "学生练习相关接口")
@RestController
@RequestMapping("/api/v1/student")
public class StudentPracticeController {
    
    @Autowired
    private IPracticeSessionService practiceSessionService;
    
    /**
     * 开启实时练习会话，生成练习题
     *
     * @param request 请求参数，包含studentId和courseId
     * @return 创建的练习会话
     */
    @Operation(summary = "开启实时练习会话，生成练习题")
    @PostMapping("/chat/practice/session")
    public Result<PracticeSession> createPracticeSession(@RequestBody PracticeSessionRequest request) {
        try {
            // 验证参数
            if (request.getStudentId() == null || request.getCourseId() == null) {
                return Result.fail("学生ID和课程ID不能为空");
            }
            
            // 创建练习会话
            PracticeSession practiceSession = practiceSessionService.createPracticeSession(
                    request.getStudentId(), request.getCourseId());
            
            return Result.success(practiceSession);
        } catch (Exception e) {
            return Result.fail("创建练习会话失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取学生的练习会话列表
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 练习会话列表
     */
    @Operation(summary = "获取学生的练习会话列表")
    @GetMapping("/practice/sessions")
    public Result<List<PracticeSession>> getPracticeSessionList(
            @Parameter(description = "学生ID") @RequestParam Long studentId,
            @Parameter(description = "课程ID") @RequestParam Long courseId) {
        try {
            List<PracticeSession> sessions = practiceSessionService.getPracticeSessionList(studentId, courseId);
            return Result.success(sessions);
        } catch (Exception e) {
            return Result.fail("获取练习会话列表失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取练习会话详情
     *
     * @param sessionId 会话ID
     * @return 练习会话详情
     */
    @Operation(summary = "获取练习会话详情")
    @GetMapping("/practice/session")
    public Result<PracticeSession> getPracticeSessionDetail(
            @Parameter(description = "会话ID") @RequestParam Long sessionId) {
        try {
            PracticeSession session = practiceSessionService.getPracticeSessionById(sessionId);
            if (session == null) {
                return Result.fail("练习会话不存在");
            }
            return Result.success(session);
        } catch (Exception e) {
            return Result.fail("获取练习会话详情失败：" + e.getMessage());
        }
    }
    
    /**
     * 响应式开启实时练习会话，生成练习题
     * 使用响应式编程处理MetaAgent的响应流
     *
     * @param request 请求参数，包含studentId和courseId
     * @return 创建的练习会话（响应式）
     */
    @Operation(summary = "响应式开启实时练习会话，生成练习题")
    @PostMapping("/chat/practice/session/reactive")
    public Mono<Result<PracticeSession>> createPracticeSessionReactive(@RequestBody PracticeSessionRequest request) {
        // 验证参数
        if (request.getStudentId() == null || request.getCourseId() == null) {
            return Mono.just(Result.fail("学生ID和课程ID不能为空"));
        }
        
        // 创建响应式练习会话
        return practiceSessionService.createPracticeSessionReactive(
                request.getStudentId(), request.getCourseId())
                .map(Result::success)
                .onErrorResume(e -> Mono.just(Result.fail("创建练习会话失败：" + e.getMessage())));
    }
}