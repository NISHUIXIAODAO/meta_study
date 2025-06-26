package com.may.meta_study.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户与agent聊天记录实体类
 * 该实体类对应MongoDB中的集合
 */
@Data
@Document(collection = "user_agent_chat_memory")
public class UserAgentChatMemory {
    
    /**
     * MongoDB文档ID
     */
    @Id
    private String id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 记忆ID
     */
    private String memoryId;
    
    /**
     * 描述
     */
    private String description;
}