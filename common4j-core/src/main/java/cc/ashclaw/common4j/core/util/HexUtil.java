// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.util;

/**
 * Utility class for hexadecimal operations.
 * <p>
 * 十六进制操作工具类。
 * <p>
 * Provides methods for converting between hexadecimal strings and binary data,
 * as well as other hexadecimal-related operations.
 * <p>
 * 提供十六进制字符串与二进制数据之间的转换方法，以及其他与十六进制相关的操作。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class HexUtil {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private HexUtil() {
        throw new UnsupportedOperationException("HexUtil cannot be instantiated.");
    }

    /**
     * Converts a byte array to a hexadecimal string.
     * <p>
     * 将字节数组转换为十六进制字符串。
     *
     * @param bytes the byte array to convert
     *              <p>
     *              要转换的字节数组
     * @return the hexadecimal string representation
     *         <p>
     *         十六进制字符串表示
     */
    public static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    /**
     * Converts a hexadecimal string to a byte array.
     * <p>
     * 将十六进制字符串转换为字节数组。
     *
     * @param hex the hexadecimal string to convert
     *            <p>
     *            要转换的十六进制字符串
     * @return the byte array representation
     *         <p>
     *         字节数组表示
     * @throws IllegalArgumentException if the hex string is invalid
     *                                  <p>
     *                                  如果十六进制字符串无效
     */
    public static byte[] hexToBytes(String hex) {
        if (hex == null) {
            return null;
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            int value = Integer.parseInt(hex.substring(index, index + 2), 16);
            bytes[i] = (byte) value;
        }
        return bytes;
    }

    /**
     * Checks if a string is a valid hexadecimal string.
     * <p>
     * 检查字符串是否是有效的十六进制字符串。
     *
     * @param hex the string to check
     *            <p>
     *            要检查的字符串
     * @return true if the string is a valid hexadecimal string, false otherwise
     *         <p>
     *         如果字符串是有效的十六进制字符串则返回true，否则返回false
     */
    public static boolean isHexString(String hex) {
        if (hex == null) {
            return false;
        }
        if (hex.length() % 2 != 0) {
            return false;
        }
        return hex.matches("[0-9a-fA-F]+");
    }

    /**
     * Converts an integer to a hexadecimal string.
     * <p>
     * 将整数转换为十六进制字符串。
     *
     * @param value the integer value to convert
     *              <p>
     *              要转换的整数值
     * @return the hexadecimal string representation
     *         <p>
     *         十六进制字符串表示
     */
    public static String intToHex(int value) {
        return String.format("%08x", value);
    }

    /**
     * Converts a hexadecimal string to an integer.
     * <p>
     * 将十六进制字符串转换为整数。
     *
     * @param hex the hexadecimal string to convert
     *            <p>
     *            要转换的十六进制字符串
     * @return the integer value
     *         <p>
     *         整数值
     * @throws NumberFormatException if the hex string is invalid
     *                               <p>
     *                               如果十六进制字符串无效
     */
    public static int hexToInt(String hex) {
        if (hex == null) {
            throw new NumberFormatException("Hex string cannot be null");
        }
        // Use parseUnsignedInt for handling large values that represent negative numbers
        return Integer.parseUnsignedInt(hex, 16);
    }
}
