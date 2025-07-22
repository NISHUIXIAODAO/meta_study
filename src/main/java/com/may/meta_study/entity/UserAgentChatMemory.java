package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户与agent聊天记录实体类
 * 该实体类对应MySQL中的user_agent_chat_memory表
 */
@Data
public class UserAgentChatMemory {
    
    /**
     * 记录ID
     */
    private Long memoryId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}