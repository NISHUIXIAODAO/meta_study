package com.may.meta_study.controller;

import com.may.meta_study.assistant.MetaAgent;
import com.may.meta_study.entity.UserAgentChatMemory;
import com.may.meta_study.entity.vo.ChatForm;
import com.may.meta_study.service.IUserAgentChatMemoryService;
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
import reactor.core.publisher.Flux;

import java.util.List;

@Tag(name = "Meta_study")
@RestController
@RequestMapping("/api/v1")
public class MetaController {
    @Autowired
    private MetaAgent metaAgent;
    
    @Autowired
    private IUserAgentChatMemoryService userAgentChatMemoryService;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        // 将聊天记录保存到user_agent_chat_memory表中
        // 暂时将userId写死为1，description为空 todo
        userAgentChatMemoryService.saveOrUpdateChatMemory(chatForm.getMemoryId().toString(), 1L, "");
        
        return metaAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
    
    @Operation(summary = "获取聊天记录列表")
    @GetMapping("/chat/list")
    public List<UserAgentChatMemory> getChatList(
            @Parameter(description = "用户ID", required = true)
            @RequestParam("userId") Long userId) {
        // 调用服务获取用户的聊天记录列表
        return userAgentChatMemoryService.getChatMemoryListByUserId(userId);
    }
}

