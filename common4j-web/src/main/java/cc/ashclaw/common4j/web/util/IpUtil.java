// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.web.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * IP address utility class for the Common4J web module.
 * <p>
 * Common4J web 模块的IP地址工具类。
 * <p>
 * This class provides utility methods for working with IP addresses in web applications,
 * including retrieving client IP addresses from HTTP requests with proper proxy handling.
 * <p>
 * 此类提供在Web应用程序中处理IP地址的工具方法，包括从HTTP请求中检索客户端IP地址并正确处理代理。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class IpUtil {

    /**
     * Header name for the X-Forwarded-For header.
     * <p>
     * X-Forwarded-For 头的名称。
     */
    private static final String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";

    /**
     * Header name for the Proxy-Client-IP header.
     * <p>
     * Proxy-Client-IP 头的名称。
     */
    private static final String PROXY_CLIENT_IP_HEADER = "Proxy-Client-IP";

    /**
     * Header name for the WL-Proxy-Client-IP header.
     * <p>
     * WL-Proxy-Client-IP 头的名称。
     */
    private static final String WL_PROXY_CLIENT_IP_HEADER = "WL-Proxy-Client-IP";

    /**
     * Header name for the HTTP_CLIENT_IP header.
     * <p>
     * HTTP_CLIENT_IP 头的名称。
     */
    private static final String HTTP_CLIENT_IP_HEADER = "HTTP_CLIENT_IP";

    /**
     * Header name for the HTTP_X_FORWARDED_FOR header.
     * <p>
     * HTTP_X_FORWARDED_FOR 头的名称。
     */
    private static final String HTTP_X_FORWARDED_FOR_HEADER = "HTTP_X_FORWARDED_FOR";

    /**
     * Unknown IP address constant.
     * <p>
     * 未知IP地址常量。
     */
    private static final String UNKNOWN_IP = "unknown";

    /**
     * Gets the client's real IP address from the HTTP request.
     * <p>
     * 从HTTP请求中获取客户端的真实IP地址。
     * <p>
     * This method attempts to retrieve the client's real IP address by checking
     * various proxy headers in the following order:
     * <ol>
     * <li>X-Forwarded-For</li>
     * <li>Proxy-Client-IP</li>
     * <li>WL-Proxy-Client-IP</li>
     * <li>HTTP_CLIENT_IP</li>
     * <li>HTTP_X_FORWARDED_FOR</li>
     * <li>Remote address from the request</li>
     * </ol>
     * <p>
     * 此方法尝试通过按以下顺序检查各种代理头来检索客户端的真实IP地址：
     * <ol>
     * <li>X-Forwarded-For</li>
     * <li>Proxy-Client-IP</li>
     * <li>WL-Proxy-Client-IP</li>
     * <li>HTTP_CLIENT_IP</li>
     * <li>HTTP_X_FORWARDED_FOR</li>
     * <li>请求中的远程地址</li>
     * </ol>
     *
     * @param request the HTTP servlet request
     *                <p>
     *                HTTP servlet 请求
     * @return the client's real IP address, or "unknown" if it cannot be determined
     *         <p>
     *         客户端的真实IP地址，如果无法确定则返回 "unknown"
     * @throws IllegalArgumentException if the request parameter is null
     *                                   <p>
     *                                   如果请求参数为null
     */
    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        String ip = request.getHeader(X_FORWARDED_FOR_HEADER);
        if (isValidIp(ip)) {
            return extractFirstIp(ip);
        }

        ip = request.getHeader(PROXY_CLIENT_IP_HEADER);
        if (isValidIp(ip)) {
            return ip;
        }

        ip = request.getHeader(WL_PROXY_CLIENT_IP_HEADER);
        if (isValidIp(ip)) {
            return ip;
        }

        ip = request.getHeader(HTTP_CLIENT_IP_HEADER);
        if (isValidIp(ip)) {
            return ip;
        }

        ip = request.getHeader(HTTP_X_FORWARDED_FOR_HEADER);
        if (isValidIp(ip)) {
            return extractFirstIp(ip);
        }

        ip = request.getRemoteAddr();
        return isValidIp(ip) ? ip : UNKNOWN_IP;
    }

    /**
     * Checks if the given IP address is valid and not "unknown".
     * <p>
     * 检查给定的IP地址是否有效且不是 "unknown"。
     *
     * @param ip the IP address to check
     *           <p>
     *           要检查的IP地址
     * @return {@code true} if the IP is valid and not "unknown", {@code false} otherwise
     *         <p>
     *         如果IP有效且不是 "unknown" 则返回 {@code true}，否则返回 {@code false}
     */
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !UNKNOWN_IP.equalsIgnoreCase(ip.trim());
    }

    /**
     * Extracts the first IP address from a comma-separated list of IP addresses.
     * <p>
     * 从逗号分隔的IP地址列表中提取第一个IP地址。
     * <p>
     * This method is typically used for headers like X-Forwarded-For that may
     * contain multiple IP addresses separated by commas.
     * <p>
     * 此方法通常用于像X-Forwarded-For这样的头，这些头可能包含多个用逗号分隔的IP地址。
     *
     * @param ipList the comma-separated list of IP addresses
     *               <p>
     *               逗号分隔的IP地址列表
     * @return the first IP address in the list, or the original string if no comma is found
     *         <p>
     *         列表中的第一个IP地址，如果未找到逗号则返回原始字符串
     */
    private static String extractFirstIp(String ipList) {
        if (ipList == null || ipList.isEmpty()) {
            return ipList;
        }

        int commaIndex = ipList.indexOf(',');
        if (commaIndex > 0) {
            return ipList.substring(0, commaIndex).trim();
        }
        return ipList.trim();
    }

    /**
     * Checks if the given IP address is a valid IP address (either IPv4 or IPv6).
     * <p>
     * 检查给定的IP地址是否为有效的IP地址（IPv4或IPv6）。
     *
     * @param ip the IP address to check
     *           <p>
     *           要检查的IP地址
     * @return {@code true} if the IP is a valid IPv4 or IPv6 address, {@code false} otherwise
     *         <p>
     *         如果IP是有效的IPv4或IPv6地址则返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isValidIpAddress(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        // IPv4 pattern
        String ipv4Pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        
        // IPv6 pattern (simplified)
        String ipv6Pattern = "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";
        
        return ip.matches(ipv4Pattern) || ip.matches(ipv6Pattern);
    }

    /**
     * Checks if the given IP address is within the specified CIDR range.
     * <p>
     * 检查给定的IP地址是否在指定的CIDR范围内。
     * <p>
     * This method supports both IPv4 and IPv6 CIDR notations.
     * The CIDR format should be like "192.168.1.0/24" or "2001:db8::/32".
     * <p>
     * 此方法支持IPv4和IPv6的CIDR表示法。CIDR格式应为 "192.168.1.0/24" 或 "2001:db8::/32"。
     *
     * @param ip   the IP address to check
     *             <p>
     *             要检查的IP地址
     * @param cidr the CIDR range to check against
     *             <p>
     *             要检查的CIDR范围
     * @return {@code true} if the IP is within the CIDR range, {@code false} otherwise
     *         <p>
     *         如果IP在CIDR范围内则返回 {@code true}，否则返回 {@code false}
     * @throws IllegalArgumentException if the IP or CIDR format is invalid
     *                                   <p>
     *                                   如果IP或CIDR格式无效
     */
    public static boolean isIpInCidr(String ip, String cidr) {
        if (ip == null || ip.isEmpty() || cidr == null || cidr.isEmpty()) {
            return false;
        }

        String[] cidrParts = cidr.split("/");
        if (cidrParts.length != 2) {
            throw new IllegalArgumentException("Invalid CIDR format: " + cidr);
        }

        String networkAddress = cidrParts[0];
        int prefixLength;
        try {
            prefixLength = Integer.parseInt(cidrParts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid prefix length in CIDR: " + cidr);
        }

        // Check if both IPs are IPv4
        String ipv4Pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        boolean isIpv4 = ip.matches(ipv4Pattern);
        boolean isNetworkIpv4 = networkAddress.matches(ipv4Pattern);
        
        // Check if both IPs are IPv6
        String ipv6Pattern = "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";
        boolean isIpv6 = ip.matches(ipv6Pattern);
        boolean isNetworkIpv6 = networkAddress.matches(ipv6Pattern);

        if (isIpv4 && isNetworkIpv4) {
            return isIpv4InCidr(ip, networkAddress, prefixLength);
        } else if (isIpv6 && isNetworkIpv6) {
            return isIpv6InCidr(ip, networkAddress, prefixLength);
        } else {
            throw new IllegalArgumentException("IP and CIDR network address must be of the same IP version");
        }
    }

    /**
     * Checks if an IPv4 address is within the specified CIDR range.
     * <p>
     * 检查IPv4地址是否在指定的CIDR范围内。
     *
     * @param ip            the IPv4 address to check
     *                      <p>
     *                      要检查的IPv4地址
     * @param networkAddress the network address of the CIDR range
     *                      <p>
     *                      CIDR范围的网络地址
     * @param prefixLength  the prefix length of the CIDR range
     *                      <p>
     *                      CIDR范围的前缀长度
     * @return {@code true} if the IP is within the CIDR range, {@code false} otherwise
     *         <p>
     *         如果IP在CIDR范围内则返回 {@code true}，否则返回 {@code false}
     */
    private static boolean isIpv4InCidr(String ip, String networkAddress, int prefixLength) {
        if (prefixLength < 0 || prefixLength > 32) {
            throw new IllegalArgumentException("IPv4 prefix length must be between 0 and 32");
        }

        long ipLong = ipv4ToLong(ip);
        long networkLong = ipv4ToLong(networkAddress);
        long mask = prefixLength == 0 ? 0 : 0xFFFFFFFFL << (32 - prefixLength);

        return (ipLong & mask) == (networkLong & mask);
    }

    /**
     * Checks if an IPv6 address is within the specified CIDR range.
     * <p>
     * 检查IPv6地址是否在指定的CIDR范围内。
     *
     * @param ip            the IPv6 address to check
     *                      <p>
     *                      要检查的IPv6地址
     * @param networkAddress the network address of the CIDR range
     *                      <p>
     *                      CIDR范围的网络地址
     * @param prefixLength  the prefix length of the CIDR range
     *                      <p>
     *                      CIDR范围的前缀长度
     * @return {@code true} if the IP is within the CIDR range, {@code false} otherwise
     *         <p>
     *         如果IP在CIDR范围内则返回 {@code true}，否则返回 {@code false}
     */
    private static boolean isIpv6InCidr(String ip, String networkAddress, int prefixLength) {
        if (prefixLength < 0 || prefixLength > 128) {
            throw new IllegalArgumentException("IPv6 prefix length must be between 0 and 128");
        }

        byte[] ipBytes = ipv6ToBytes(ip);
        byte[] networkBytes = ipv6ToBytes(networkAddress);

        int fullBytes = prefixLength / 8;
        int remainingBits = prefixLength % 8;

        // Compare full bytes
        for (int i = 0; i < fullBytes; i++) {
            if (ipBytes[i] != networkBytes[i]) {
                return false;
            }
        }

        // Compare remaining bits
        if (remainingBits > 0) {
            byte mask = (byte) (0xFF << (8 - remainingBits));
            return (ipBytes[fullBytes] & mask) == (networkBytes[fullBytes] & mask);
        }

        return true;
    }

    /**
     * Converts an IPv4 address to a long value.
     * <p>
     * 将IPv4地址转换为long值。
     *
     * @param ip the IPv4 address to convert
     *           <p>
     *           要转换的IPv4地址
     * @return the long representation of the IP address
     *         <p>
     *         IP地址的long表示
     * @throws IllegalArgumentException if the IP address is invalid
     *                                   <p>
     *                                   如果IP地址无效
     */
    private static long ipv4ToLong(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IPv4 address: " + ip);
        }

        long result = 0;
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(parts[i]);
            if (octet < 0 || octet > 255) {
                throw new IllegalArgumentException("Invalid IPv4 octet: " + octet);
            }
            result = (result << 8) | octet;
        }
        return result;
    }

    /**
     * Converts an IPv6 address to a byte array.
     * <p>
     * 将IPv6地址转换为字节数组。
     *
     * @param ip the IPv6 address to convert
     *           <p>
     *           要转换的IPv6地址
     * @return the byte array representation of the IP address
     *         <p>
     *         IP地址的字节数组表示
     * @throws IllegalArgumentException if the IP address is invalid
     *                                   <p>
     *                                   如果IP地址无效
     */
    private static byte[] ipv6ToBytes(String ip) {
        // Normalize the IPv6 address
        String normalizedIp = normalizeIpv6(ip);
        String[] parts = normalizedIp.split(":");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid IPv6 address: " + ip);
        }

        byte[] bytes = new byte[16];
        for (int i = 0; i < 8; i++) {
            int value = Integer.parseInt(parts[i], 16);
            if (value < 0 || value > 0xFFFF) {
                throw new IllegalArgumentException("Invalid IPv6 segment: " + parts[i]);
            }
            bytes[i * 2] = (byte) (value >> 8);
            bytes[i * 2 + 1] = (byte) (value & 0xFF);
        }
        return bytes;
    }

    /**
     * Normalizes an IPv6 address by expanding compressed segments.
     * <p>
     * 通过扩展压缩段来规范化IPv6地址。
     *
     * @param ip the IPv6 address to normalize
     *           <p>
     *           要规范化的IPv6地址
     * @return the normalized IPv6 address
     *         <p>
     *         规范化的IPv6地址
     */
    private static String normalizeIpv6(String ip) {
        // Handle compressed IPv6 addresses (with ::)
        if (ip.contains("::")) {
            int colonCount = countOccurrences(ip, ':');
            int missingColons = 7 - colonCount;
            String replacement = ":";
            for (int i = 0; i < missingColons; i++) {
                replacement += "0:";
            }
            ip = ip.replace("::", replacement);
        }

        // Ensure all segments are present
        String[] parts = ip.split(":");
        StringBuilder normalized = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i < parts.length) {
                // Pad each segment to 4 hex digits
                String segment = parts[i];
                while (segment.length() < 4) {
                    segment = "0" + segment;
                }
                normalized.append(segment);
            } else {
                normalized.append("0000");
            }
            if (i < 7) {
                normalized.append(":");
            }
        }

        return normalized.toString();
    }

    /**
     * Counts the number of occurrences of a character in a string.
     * <p>
     * 计算字符串中某个字符的出现次数。
     *
     * @param str the string to search
     *            <p>
     *            要搜索的字符串
     * @param ch  the character to count
     *            <p>
     *            要计数的字符
     * @return the number of occurrences
     *         <p>
     *         出现次数
     */
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}