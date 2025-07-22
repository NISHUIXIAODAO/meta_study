package com.may.meta_study.service;

import com.may.meta_study.entity.PracticeSession;

import java.util.List;
import reactor.core.publisher.Mono;

/**
 * 练习会话服务接口
 */
public interface IPracticeSessionService {
    
    /**
     * 创建练习会话
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 创建的练习会话
     */
    PracticeSession createPracticeSession(Long studentId, Long courseId);
    
    /**
     * 根据学生ID和课程ID查询练习会话列表
     *
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 练习会话列表
     */
    List<PracticeSession> getPracticeSessionList(Long studentId, Long courseId);
    
    /**
     * 根据ID查询练习会话
     *
     * @param practiceSessionId 练习会话ID
     * @return 练习会话
     */
    PracticeSession getPracticeSessionById(Long practiceSessionId);
    
    /**
     * 响应式创建练习会话
     * 使用响应式编程处理MetaAgent的响应流
     *
     * @param studentId 学生ID
     * @param courseId  课程ID
     * @return Mono<PracticeSession> 响应式练习会话对象
     */
    Mono<PracticeSession> createPracticeSessionReactive(Long studentId, Long courseId);
}