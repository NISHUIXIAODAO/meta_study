package com.may.meta_study.service;

import com.may.meta_study.entity.UserAgentChatMemory;

import java.util.List;

/**
 * 用户与agent聊天记录服务接口
 */
public interface IUserAgentChatMemoryService {
    
    /**
     * 保存或更新用户与agent的聊天记录
     * 
     * @param memoryId 记忆ID
     * @param userId 用户ID
     * @param description 描述
     * @return 保存或更新后的聊天记录
     */
    UserAgentChatMemory  saveOrUpdateChatMemory(String memoryId, Long userId, String description);
    
    /**
     * 获取用户与agent的聊天记录列表
     * 
     * @param userId 用户ID
     * @return 聊天记录列表
     */
    List<UserAgentChatMemory> getChatMemoryListByUserId(Long userId);
}