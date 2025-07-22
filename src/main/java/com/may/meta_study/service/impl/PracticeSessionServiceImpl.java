package com.may.meta_study.service.impl;

import com.may.meta_study.assistant.MetaAgent;
import com.may.meta_study.entity.Course;
import com.may.meta_study.entity.PracticeSession;
import com.may.meta_study.mapper.CourseMapper;
import com.may.meta_study.mapper.PracticeSessionMapper;
import com.may.meta_study.service.IPracticeSessionService;
import com.may.meta_study.service.IUserAgentChatMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 练习会话服务实现类
 */
@Service
public class PracticeSessionServiceImpl implements IPracticeSessionService {
    
    @Autowired
    private PracticeSessionMapper practiceSessionMapper;
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private MetaAgent metaAgent;
    
    @Autowired
    private IUserAgentChatMemoryService userAgentChatMemoryService;
    
    @Override
    public PracticeSession createPracticeSession(Long studentId, Long courseId) {
        // 获取课程信息
        Course course = courseMapper.getById(courseId);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        
        // 获取课程相关信息
        String knowledgePoint = course.getKnowledgePoint();
        String subject = course.getSubject();
        String courseName = course.getCoursesName();
        
        // 创建一个新的聊天记忆ID
        Long memoryId = System.currentTimeMillis();
        
        // 保存聊天记忆
        String description = "学生练习：" + courseName + " - " + knowledgePoint;
        userAgentChatMemoryService.saveOrUpdateChatMemory(memoryId.toString(), studentId, description);
        
        // 构建提问消息
        String message = String.format("请根据以下信息生成一份试卷，包含答案和解析：\n学科：%s\n课程：%s\n知识点：%s",
                subject, courseName, knowledgePoint);
        
        // 使用MetaAgent生成练习题
        AtomicReference<String> responseRef = new AtomicReference<>("");
        
        // 订阅并收集结果
        metaAgent.chat(memoryId, message)
                .subscribe(chunk -> {
                    String current = responseRef.get();
                    responseRef.set(current + chunk);
                });
        
        // 创建练习会话
        PracticeSession practiceSession = new PracticeSession();
        practiceSession.setUserId(studentId);
        practiceSession.setCoursesId(courseId);
        practiceSession.setBody(responseRef.get());
        
        // 保存到数据库
        practiceSessionMapper.createPracticeSession(practiceSession);
        
        return practiceSession;
    }
    
    @Override
    public List<PracticeSession> getPracticeSessionList(Long studentId, Long courseId) {
        return practiceSessionMapper.findByUserIdAndCoursesId(studentId, courseId);
    }
    
    @Override
    public PracticeSession getPracticeSessionById(Long practiceSessionId) {
        return practiceSessionMapper.findById(practiceSessionId);
    }
    
    /**
     * 响应式创建练习会话
     * 使用响应式编程处理MetaAgent的响应流
     *
     * @param studentId 学生ID
     * @param courseId  课程ID
     * @return Mono<PracticeSession> 响应式练习会话对象
     */
    @Override
    public Mono<PracticeSession> createPracticeSessionReactive(Long studentId, Long courseId) {
        // 获取课程信息
        Course course = courseMapper.getById(courseId);
        if (course == null) {
            return Mono.error(new RuntimeException("课程不存在"));
        }
        
        // 获取课程相关信息
        String knowledgePoint = course.getKnowledgePoint();
        String subject = course.getSubject();
        String courseName = course.getCoursesName();
        
        // 创建一个新的聊天记忆ID
        Long memoryId = System.currentTimeMillis();
        
        // 保存聊天记忆
        String description = "学生练习：" + courseName + " - " + knowledgePoint;
        userAgentChatMemoryService.saveOrUpdateChatMemory(memoryId.toString(), studentId, description);
        
        // 构建提问消息
        String message = String.format("请根据以下信息生成3道练习题，包含答案和解析：\n学科：%s\n课程：%s\n知识点：%s", 
                subject, courseName, knowledgePoint);
        
        // 使用MetaAgent生成练习题
        Flux<String> responseFlux = metaAgent.chat(memoryId, message);
        
        return responseFlux
                .reduce(new StringBuilder(), (sb, chunk) -> sb.append(chunk))
                .map(StringBuilder::toString)
                .map(content -> {
                    // 创建练习会话
                    PracticeSession practiceSession = new PracticeSession();
                    practiceSession.setUserId(studentId);
                    practiceSession.setCoursesId(courseId);
                    practiceSession.setBody(content);
                    
                    // 保存到数据库
                    practiceSessionMapper.createPracticeSession(practiceSession);
                    
                    return practiceSession;
                });
    }
}