// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON utility class for the Common4J web module.
 * <p>
 * Common4J web 模块的JSON工具类。
 * <p>
 * This class provides utility methods for JSON serialization and deserialization
 * using Jackson library, including object-to-JSON conversion, JSON-to-object conversion,
 * and JSON-to-Map operations.
 * <p>
 * 此类提供使用Jackson库进行JSON序列化和反序列化的工具方法，包括对象转JSON、JSON转对象以及JSON转Map操作。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class JsonUtil {

    /**
     * ObjectMapper instance for JSON operations.
     * <p>
     * 用于JSON操作的ObjectMapper实例。
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Static initialization block to configure ObjectMapper.
     * <p>
     * 配置ObjectMapper的静态初始化块。
     */
    static {
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Converts an object to a JSON string.
     * <p>
     * 将对象转换为JSON字符串。
     * <p>
     * This method serializes the given object to a JSON string using Jackson.
     * If the object is null, it returns "null".
     * <p>
     * 此方法使用Jackson将给定对象序列化为JSON字符串。如果对象为null，则返回 "null"。
     *
     * @param object the object to convert
     *               <p>
     *               要转换的对象
     * @return the JSON string representation of the object
     *         <p>
     *         对象的JSON字符串表示
     * @throws RuntimeException if conversion fails
     *                         <p>
     *                         如果转换失败
     */
    public static String toJson(Object object) {
        if (object == null) {
            return "null";
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Converts an object to a pretty-printed JSON string.
     * <p>
     * 将对象转换为格式化的JSON字符串。
     * <p>
     * This method serializes the given object to a pretty-printed JSON string
     * with proper indentation for better readability.
     * <p>
     * 此方法将给定对象序列化为格式化的JSON字符串，具有适当的缩进以提高可读性。
     *
     * @param object the object to convert
     *               <p>
     *               要转换的对象
     * @return the pretty-printed JSON string representation of the object
     *         <p>
     *         对象格式化的JSON字符串表示
     * @throws RuntimeException if conversion fails
     *                         <p>
     *                         如果转换失败
     */
    public static String toPrettyJson(Object object) {
        if (object == null) {
            return "null";
        }

        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to pretty JSON", e);
        }
    }

    /**
     * Parses a JSON string to an object of the specified class.
     * <p>
     * 将JSON字符串解析为指定类的对象。
     *
     * @param json  the JSON string to parse
     *             <p>
     *             要解析的JSON字符串
     * @param clazz the class of the target object
     *              <p>
     *              目标对象的类
     * @param <T>   the type of the target object
     *              <p>
     *              目标对象的类型
     * @return the object deserialized from the JSON string
     *         <p>
     *         从JSON字符串反序列化的对象
     * @throws RuntimeException if parsing fails
     *                         <p>
     *                         如果解析失败
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }

        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON to object", e);
        }
    }

    /**
     * Parses a JSON string to an object using TypeReference for complex types.
     * <p>
     * 使用TypeReference将JSON字符串解析为对象，用于复杂类型。
     * <p>
     * This method is particularly useful for deserializing generic types like
     * List&lt;MyClass&gt; or Map&lt;String, MyClass&gt;.
     * <p>
     * 此方法对于反序列化泛型类型（如 List&lt;MyClass&gt; 或 Map&lt;String, MyClass&gt;）特别有用。
     *
     * @param json        the JSON string to parse
     *                    <p>
     *                    要解析的JSON字符串
     * @param typeRef     the TypeReference for complex type resolution
     *                    <p>
     *                    用于复杂类型解析的TypeReference
     * @param <T>         the type of the target object
     *                    <p>
     *                    目标对象的类型
     * @return the object deserialized from the JSON string
     *         <p>
     *         从JSON字符串反序列化的对象
     * @throws RuntimeException if parsing fails
     *                         <p>
     *                         如果解析失败
     */
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }

        try {
            return OBJECT_MAPPER.readValue(json, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON to object with TypeReference", e);
        }
    }

    /**
     * Converts a JSON string to a Map.
     * <p>
     * 将JSON字符串转换为Map。
     *
     * @param json the JSON string to convert
     *             <p>
     *             要转换的JSON字符串
     * @return a Map representation of the JSON string
     *         <p>
     *         JSON字符串的Map表示
     * @throws RuntimeException if conversion fails
     *                         <p>
     *                         如果转换失败
     */
    public static Map<String, Object> toMap(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }

        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to Map", e);
        }
    }

    /**
     * Converts an object to a Map.
     * <p>
     * 将对象转换为Map。
     *
     * @param object the object to convert
     *               <p>
     *               要转换的对象
     * @return a Map representation of the object
     *         <p>
     *         对象的Map表示
     * @throws RuntimeException if conversion fails
     *                         <p>
     *                         如果转换失败
     */
    public static Map<String, Object> toMap(Object object) {
        if (object == null) {
            return new HashMap<>();
        }

        try {
            return OBJECT_MAPPER.convertValue(object, new TypeReference<Map<String, Object>>() {});
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to convert object to Map", e);
        }
    }
}