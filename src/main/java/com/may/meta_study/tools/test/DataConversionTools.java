package com.may.meta_study.tools.test;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

/**
 * 数据转换工具类
 * 提供各种数据格式转换功能供大模型调用
 */
@Component
public class DataConversionTools {

    /**
     * 字符串转Base64编码
     * 
     * @param memoryId 会话ID
     * @param text 需要编码的文本
     * @return Base64编码后的字符串
     */
    @Tool(name = "Base64编码", value = "将文本转换为Base64编码")
    public String encodeBase64(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text
    ) {
        System.out.println("调用Base64编码 " + memoryId);
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64解码为字符串
     * 
     * @param memoryId 会话ID
     * @param base64Text Base64编码的字符串
     * @return 解码后的文本
     */
    @Tool(name = "Base64解码", value = "将Base64编码转换为原始文本")
    public String decodeBase64(
            @ToolMemoryId int memoryId,
            @P(value = "Base64文本", required = true) String base64Text
    ) {
        System.out.println("调用Base64解码 " + memoryId);
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Text);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return "无效的Base64编码: " + e.getMessage();
        }
    }

    /**
     * 十进制转换为其他进制
     * 
     * @param memoryId 会话ID
     * @param decimalValue 十进制数值
     * @param targetBase 目标进制（2-36）
     * @return 转换后的字符串
     */
    @Tool(name = "进制转换", value = "将十进制数值转换为指定进制")
    public String convertBase(
            @ToolMemoryId int memoryId,
            @P(value = "十进制数值", required = true) int decimalValue,
            @P(value = "目标进制", required = true) int targetBase
    ) {
        System.out.println("调用进制转换 " + memoryId);
        if (targetBase < 2 || targetBase > 36) {
            return "目标进制必须在2到36之间";
        }
        return Integer.toString(decimalValue, targetBase);
    }

    /**
     * 其他进制转换为十进制
     * 
     * @param memoryId 会话ID
     * @param value 原始数值字符串
     * @param sourceBase 原始进制（2-36）
     * @return 转换后的十进制数值
     */
    @Tool(name = "转换为十进制", value = "将指定进制的数值转换为十进制")
    public int convertToDecimal(
            @ToolMemoryId int memoryId,
            @P(value = "原始数值", required = true) String value,
            @P(value = "原始进制", required = true) int sourceBase
    ) {
        System.out.println("调用转换为十进制 " + memoryId);
        if (sourceBase < 2 || sourceBase > 36) {
            throw new IllegalArgumentException("原始进制必须在2到36之间");
        }
        try {
            return Integer.parseInt(value, sourceBase);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无效的数值格式: " + e.getMessage());
        }
    }

    /**
     * URL编码
     * 
     * @param memoryId 会话ID
     * @param text 需要编码的文本
     * @return URL编码后的字符串
     */
    @Tool(name = "URL编码", value = "将文本进行URL编码")
    public String urlEncode(
            @ToolMemoryId int memoryId,
            @P(value = "输入文本", required = true) String text
    ) {
        System.out.println("调用URL编码 " + memoryId);
        try {
            return java.net.URLEncoder.encode(text, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return "URL编码失败: " + e.getMessage();
        }
    }

    /**
     * URL解码
     * 
     * @param memoryId 会话ID
     * @param encodedText URL编码的字符串
     * @return 解码后的文本
     */
    @Tool(name = "URL解码", value = "将URL编码的文本解码")
    public String urlDecode(
            @ToolMemoryId int memoryId,
            @P(value = "编码文本", required = true) String encodedText
    ) {
        System.out.println("调用URL解码 " + memoryId);
        try {
            return java.net.URLDecoder.decode(encodedText, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return "URL解码失败: " + e.getMessage();
        }
    }
}