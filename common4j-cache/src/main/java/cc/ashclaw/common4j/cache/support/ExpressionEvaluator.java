package cc.ashclaw.common4j.cache.support;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * SpEL expression evaluator for cache condition evaluation.
 * <p>
 * SpEL表达式求值器，用于缓存条件评估。
 * <p>
 * This class provides evaluation capabilities for SpEL expressions
 * used in cache annotations for conditional caching and unless expressions.
 * <p>
 * 此类提供SpEL表达式的评估功能，用于缓存注解中的条件缓存和unless表达式。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class ExpressionEvaluator {
    
    private final ExpressionParser parser = new SpelExpressionParser();
    
    /**
     * Evaluates a condition expression for cache operations.
     * <p>
     * 评估缓存操作的条件表达式。
     *
     * @param condition the SpEL condition expression to evaluate
     *                  <p>
     *                  要评估的SpEL条件表达式
     * @param target the target object
     *               <p>
     *               目标对象
     * @param method the target method
     *               <p>
     *               目标方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param result the method result (can be null for before execution)
     *               <p>
     *               方法结果（执行前可以为null）
     * @return {@code true} if the condition evaluates to true, {@code false} otherwise
     *         <p>
     *         如果条件评估为true，则返回 {@code true}；否则返回 {@code false}
     */
    public boolean evaluateCondition(String condition, Object target, Method method, Object[] args, Object result) {
        if (!StringUtils.hasText(condition)) {
            return true;
        }
        
        StandardEvaluationContext context = createEvaluationContext(target, method, args, result);
        Expression expression = parser.parseExpression(condition);
        
        Boolean value = expression.getValue(context, Boolean.class);
        return value != null && value;
    }
    
    /**
     * Evaluates an unless expression for cache operations.
     * <p>
     * 评估缓存操作的unless表达式。
     *
     * @param unless the SpEL unless expression to evaluate
     *               <p>
     *               要评估的SpEL unless表达式
     * @param target the target object
     *               <p>
     *               目标对象
     * @param method the target method
     *               <p>
     *               目标方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param result the method result (can be null for before execution)
     *               <p>
     *               方法结果（执行前可以为null）
     * @return {@code true} if the unless expression evaluates to true, {@code false} otherwise
     *         <p>
     *         如果unless表达式评估为true，则返回 {@code true}；否则返回 {@code false}
     */
    public boolean evaluateUnless(String unless, Object target, Method method, Object[] args, Object result) {
        if (!StringUtils.hasText(unless)) {
            return false;
        }
        
        StandardEvaluationContext context = createEvaluationContext(target, method, args, result);
        Expression expression = parser.parseExpression(unless);
        
        Boolean value = expression.getValue(context, Boolean.class);
        return value != null && value;
    }
    
    /**
     * Evaluates an expression and returns the result object.
     * <p>
     * 评估表达式并返回结果对象。
     *
     * @param expression the SpEL expression to evaluate
     *                   <p>
     *                   要评估的SpEL表达式
     * @param target the target object
     *               <p>
     *               目标对象
     * @param method the target method
     *               <p>
     *               目标方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param result the method result (can be null for before execution)
     *               <p>
     *               方法结果（执行前可以为null）
     * @return the evaluation result, or null if the expression is empty
     *         <p>
     *         评估结果，如果表达式为空则返回null
     */
    public Object evaluateExpression(String expression, Object target, Method method, Object[] args, Object result) {
        if (!StringUtils.hasText(expression)) {
            return null;
        }
        
        StandardEvaluationContext context = createEvaluationContext(target, method, args, result);
        Expression expr = parser.parseExpression(expression);
        
        return expr.getValue(context);
    }
    
    /**
     * Creates an evaluation context for SpEL expression evaluation.
     * <p>
     * 为SpEL表达式评估创建评估上下文。
     *
     * @param target the target object
     *               <p>
     *               目标对象
     * @param method the target method
     *               <p>
     *               目标方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param result the method result (can be null for before execution)
     *               <p>
     *               方法结果（执行前可以为null）
     * @return the created StandardEvaluationContext
     *         <p>
     *         创建的StandardEvaluationContext
     */
    private StandardEvaluationContext createEvaluationContext(Object target, Method method, Object[] args, Object result) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        
        context.setVariable("target", target);
        context.setVariable("method", method);
        context.setVariable("args", args);
        context.setVariable("result", result);
        
        // 设置方法参数
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                context.setVariable("p" + i, args[i]);
                context.setVariable("a" + i, args[i]);
            }
        }
        
        return context;
    }
}