package com.may.meta_study.tools.test;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

/**
 * 文本处理工具类
 * 提供各种文本处理功能供大模型调用
 */
@Component
public class TextProcessingTools {

    /**
     * 文本转换为大写
     * 
     * @param memoryId 会话ID
     * @param text 需要转换的文本
     * @return 转换后的大写文本
     */
    @Tool(name = "文本大写转换", value = "将输入的文本转换为大写形式")
    public String toUpperCase(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text
    ) {
        System.out.println("调用文本大写转换 " + memoryId);
        return text.toUpperCase();
    }

    /**
     * 文本转换为小写
     * 
     * @param memoryId 会话ID
     * @param text 需要转换的文本
     * @return 转换后的小写文本
     */
    @Tool(name = "文本小写转换", value = "将输入的文本转换为小写形式")
    public String toLowerCase(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text
    ) {
        System.out.println("调用文本小写转换 " + memoryId);
        return text.toLowerCase();
    }

    /**
     * 计算文本长度
     * 
     * @param memoryId 会话ID
     * @param text 需要计算长度的文本
     * @return 文本的字符长度
     */
    @Tool(name = "文本长度计算", value = "计算输入文本的字符长度")
    public int textLength(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text
    ) {
        System.out.println("调用文本长度计算 " + memoryId);
        return text.length();
    }

    /**
     * 文本关键词检测
     * 
     * @param memoryId 会话ID
     * @param text 需要检测的文本
     * @param keyword 需要检测的关键词
     * @return 是否包含关键词
     */
    @Tool(name = "关键词检测", value = "检测文本中是否包含指定的关键词")
    public boolean containsKeyword(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text,
            @P(value = "关键词", required = true) String keyword
    ) {
        System.out.println("调用关键词检测 " + memoryId);
        return text.contains(keyword);
    }

    /**
     * 文本分割
     * 
     * @param memoryId 会话ID
     * @param text 需要分割的文本
     * @param delimiter 分隔符
     * @return 分割后的文本数组描述
     */
    @Tool(name = "文本分割", value = "使用指定的分隔符将文本分割成多个部分")
    public String splitText(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text,
            @P(value = "分隔符", required = true) String delimiter
    ) {
        System.out.println("调用文本分割 " + memoryId);
        String[] parts = text.split(delimiter);
        StringBuilder result = new StringBuilder("分割结果包含 " + parts.length + " 个部分:\n");
        
        for (int i = 0; i < parts.length; i++) {
            result.append(i + 1).append(". \"").append(parts[i]).append("\"\n");
        }
        
        return result.toString();
    }
}