package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统使用日志实体类
 */
@Data
public class UsageLog {
    
    /**
     * 日志ID
     */
    private Long usageLogsId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 操作类型 (e.g. "generate_content", "submit_assessment")
     */
    private String action;
    
    /**
     * 操作时间
     */
    private LocalDateTime actionTime;
}