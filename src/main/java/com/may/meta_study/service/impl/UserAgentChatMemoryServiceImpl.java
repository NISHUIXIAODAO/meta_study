package com.may.meta_study.service.impl;

import com.may.meta_study.entity.UserAgentChatMemory;
import com.may.meta_study.mapper.UserAgentChatMemoryMapper;
import com.may.meta_study.service.IUserAgentChatMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户与agent聊天记录服务实现类
 */
@Service
public class UserAgentChatMemoryServiceImpl implements IUserAgentChatMemoryService {
    
    @Autowired
    private UserAgentChatMemoryMapper userAgentChatMemoryMapper;
    
    @Override
    public UserAgentChatMemory saveOrUpdateChatMemory(String memoryId, Long userId, String description) {
        // 查询是否已存在该memoryId的记录
        UserAgentChatMemory chatMemory = userAgentChatMemoryMapper.findByMemoryId(memoryId);
        
        if (chatMemory == null) {
            // 不存在则创建新记录
            chatMemory = new UserAgentChatMemory();
            chatMemory.setUpdatedAt(LocalDateTime.now());
            chatMemory.setUserId(userId);
            chatMemory.setDescription(description);
            
            // 插入到数据库
            userAgentChatMemoryMapper.insert(chatMemory);
        } else {
            // 设置或更新字段
            chatMemory.setUserId(userId);
            chatMemory.setDescription(description);
            chatMemory.setUpdatedAt(LocalDateTime.now());
            
            // 更新到数据库
            userAgentChatMemoryMapper.update(chatMemory);
        }
        
        return chatMemory;
    }
    
    @Override
    public List<UserAgentChatMemory> getChatMemoryListByUserId(Long userId) {
        // 调用Mapper查询用户的聊天记录列表
        return userAgentChatMemoryMapper.findByUserId(userId);
    }
}