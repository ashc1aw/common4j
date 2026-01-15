// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.constant;

import java.io.File;

/**
 * Provides various separator constants for different use cases.
 * <p>
 * 分隔符常量
 * <p>
 * Includes separators for time, date, file paths, system properties, and common punctuation.
 * <p>
 * 包含时间、日期、文件路径、系统属性和常用标点符号的分隔符。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class Separators {

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化
     */
    private Separators() {
        throw new UnsupportedOperationException("Separators cannot be instantiated");
    }

    

    /**
     * ISO datetime separator
     * <p>
     * ISO 日期时间分隔符
     */
    public static final String ISO_DATETIME = "T";

    /**
     * Standard datetime separator
     * <p>
     * 标准日期时间分隔符
     */
    public static final String STANDARD_DATETIME = " ";

    /**
     * Date part separator: -
     * <p>
     * 日期部分分隔符：-
     */
    public static final String DATE = "-";

    /**
     * Time part separator
     * <p>
     * 时间部分分隔符
     */
    public static final String TIME = ":";

    /**
     * Millisecond separator
     * <p>
     * 毫秒分隔符
     */
    public static final String MILLISECOND = ".";

    

    /**
     * File path separator (system-dependent)
     * <p>
     * 文件路径分隔符（系统相关）
     */
    public static final String FILE_SEPARATOR = File.separator;

    /**
     * Path separator (system-dependent), used to separate multiple paths
     * <p>
     * 路径分隔符（系统相关），用于分隔多个路径
     */
    public static final String PATH_SEPARATOR = File.pathSeparator;

    

    /**
     * Comma separator
     * <p>
     * 逗号分隔符
     */
    public static final String COMMA = ",";

    /**
     * Semicolon separator
     * <p>
     * 分号分隔符
     */
    public static final String SEMICOLON = ";";

    /**
     * Pipe separator
     * <p>
     * 竖线分隔符
     */
    public static final String PIPE = "|";

    /**
     * Underscore separator
     * <p>
     * 下划线分隔符
     */
    public static final String UNDERSCORE = "_";

    /**
     * Slash separator
     * <p>
     * 斜杠分隔符
     */
    public static final String SLASH = "/";

    /**
     * Backslash separator
     * <p>
     * 反斜杠分隔符
     */
    public static final String BACKSLASH = "\\";

    /**
     * Asterisk separator
     * <p>
     * 星号分隔符
     */
    public static final String ASTERISK = "*";

    /**
     * Hash separator
     * <p>
     * 井号分隔符
     */
    public static final String HASH = "#";

    /**
     * Equals separator
     * <p>
     * 等号分隔符
     */
    public static final String EQUALS = "=";

    /**
     * Ampersand separator
     * <p>
     * and符号分隔符
     */
    public static final String AMPERSAND = "&";

    /**
     * At separator
     * <p>
     * at符号分隔符
     */
    public static final String AT = "@";

    /**
     * Question mark separator
     * <p>
     * 问号分隔符
     */
    public static final String QUESTION_MARK = "?";

    

    /**
     * Tab separator
     * <p>
     * 制表符分隔符
     */
    public static final String TAB = "\t";

    /**
     * Line feed separator
     * <p>
     * 换行符
     */
    public static final String LINE_FEED = "\n";

    /**
     * Carriage return separator
     * <p>
     * 回车符
     */
    public static final String CARRIAGE_RETURN = "\r";

    /**
     * System-dependent line separator
     * <p>
     * 系统相关的换行符
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Space separator
     * <p>
     * 空格分隔符
     */
    public static final String SPACE = " ";

    /**
     * Zero-width space separator (invisible character)
     * <p>
     * 零宽空格分隔符（不可见字符）
     */
    public static final String ZERO_WIDTH_SPACE = "\u200B";

    

    /**
     * Comma followed by space
     * <p>
     * 逗号加空格
     */
    public static final String COMMA_SPACE = ", ";

    /**
     * Semicolon followed by space
     * <p>
     * 分号加空格
     */
    public static final String SEMICOLON_SPACE = "; ";

    /**
     * Colon followed by space
     * <p>
     * 冒号加空格
     */
    public static final String COLON_SPACE = ": ";

    /**
     * Pipe followed by space
     * <p>
     * 竖线加空格
     */
    public static final String PIPE_SPACE = "| ";

    

    /**
     * JSON array/object element separator
     * <p>
     * JSON 数组/对象分隔符
     */
    public static final String JSON_ELEMENT = ",";

    /**
     * Query parameter separator
     * <p>
     * 查询参数分隔符
     */
    public static final String QUERY_PARAM = "&";

    /**
     * URL path separator
     * <p>
     * URL路径分隔符
     */
    public static final String URL_PATH = "/";

    /**
     * Windows file path separator
     * <p>
     * Windows 文件路径分隔符
     */
    public static final String WINDOWS_FILE_SEPARATOR = "\\";

    /**
     * Unix/Linux file path separator
     * <p>
     * Unix/Linux 文件路径分隔符
     */
    public static final String UNIX_FILE_SEPARATOR = "/";

    /**
     * CSV separator
     * <p>
     * CSV 分隔符
     */
    public static final String CSV = ",";

    /**
     * TSV separator (tab)
     * <p>
     * TSV 分隔符（制表符）
     */
    public static final String TSV = "\t";
}