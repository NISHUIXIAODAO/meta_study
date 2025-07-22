package com.may.meta_study.mapper;

import com.may.meta_study.entity.PracticeSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 练习会话Mapper接口
 */
@Mapper
public interface PracticeSessionMapper {
    
    /**
     * 创建练习会话
     *
     * @param practiceSession 练习会话信息
     * @return 影响行数
     */
    @Insert("INSERT INTO practice_sessions (user_id, courses_id, body) VALUES (#{userId}, #{coursesId}, #{body})")
    @Options(useGeneratedKeys = true, keyProperty = "practiceSessionsId")
    int createPracticeSession(PracticeSession practiceSession);
    
    /**
     * 根据学生ID和课程ID查询练习会话列表
     *
     * @param userId 学生ID
     * @param coursesId 课程ID
     * @return 练习会话列表
     */
    @Select("SELECT * FROM practice_sessions WHERE user_id = #{userId} AND courses_id = #{coursesId} ORDER BY practice_sessions_id DESC")
    List<PracticeSession> findByUserIdAndCoursesId(@Param("userId") Long userId, @Param("coursesId") Long coursesId);
    
    /**
     * 根据ID查询练习会话
     *
     * @param practiceSessionsId 练习会话ID
     * @return 练习会话
     */
    @Select("SELECT * FROM practice_sessions WHERE practice_sessions_id = #{practiceSessionsId}")
    PracticeSession findById(@Param("practiceSessionsId") Long practiceSessionsId);
}