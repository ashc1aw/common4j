// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Utility class for internationalization (i18n) operations.
 * <p>
 * 国际化（i18n）操作工具类。
 * <p>
 * Provides methods for loading and retrieving localized messages from resource bundles.
 * <p>
 * 提供从资源束加载和检索本地化消息的方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class I18nUtil {

    /**
     * Cache for loaded resource bundles.
     * <p>
     * 已加载资源束的缓存。
     */
    private static final ConcurrentMap<String, ConcurrentMap<Locale, ResourceBundle>> BUNDLE_CACHE = new ConcurrentHashMap<>();

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private I18nUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets a localized message from the default resource bundle.
     * <p>
     * 从默认资源束获取本地化消息。
     *
     * @param key the message key
     *            <p>
     *            消息键
     * @return the localized message, or the key if the message is not found
     *         <p>
     *         本地化消息，如果未找到则返回键
     */
    public static String getMessage(String key) {
        return getMessage(key, Locale.getDefault());
    }

    /**
     * Gets a localized message from the default resource bundle using the specified locale.
     * <p>
     * 使用指定的语言环境从默认资源束获取本地化消息。
     *
     * @param key    the message key
     *               <p>
     *               消息键
     * @param locale the locale to use
     *               <p>
     *               要使用的语言环境
     * @return the localized message, or the key if the message is not found
     *         <p>
     *         本地化消息，如果未找到则返回键
     */
    public static String getMessage(String key, Locale locale) {
        return getMessage("messages", key, locale);
    }

    /**
     * Gets a localized message from the specified resource bundle using the default locale.
     * <p>
     * 使用默认语言环境从指定资源束获取本地化消息。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     * @param key        the message key
     *                   <p>
     *                   消息键
     * @return the localized message, or the key if the message is not found
     *         <p>
     *         本地化消息，如果未找到则返回键
     */
    public static String getMessage(String bundleName, String key) {
        return getMessage(bundleName, key, Locale.getDefault());
    }

    /**
     * Gets a localized message from the specified resource bundle using the specified locale.
     * <p>
     * 使用指定的语言环境从指定资源束获取本地化消息。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     * @param key        the message key
     *                   <p>
     *                   消息键
     * @param locale     the locale to use
     *                   <p>
     *                   要使用的语言环境
     * @return the localized message, or the key if the message is not found
     *         <p>
     *         本地化消息，如果未找到则返回键
     */
    public static String getMessage(String bundleName, String key, Locale locale) {
        ResourceBundle bundle = getResourceBundle(bundleName, locale);
        if (bundle == null) {
            return key;
        }
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

    /**
     * Gets a parameterized localized message from the default resource bundle using the default locale.
     * <p>
     * 使用默认语言环境从默认资源束获取参数化本地化消息。
     *
     * @param key    the message key
     *               <p>
     *               消息键
     * @param params the message parameters
     *               <p>
     *               消息参数
     * @return the formatted localized message, or the key if the message is not found
     *         <p>
     *         格式化的本地化消息，如果未找到则返回键
     */
    public static String getMessage(String key, Object... params) {
        return getMessage(key, Locale.getDefault(), params);
    }

    /**
     * Gets a parameterized localized message from the default resource bundle using the specified locale.
     * <p>
     * 使用指定的语言环境从默认资源束获取参数化本地化消息。
     *
     * @param key    the message key
     *               <p>
     *               消息键
     * @param locale the locale to use
     *               <p>
     *               要使用的语言环境
     * @param params the message parameters
     *               <p>
     *               消息参数
     * @return the formatted localized message, or the key if the message is not found
     *         <p>
     *         格式化的本地化消息，如果未找到则返回键
     */
    public static String getMessage(String key, Locale locale, Object... params) {
        return getMessage("messages", key, locale, params);
    }

    /**
     * Gets a parameterized localized message from the specified resource bundle using the default locale.
     * <p>
     * 使用默认语言环境从指定资源束获取参数化本地化消息。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     * @param key        the message key
     *                   <p>
     *                   消息键
     * @param params     the message parameters
     *                   <p>
     *                   消息参数
     * @return the formatted localized message, or the key if the message is not found
     *         <p>
     *         格式化的本地化消息，如果未找到则返回键
     */
    public static String getMessage(String bundleName, String key, Object... params) {
        return getMessage(bundleName, key, Locale.getDefault(), params);
    }

    /**
     * Gets a parameterized localized message from the specified resource bundle using the specified locale.
     * <p>
     * 使用指定的语言环境从指定资源束获取参数化本地化消息。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     * @param key        the message key
     *                   <p>
     *                   消息键
     * @param locale     the locale to use
     *                   <p>
     *                   要使用的语言环境
     * @param params     the message parameters
     *                   <p>
     *                   消息参数
     * @return the formatted localized message, or the key if the message is not found
     *         <p>
     *         格式化的本地化消息，如果未找到则返回键
     */
    public static String getMessage(String bundleName, String key, Locale locale, Object... params) {
        String message = getMessage(bundleName, key, locale);
        if (params == null || params.length == 0) {
            return message;
        }
        try {
            return String.format(message, params);
        } catch (Exception e) {
            return message;
        }
    }

    /**
     * Gets a resource bundle by name and locale, with caching.
     * <p>
     * 通过名称和语言环境获取资源束，并进行缓存。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     * @param locale     the locale to use
     *                   <p>
     *                   要使用的语言环境
     * @return the resource bundle, or null if not found
     *         <p>
     *         资源束，如果未找到则返回null
     */
    public static ResourceBundle getResourceBundle(String bundleName, Locale locale) {
        if (bundleName == null || locale == null) {
            return null;
        }

        ConcurrentMap<Locale, ResourceBundle> localeMap = BUNDLE_CACHE.computeIfAbsent(bundleName, k -> new ConcurrentHashMap<>());
        return localeMap.computeIfAbsent(locale, l -> {
            try {
                return ResourceBundle.getBundle(bundleName, l);
            } catch (Exception e) {
                return null;
            }
        });
    }

    /**
     * Clears the resource bundle cache.
     * <p>
     * 清除资源束缓存。
     */
    public static void clearCache() {
        BUNDLE_CACHE.clear();
    }

    /**
     * Clears the cache for a specific resource bundle.
     * <p>
     * 清除特定资源束的缓存。
     *
     * @param bundleName the resource bundle name
     *                   <p>
     *                   资源束名称
     */
    public static void clearCache(String bundleName) {
        if (bundleName != null) {
            BUNDLE_CACHE.remove(bundleName);
        }
    }
}
