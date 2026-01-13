// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

/**
 * Test class for StringUtils result verification.
 * <p>
 * StringUtils结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        System.out.println("===== StringUtils Result Verification Test Start =====");
        
        // Test blank/empty checking methods
        testBlankEmptyChecks();
        
        // Test trim methods
        testTrimMethods();
        
        // Test default value methods
        testDefaultValueMethods();
        
        // Test whitespace methods
        testWhitespaceMethods();
        
        // Test equality methods
        testEqualityMethods();
        
        // Test startsWith/endsWith methods
        testStartEndMethods();
        
        // Test substring methods
        testSubstringMethods();
        
        System.out.println("===== StringUtils Result Verification Test End =====");
    }

    /**
     * Test the blank and empty checking methods of StringUtils.
     * <p>
     * 测试StringUtils的空白和空值检查方法。
     */
    private static void testBlankEmptyChecks() {
        System.out.println("\n1. Testing blank/empty checking methods...");
        
        // Test isBlank
        boolean result = StringUtils.isBlank(null);
        System.out.println("isBlank(null) = " + result);
        if (!result) {
            System.out.println("ERROR: isBlank should return true for null!\n");
            return;
        }
        
        result = StringUtils.isBlank("");
        System.out.println("isBlank(\"\") = " + result);
        if (!result) {
            System.out.println("ERROR: isBlank should return true for empty string!\n");
            return;
        }
        
        result = StringUtils.isBlank("   ");
        System.out.println("isBlank(\"   \") = " + result);
        if (!result) {
            System.out.println("ERROR: isBlank should return true for whitespace only!\n");
            return;
        }
        
        result = StringUtils.isBlank("test");
        System.out.println("isBlank(\"test\") = " + result);
        if (result) {
            System.out.println("ERROR: isBlank should return false for non-blank string!\n");
            return;
        }
        
        result = StringUtils.isBlank("  test  ");
        System.out.println("isBlank(\"  test  \") = " + result);
        if (result) {
            System.out.println("ERROR: isBlank should return false for string with whitespace!\n");
            return;
        }
        
        // Test isEmpty
        result = StringUtils.isEmpty(null);
        System.out.println("isEmpty(null) = " + result);
        if (!result) {
            System.out.println("ERROR: isEmpty should return true for null!\n");
            return;
        }
        
        result = StringUtils.isEmpty("");
        System.out.println("isEmpty(\"\") = " + result);
        if (!result) {
            System.out.println("ERROR: isEmpty should return true for empty string!\n");
            return;
        }
        
        result = StringUtils.isEmpty("   ");
        System.out.println("isEmpty(\"   \") = " + result);
        if (result) {
            System.out.println("ERROR: isEmpty should return false for whitespace only!\n");
            return;
        }
        
        result = StringUtils.isEmpty("test");
        System.out.println("isEmpty(\"test\") = " + result);
        if (result) {
            System.out.println("ERROR: isEmpty should return false for non-empty string!\n");
            return;
        }
        
        // Test isNotBlank
        result = StringUtils.isNotBlank(null);
        System.out.println("isNotBlank(null) = " + result);
        if (result) {
            System.out.println("ERROR: isNotBlank should return false for null!\n");
            return;
        }
        
        result = StringUtils.isNotBlank("test");
        System.out.println("isNotBlank(\"test\") = " + result);
        if (!result) {
            System.out.println("ERROR: isNotBlank should return true for non-blank string!\n");
            return;
        }
        
        // Test isNotEmpty
        result = StringUtils.isNotEmpty(null);
        System.out.println("isNotEmpty(null) = " + result);
        if (result) {
            System.out.println("ERROR: isNotEmpty should return false for null!\n");
            return;
        }
        
        result = StringUtils.isNotEmpty("   ");
        System.out.println("isNotEmpty(\"   \") = " + result);
        if (!result) {
            System.out.println("ERROR: isNotEmpty should return true for non-empty string!\n");
            return;
        }
        
        System.out.println("blank/empty checking methods test passed.");
    }

    /**
     * Test the trim methods of StringUtils.
     * <p>
     * 测试StringUtils的修剪方法。
     */
    private static void testTrimMethods() {
        System.out.println("\n2. Testing trim methods...");
        
        // Test trim
        String result = StringUtils.trim(null);
        System.out.println("trim(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: trim should return null for null!\n");
            return;
        }
        
        result = StringUtils.trim("  test  ");
        System.out.println("trim(\"  test  \") = \"" + result + "\"");
        if (!"test".equals(result)) {
            System.out.println("ERROR: trim should remove whitespace from both ends!\n");
            return;
        }
        
        // Test trimToNull
        result = StringUtils.trimToNull(null);
        System.out.println("trimToNull(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: trimToNull should return null for null!\n");
            return;
        }
        
        result = StringUtils.trimToNull("   ");
        System.out.println("trimToNull(\"   \") = " + result);
        if (result != null) {
            System.out.println("ERROR: trimToNull should return null for whitespace only!\n");
            return;
        }
        
        result = StringUtils.trimToNull("  test  ");
        System.out.println("trimToNull(\"  test  \") = \"" + result + "\"");
        if (!"test".equals(result)) {
            System.out.println("ERROR: trimToNull should trim and return non-empty string!\n");
            return;
        }
        
        // Test trimToEmpty
        result = StringUtils.trimToEmpty(null);
        System.out.println("trimToEmpty(null) = \"" + result + "\"");
        if (!"" .equals(result)) {
            System.out.println("ERROR: trimToEmpty should return empty string for null!\n");
            return;
        }
        
        result = StringUtils.trimToEmpty("   ");
        System.out.println("trimToEmpty(\"   \") = \"" + result + "\"");
        if (!"" .equals(result)) {
            System.out.println("ERROR: trimToEmpty should return empty string for whitespace only!\n");
            return;
        }
        
        result = StringUtils.trimToEmpty("  test  ");
        System.out.println("trimToEmpty(\"  test  \") = \"" + result + "\"");
        if (!"test".equals(result)) {
            System.out.println("ERROR: trimToEmpty should trim string!\n");
            return;
        }
        
        System.out.println("trim methods test passed.");
    }

    /**
     * Test the default value methods of StringUtils.
     * <p>
     * 测试StringUtils的默认值方法。
     */
    private static void testDefaultValueMethods() {
        System.out.println("\n3. Testing default value methods...");
        
        // Test defaultIfBlank
        String result = StringUtils.defaultIfBlank(null, "default");
        System.out.println("defaultIfBlank(null, \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfBlank should return default for null!\n");
            return;
        }
        
        result = StringUtils.defaultIfBlank("", "default");
        System.out.println("defaultIfBlank(\"\", \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfBlank should return default for empty string!\n");
            return;
        }
        
        result = StringUtils.defaultIfBlank("   ", "default");
        System.out.println("defaultIfBlank(\"   \", \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfBlank should return default for whitespace only!\n");
            return;
        }
        
        result = StringUtils.defaultIfBlank("value", "default");
        System.out.println("defaultIfBlank(\"value\", \"default\") = \"" + result + "\"");
        if (!"value".equals(result)) {
            System.out.println("ERROR: defaultIfBlank should return original for non-blank!\n");
            return;
        }
        
        // Test defaultIfEmpty
        result = StringUtils.defaultIfEmpty(null, "default");
        System.out.println("defaultIfEmpty(null, \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfEmpty should return default for null!\n");
            return;
        }
        
        result = StringUtils.defaultIfEmpty("", "default");
        System.out.println("defaultIfEmpty(\"\", \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfEmpty should return default for empty string!\n");
            return;
        }
        
        result = StringUtils.defaultIfEmpty("   ", "default");
        System.out.println("defaultIfEmpty(\"   \", \"default\") = \"" + result + "\"");
        if (!"   ".equals(result)) {
            System.out.println("ERROR: defaultIfEmpty should return original for non-empty!\n");
            return;
        }
        
        result = StringUtils.defaultIfEmpty("value", "default");
        System.out.println("defaultIfEmpty(\"value\", \"default\") = \"" + result + "\"");
        if (!"value".equals(result)) {
            System.out.println("ERROR: defaultIfEmpty should return original for non-empty!\n");
            return;
        }
        
        System.out.println("default value methods test passed.");
    }

    /**
     * Test the whitespace methods of StringUtils.
     * <p>
     * 测试StringUtils的空白字符方法。
     */
    private static void testWhitespaceMethods() {
        System.out.println("\n4. Testing whitespace methods...");
        
        // Test containsWhitespace
        boolean result = StringUtils.containsWhitespace(null);
        System.out.println("containsWhitespace(null) = " + result);
        if (result) {
            System.out.println("ERROR: containsWhitespace should return false for null!\n");
            return;
        }
        
        result = StringUtils.containsWhitespace("");
        System.out.println("containsWhitespace(\"\") = " + result);
        if (result) {
            System.out.println("ERROR: containsWhitespace should return false for empty string!\n");
            return;
        }
        
        result = StringUtils.containsWhitespace("no_whitespace");
        System.out.println("containsWhitespace(\"no_whitespace\") = " + result);
        if (result) {
            System.out.println("ERROR: containsWhitespace should return false for no whitespace!\n");
            return;
        }
        
        result = StringUtils.containsWhitespace("with whitespace");
        System.out.println("containsWhitespace(\"with whitespace\") = " + result);
        if (!result) {
            System.out.println("ERROR: containsWhitespace should return true for whitespace!\n");
            return;
        }
        
        result = StringUtils.containsWhitespace("with\ttab");
        System.out.println("containsWhitespace(\"with\\ttab\") = " + result);
        if (!result) {
            System.out.println("ERROR: containsWhitespace should return true for tab!\n");
            return;
        }
        
        // Test removeWhitespace
        String strResult = StringUtils.removeWhitespace(null);
        System.out.println("removeWhitespace(null) = " + strResult);
        if (strResult != null) {
            System.out.println("ERROR: removeWhitespace should return null for null!\n");
            return;
        }
        
        strResult = StringUtils.removeWhitespace("");
        System.out.println("removeWhitespace(\"\") = \"" + strResult + "\"");
        if (!"" .equals(strResult)) {
            System.out.println("ERROR: removeWhitespace should return empty string for empty!\n");
            return;
        }
        
        strResult = StringUtils.removeWhitespace("no_whitespace");
        System.out.println("removeWhitespace(\"no_whitespace\") = \"" + strResult + "\"");
        if (!"no_whitespace".equals(strResult)) {
            System.out.println("ERROR: removeWhitespace should return same string for no whitespace!\n");
            return;
        }
        
        strResult = StringUtils.removeWhitespace("with whitespace");
        System.out.println("removeWhitespace(\"with whitespace\") = \"" + strResult + "\"");
        if (!"withwhitespace".equals(strResult)) {
            System.out.println("ERROR: removeWhitespace should remove whitespace!\n");
            return;
        }
        
        strResult = StringUtils.removeWhitespace("  leading and trailing  ");
        System.out.println("removeWhitespace(\"  leading and trailing  \") = \"" + strResult + "\"");
        if (!"leadingandtrailing".equals(strResult)) {
            System.out.println("ERROR: removeWhitespace should remove all whitespace!\n");
            return;
        }
        
        System.out.println("whitespace methods test passed.");
    }

    /**
     * Test the equality methods of StringUtils.
     * <p>
     * 测试StringUtils的相等性方法。
     */
    private static void testEqualityMethods() {
        System.out.println("\n5. Testing equality methods...");
        
        // Test equals
        boolean result = StringUtils.equals(null, null);
        System.out.println("equals(null, null) = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for both null!\n");
            return;
        }
        
        result = StringUtils.equals(null, "test");
        System.out.println("equals(null, \"test\") = " + result);
        if (result) {
            System.out.println("ERROR: equals should return false for null and non-null!\n");
            return;
        }
        
        result = StringUtils.equals("test", "test");
        System.out.println("equals(\"test\", \"test\") = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for equal strings!\n");
            return;
        }
        
        result = StringUtils.equals("test1", "test2");
        System.out.println("equals(\"test1\", \"test2\") = " + result);
        if (result) {
            System.out.println("ERROR: equals should return false for different strings!\n");
            return;
        }
        
        // Test equalsIgnoreCase
        result = StringUtils.equalsIgnoreCase("Test", "test");
        System.out.println("equalsIgnoreCase(\"Test\", \"test\") = " + result);
        if (!result) {
            System.out.println("ERROR: equalsIgnoreCase should return true for case-insensitive equal!\n");
            return;
        }
        
        result = StringUtils.equalsIgnoreCase("TEST", "test");
        System.out.println("equalsIgnoreCase(\"TEST\", \"test\") = " + result);
        if (!result) {
            System.out.println("ERROR: equalsIgnoreCase should return true for case-insensitive equal!\n");
            return;
        }
        
        result = StringUtils.equalsIgnoreCase("Test1", "test2");
        System.out.println("equalsIgnoreCase(\"Test1\", \"test2\") = " + result);
        if (result) {
            System.out.println("ERROR: equalsIgnoreCase should return false for different strings!\n");
            return;
        }
        
        System.out.println("equality methods test passed.");
    }

    /**
     * Test the startsWith and endsWith methods of StringUtils.
     * <p>
     * 测试StringUtils的startsWith和endsWith方法。
     */
    private static void testStartEndMethods() {
        System.out.println("\n6. Testing startsWith/endsWith methods...");
        
        // Test startsWith
        boolean result = StringUtils.startsWith(null, null);
        System.out.println("startsWith(null, null) = " + result);
        if (!result) {
            System.out.println("ERROR: startsWith should return true for both null!\n");
            return;
        }
        
        result = StringUtils.startsWith(null, "prefix");
        System.out.println("startsWith(null, \"prefix\") = " + result);
        if (result) {
            System.out.println("ERROR: startsWith should return false for null string!\n");
            return;
        }
        
        result = StringUtils.startsWith("test", null);
        System.out.println("startsWith(\"test\", null) = " + result);
        if (result) {
            System.out.println("ERROR: startsWith should return false for null prefix!\n");
            return;
        }
        
        result = StringUtils.startsWith("test", "te");
        System.out.println("startsWith(\"test\", \"te\") = " + result);
        if (!result) {
            System.out.println("ERROR: startsWith should return true for matching prefix!\n");
            return;
        }
        
        result = StringUtils.startsWith("test", "es");
        System.out.println("startsWith(\"test\", \"es\") = " + result);
        if (result) {
            System.out.println("ERROR: startsWith should return false for non-matching prefix!\n");
            return;
        }
        
        // Test endsWith
        result = StringUtils.endsWith(null, null);
        System.out.println("endsWith(null, null) = " + result);
        if (!result) {
            System.out.println("ERROR: endsWith should return true for both null!\n");
            return;
        }
        
        result = StringUtils.endsWith("test", "st");
        System.out.println("endsWith(\"test\", \"st\") = " + result);
        if (!result) {
            System.out.println("ERROR: endsWith should return true for matching suffix!\n");
            return;
        }
        
        result = StringUtils.endsWith("test", "es");
        System.out.println("endsWith(\"test\", \"es\") = " + result);
        if (result) {
            System.out.println("ERROR: endsWith should return false for non-matching suffix!\n");
            return;
        }
        
        System.out.println("startsWith/endsWith methods test passed.");
    }

    /**
     * Test the substring methods of StringUtils.
     * <p>
     * 测试StringUtils的substring方法。
     */
    private static void testSubstringMethods() {
        System.out.println("\n7. Testing substring methods...");
        
        // Test substring(String, int)
        String result = StringUtils.substring(null, 0);
        System.out.println("substring(null, 0) = " + result);
        if (result != null) {
            System.out.println("ERROR: substring should return null for null string!\n");
            return;
        }
        
        result = StringUtils.substring("test", 0);
        System.out.println("substring(\"test\", 0) = \"" + result + "\"");
        if (!"test".equals(result)) {
            System.out.println("ERROR: substring should return full string for start 0!\n");
            return;
        }
        
        result = StringUtils.substring("test", 2);
        System.out.println("substring(\"test\", 2) = \"" + result + "\"");
        if (!"st".equals(result)) {
            System.out.println("ERROR: substring should return from index 2!\n");
            return;
        }
        
        result = StringUtils.substring("test", -2);
        System.out.println("substring(\"test\", -2) = \"" + result + "\"");
        if (!"st".equals(result)) {
            System.out.println("ERROR: substring should handle negative index!\n");
            return;
        }
        
        result = StringUtils.substring("test", 10);
        System.out.println("substring(\"test\", 10) = \"" + result + "\"");
        if (!"" .equals(result)) {
            System.out.println("ERROR: substring should return empty for index too large!\n");
            return;
        }
        
        // Test substring(String, int, int)
        result = StringUtils.substring(null, 0, 0);
        System.out.println("substring(null, 0, 0) = " + result);
        if (result != null) {
            System.out.println("ERROR: substring should return null for null string!\n");
            return;
        }
        
        result = StringUtils.substring("test", 0, 2);
        System.out.println("substring(\"test\", 0, 2) = \"" + result + "\"");
        if (!"te".equals(result)) {
            System.out.println("ERROR: substring should return from 0 to 2!\n");
            return;
        }
        
        result = StringUtils.substring("test", 1, 3);
        System.out.println("substring(\"test\", 1, 3) = \"" + result + "\"");
        if (!"es".equals(result)) {
            System.out.println("ERROR: substring should return from 1 to 3!\n");
            return;
        }
        
        result = StringUtils.substring("test", -3, -1);
        System.out.println("substring(\"test\", -3, -1) = \"" + result + "\"");
        if (!"es".equals(result)) {
            System.out.println("ERROR: substring should handle negative indices!\n");
            return;
        }
        
        result = StringUtils.substring("test", 2, 1);
        System.out.println("substring(\"test\", 2, 1) = \"" + result + "\"");
        if (!"" .equals(result)) {
            System.out.println("ERROR: substring should return empty for start > end!\n");
            return;
        }
        
        System.out.println("substring methods test passed.");
    }
}
