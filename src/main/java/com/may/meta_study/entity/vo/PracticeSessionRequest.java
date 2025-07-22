package com.may.meta_study.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 练习会话请求参数
 */
@Data
@Schema(description = "练习会话请求参数")
public class PracticeSessionRequest {
    
    /**
     * 学生ID
     */
    @Schema(description = "学生ID", required = true)
    private Long studentId;
    
    /**
     * 课程ID
     */
    @Schema(description = "课程ID", required = true)
    private Long courseId;
}