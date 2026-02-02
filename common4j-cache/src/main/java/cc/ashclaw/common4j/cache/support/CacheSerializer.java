package cc.ashclaw.common4j.cache.support;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Cache serializer implementation for object serialization and deserialization.
 * <p>
 * 缓存序列化器实现，用于对象序列化和反序列化。
 * <p>
 * This class provides serialization and deserialization capabilities
 * for cache values using Jackson ObjectMapper for JSON serialization.
 * <p>
 * 此类使用Jackson ObjectMapper提供缓存值的序列化和反序列化功能，支持JSON序列化。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheSerializer {
    
    private final ObjectMapper objectMapper;
    
    public CacheSerializer() {
        this.objectMapper = new ObjectMapper();
    }
    
    public CacheSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    /**
     * Serializes an object to string.
     * <p>
     * 将对象序列化为字符串。
     *
     * @param obj the object to serialize
     *            <p>
     *            要序列化的对象
     * @return the serialized string, or null if the object is null
     *         <p>
     *         序列化后的字符串，如果对象为null则返回null
     */
    public String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        
        if (obj instanceof String) {
            return (String) obj;
        }
        
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化对象失败", e);
        }
    }
    
    /**
     * Deserializes a string to an object of the specified class.
     * <p>
     * 将字符串反序列化为指定类的对象。
     *
     * @param str the string to deserialize
     *            <p>
     *            要反序列化的字符串
     * @param clazz the target class type
     *              <p>
     *              目标类类型
     * @param <T> the type of the object to deserialize
     *            <p>
     *            要反序列化的对象类型
     * @return the deserialized object, or null if the string is empty
     *         <p>
     *         反序列化后的对象，如果字符串为空则返回null
     */
    public <T> T deserialize(String str, Class<T> clazz) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        
        if (clazz == String.class) {
            return clazz.cast(str);
        }
        
        try {
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象失败", e);
        }
    }
    
    /**
     * Deserializes a string to an object of the specified type.
     * <p>
     * 将字符串反序列化为指定类型的对象。
     *
     * @param str the string to deserialize
     *            <p>
     *            要反序列化的字符串
     * @param type the target type
     *             <p>
     *             目标类型
     * @param <T> the type of the object to deserialize
     *            <p>
     *            要反序列化的对象类型
     * @return the deserialized object, or null if the string is empty
     *         <p>
     *         反序列化后的对象，如果字符串为空则返回null
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String str, java.lang.reflect.Type type) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        
        try {
            return objectMapper.readValue(str, objectMapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象失败", e);
        }
    }
}