package com.may.meta_study.mapper;

import com.may.meta_study.entity.UserAgentChatMemory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户与agent聊天记录Mapper接口
 */
@Mapper
public interface UserAgentChatMemoryMapper {
    
    /**
     * 根据memoryId查询聊天记录
     * 
     * @param memoryId 记忆ID
     * @return 聊天记录
     */
    @Select("SELECT * FROM user_agent_chat_memory WHERE memory_id = #{memoryId}")
    UserAgentChatMemory findByMemoryId(@Param("memoryId") String memoryId);
    
    /**
     * 插入聊天记录
     * 
     * @param chatMemory 聊天记录
     * @return 影响行数
     */
    @Insert("INSERT INTO user_agent_chat_memory (user_id, description, updated_at) VALUES (#{userId}, #{description}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "memoryId")
    int insert(UserAgentChatMemory chatMemory);
    
    /**
     * 更新聊天记录
     * 
     * @param chatMemory 聊天记录
     * @return 影响行数
     */
    @Update("UPDATE user_agent_chat_memory SET user_id = #{userId}, description = #{description}, updated_at = #{updatedAt} WHERE memory_id = #{memoryId}")
    int update(UserAgentChatMemory chatMemory);
    
    /**
     * 根据userId查询聊天记录列表
     * 
     * @param userId 用户ID
     * @return 聊天记录列表
     */
    @Select("SELECT * FROM user_agent_chat_memory WHERE user_id = #{userId} ORDER BY updated_at DESC")
    List<UserAgentChatMemory> findByUserId(@Param("userId") Long userId);
}