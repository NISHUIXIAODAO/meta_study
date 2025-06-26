package com.may.meta_study.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息实体类
 */
@Data
public class User {
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码哈希
     */
    private String passwordHash;
    
    /**
     * 角色（admin/teacher/student）
     */
    private String role;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}