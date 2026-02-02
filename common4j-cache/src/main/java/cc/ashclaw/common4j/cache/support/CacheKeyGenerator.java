package cc.ashclaw.common4j.cache.support;

import org.springframework.cache.interceptor.KeyGenerator;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Cache key generator implementation.
 * <p>
 * 缓存键生成器实现。
 * <p>
 * This class provides methods for generating cache keys based on
 * method names, parameters, and SpEL expressions for flexible
 * cache key generation strategies.
 * <p>
 * 此类提供基于方法名称、参数和SpEL表达式生成缓存键的方法，支持灵活的缓存键生成策略。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheKeyGenerator implements KeyGenerator {
    
    private final ExpressionParser parser = new SpelExpressionParser();
    
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(method.getName(), params);
    }
    
    /**
     * Generates a cache key based on method name and parameters.
     * <p>
     * 基于方法名称和参数生成缓存键。
     *
     * @param methodName the name of the method
     *                   <p>
     *                   方法名称
     * @param params the method parameters
     *               <p>
     *               方法参数
     * @return the generated cache key
     *         <p>
     *         生成的缓存键
     */
    public Object generateKey(String methodName, Object... params) {
        if (params.length == 0) {
            return methodName;
        }
        
        if (params.length == 1) {
            Object param = params[0];
            if (param != null && !param.getClass().isArray()) {
                return methodName + ":" + param;
            }
        }
        
        StringBuilder key = new StringBuilder(methodName);
        for (Object param : params) {
            key.append(":").append(param);
        }
        
        return key.toString();
    }
    
    /**
     * Generates a cache key based on SpEL expression.
     * <p>
     * 基于SpEL表达式生成缓存键。
     *
     * @param keyExpression the SpEL expression for key generation
     *                      <p>
     *                      用于生成键的SpEL表达式
     * @param target the target object
     *               <p>
     *               目标对象
     * @param method the target method
     *               <p>
     *               目标方法
     * @param params the method parameters
     *               <p>
     *               方法参数
     * @return the generated cache key based on SpEL expression
     *         <p>
     *         基于SpEL表达式生成的缓存键
     */
    public Object generateKeyBySpEL(String keyExpression, Object target, Method method, Object... params) {
        if (!StringUtils.hasText(keyExpression)) {
            return generate(target, method, params);
        }
        
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("target", target);
        context.setVariable("method", method);
        context.setVariable("args", params);
        
        // 设置方法参数
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                context.setVariable("p" + i, params[i]);
                context.setVariable("a" + i, params[i]);
            }
        }
        
        Expression expression = parser.parseExpression(keyExpression);
        return expression.getValue(context);
    }
}