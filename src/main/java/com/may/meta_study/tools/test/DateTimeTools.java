package com.may.meta_study.tools.test;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 日期时间处理工具类
 * 提供各种日期时间处理功能供大模型调用
 */
@Component
public class DateTimeTools {

    /**
     * 获取当前日期时间
     * 
     * @param memoryId 会话ID
     * @param format 日期时间格式，可选，默认为 yyyy-MM-dd HH:mm:ss
     * @return 格式化后的当前日期时间
     */
    @Tool(name = "获取当前时间", value = "获取当前的日期时间，可指定格式")
    public String getCurrentDateTime(
            @ToolMemoryId int memoryId,
            @P(value = "日期格式", required = false) String format
    ) {
        System.out.println("调用获取当前时间 " + memoryId);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                format != null && !format.isEmpty() ? format : "yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    /**
     * 计算两个日期之间的天数差
     * 
     * @param memoryId 会话ID
     * @param startDate 开始日期，格式：yyyy-MM-dd
     * @param endDate 结束日期，格式：yyyy-MM-dd
     * @return 两个日期之间的天数差
     */
    @Tool(name = "计算日期差", value = "计算两个日期之间的天数差")
    public long calculateDateDifference(
            @ToolMemoryId int memoryId,
            @P(value = "开始日期", required = true) String startDate,
            @P(value = "结束日期", required = true) String endDate
    ) {
        System.out.println("调用计算日期差 " + memoryId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 日期格式转换
     * 
     * @param memoryId 会话ID
     * @param dateStr 日期字符串
     * @param inputFormat 输入格式
     * @param outputFormat 输出格式
     * @return 转换后的日期字符串
     */
    @Tool(name = "日期格式转换", value = "将日期从一种格式转换为另一种格式")
    public String convertDateFormat(
            @ToolMemoryId int memoryId,
            @P(value = "日期字符串", required = true) String dateStr,
            @P(value = "输入格式", required = true) String inputFormat,
            @P(value = "输出格式", required = true) String outputFormat
    ) {
        System.out.println("调用日期格式转换 " + memoryId);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateStr, inputFormatter);
        } catch (Exception e) {
            // 尝试解析为LocalDate
            try {
                LocalDate date = LocalDate.parse(dateStr, inputFormatter);
                dateTime = date.atStartOfDay();
            } catch (Exception ex) {
                // 尝试解析为LocalTime
                try {
                    LocalTime time = LocalTime.parse(dateStr, inputFormatter);
                    dateTime = time.atDate(LocalDate.now());
                } catch (Exception exc) {
                    return "无法解析日期: " + dateStr + "，请检查输入格式是否正确";
                }
            }
        }
        
        return dateTime.format(outputFormatter);
    }

    /**
     * 添加或减少时间
     * 
     * @param memoryId 会话ID
     * @param dateStr 日期字符串，格式：yyyy-MM-dd HH:mm:ss
     * @param amount 添加或减少的数量，正数为添加，负数为减少
     * @param unit 时间单位，可选值：years, months, days, hours, minutes, seconds
     * @return 计算后的日期时间
     */
    @Tool(name = "时间计算", value = "在指定日期时间上添加或减少一定的时间")
    public String addOrSubtractTime(
            @ToolMemoryId int memoryId,
            @P(value = "日期时间", required = true) String dateStr,
            @P(value = "数量", required = true) long amount,
            @P(value = "时间单位", required = true) String unit
    ) {
        System.out.println("调用时间计算 " + memoryId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        
        LocalDateTime result;
        switch (unit.toLowerCase()) {
            case "years":
                result = dateTime.plusYears(amount);
                break;
            case "months":
                result = dateTime.plusMonths(amount);
                break;
            case "days":
                result = dateTime.plusDays(amount);
                break;
            case "hours":
                result = dateTime.plusHours(amount);
                break;
            case "minutes":
                result = dateTime.plusMinutes(amount);
                break;
            case "seconds":
                result = dateTime.plusSeconds(amount);
                break;
            default:
                return "不支持的时间单位: " + unit;
        }
        
        return result.format(formatter);
    }
}