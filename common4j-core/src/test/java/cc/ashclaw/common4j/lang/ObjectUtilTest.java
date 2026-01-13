// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Test class for ObjectUtil result verification.
 * <p>
 * ObjectUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ObjectUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ObjectUtil Result Verification Test Start =====");
        
        // Test null checking methods
        testNullCheckingMethods();
        
        // Test equality methods
        testEqualityMethods();
        
        // Test default value methods
        testDefaultValueMethods();
        
        // Test hash code methods
        testHashCodeMethods();
        
        // Test object conversion methods
        testObjectConversionMethods();
        
        System.out.println("===== ObjectUtil Result Verification Test End =====");
    }

    /**
     * Test the null checking methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的null检查方法。
     */
    private static void testNullCheckingMethods() {
        System.out.println("\n1. Testing null checking methods...");
        
        // Test isNull
        boolean result = ObjectUtil.isNull(null);
        System.out.println("isNull(null) = " + result);
        if (!result) {
            System.out.println("ERROR: isNull should return true for null!");
            return;
        }
        
        result = ObjectUtil.isNull("test");
        System.out.println("isNull(test) = " + result);
        if (result) {
            System.out.println("ERROR: isNull should return false for non-null!");
            return;
        }
        
        // Test nonNull
        result = ObjectUtil.nonNull(null);
        System.out.println("nonNull(null) = " + result);
        if (result) {
            System.out.println("ERROR: nonNull should return false for null!");
            return;
        }
        
        result = ObjectUtil.nonNull("test");
        System.out.println("nonNull(test) = " + result);
        if (!result) {
            System.out.println("ERROR: nonNull should return true for non-null!");
            return;
        }
        
        System.out.println("null checking methods test passed.");
    }

    /**
     * Test the equality methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的相等性方法。
     */
    private static void testEqualityMethods() {
        System.out.println("\n2. Testing equality methods...");
        
        // Test equals
        boolean result = ObjectUtil.equals(null, null);
        System.out.println("equals(null, null) = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for both null!");
            return;
        }
        
        result = ObjectUtil.equals(null, "test");
        System.out.println("equals(null, \"test\") = " + result);
        if (result) {
            System.out.println("ERROR: equals should return false for null and non-null!");
            return;
        }
        
        result = ObjectUtil.equals("test", "test");
        System.out.println("equals(\"test\", \"test\") = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for equal objects!");
            return;
        }
        
        result = ObjectUtil.equals("test1", "test2");
        System.out.println("equals(\"test1\", \"test2\") = " + result);
        if (result) {
            System.out.println("ERROR: equals should return false for different objects!");
            return;
        }
        
        System.out.println("equality methods test passed.");
    }

    /**
     * Test the default value methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的默认值方法。
     */
    private static void testDefaultValueMethods() {
        System.out.println("\n3. Testing default value methods...");
        
        // Test defaultIfNull
        String result = ObjectUtil.defaultIfNull(null, "default");
        System.out.println("defaultIfNull(null, \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: defaultIfNull should return default for null!");
            return;
        }
        
        result = ObjectUtil.defaultIfNull("value", "default");
        System.out.println("defaultIfNull(\"value\", \"default\") = \"" + result + "\"");
        if (!"value".equals(result)) {
            System.out.println("ERROR: defaultIfNull should return original for non-null!");
            return;
        }
        
        // Test firstNonNull
        String firstNonNull = ObjectUtil.firstNonNull(null, "second", "third");
        System.out.println("firstNonNull(null, \"second\", \"third\") = \"" + firstNonNull + "\"");
        if (!"second".equals(firstNonNull)) {
            System.out.println("ERROR: firstNonNull should return first non-null value!");
            return;
        }
        
        System.out.println("default value methods test passed.");
    }

    /**
     * Test the hash code methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的哈希码方法。
     */
    private static void testHashCodeMethods() {
        System.out.println("\n4. Testing hash code methods...");
        
        // Test hashCode
        int result = ObjectUtil.hashCode(null);
        System.out.println("hashCode(null) = " + result);
        if (result != 0) {
            System.out.println("ERROR: hashCode should return 0 for null!");
            return;
        }
        
        result = ObjectUtil.hashCode("test");
        System.out.println("hashCode(\"test\") = " + result);
        if (result == 0) {
            System.out.println("ERROR: hashCode should not return 0 for non-null object!");
            return;
        }
        
        System.out.println("hash code methods test passed.");
    }

    /**
     * Test the object conversion methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的对象转换方法。
     */
    private static void testObjectConversionMethods() {
        System.out.println("\n5. Testing object conversion methods...");
        
        // Test toString
        String result = ObjectUtil.toString(null);
        System.out.println("toString(null) = \"" + result + "\"");
        if (!"null".equals(result)) {
            System.out.println("ERROR: toString should return \"null\" for null!");
            return;
        }
        
        result = ObjectUtil.toString("test");
        System.out.println("toString(\"test\") = \"" + result + "\"");
        if (!"test".equals(result)) {
            System.out.println("ERROR: toString should return object's toString() result!");
            return;
        }
        
        // Test toString with default value
        result = ObjectUtil.toString(null, "default");
        System.out.println("toString(null, \"default\") = \"" + result + "\"");
        if (!"default".equals(result)) {
            System.out.println("ERROR: toString should return default for null!");
            return;
        }
        
        System.out.println("object conversion methods test passed.");
    }
}
