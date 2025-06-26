package com.may.meta_study.controller;

import com.may.meta_study.assistant.MetaAgent;
import com.may.meta_study.entity.vo.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Meta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Meta_study")
@RestController
@RequestMapping("/api/v1")
public class MetaController {
    @Autowired
    private MetaAgent metaAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return metaAgent.chat(chatForm.getMemoryId(),chatForm.getMessage());
    }
}

