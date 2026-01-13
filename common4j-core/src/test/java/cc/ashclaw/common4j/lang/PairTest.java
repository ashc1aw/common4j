// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Test class for Pair result verification.
 * <p>
 * Pair结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class PairTest {

    public static void main(String[] args) {
        System.out.println("===== Pair Result Verification Test Start =====");
        
        // Test creation methods
        testCreationMethods();
        
        // Test access methods
        testAccessMethods();
        
        // Test equality methods
        testEqualityMethods();
        
        // Test hash code methods
        testHashCodeMethods();
        
        // Test toString methods
        testToStringMethods();
        
        System.out.println("===== Pair Result Verification Test End =====");
    }

    /**
     * Test the creation methods of Pair.
     * <p>
     * 测试Pair的创建方法。
     */
    private static void testCreationMethods() {
        System.out.println("\n1. Testing creation methods...");
        
        // Test constructor
        Pair<String, Integer> pair1 = new Pair<>("key", 123);
        System.out.println("new Pair<>(\"key\", 123) = " + (pair1 != null ? "created" : "null"));
        if (pair1 == null) {
            System.out.println("ERROR: Pair constructor should not return null!");
            return;
        }
        
        // Test of method
        Pair<String, Integer> pair2 = Pair.of("key2", 456);
        System.out.println("Pair.of(\"key2\", 456) = " + (pair2 != null ? "created" : "null"));
        if (pair2 == null) {
            System.out.println("ERROR: Pair.of should not return null!");
            return;
        }
        
        // Test with null values
        Pair<String, Integer> pair3 = Pair.of(null, null);
        System.out.println("Pair.of(null, null) = " + (pair3 != null ? "created" : "null"));
        if (pair3 == null) {
            System.out.println("ERROR: Pair.of should handle null values!");
            return;
        }
        
        System.out.println("creation methods test passed.");
    }

    /**
     * Test the access methods of Pair.
     * <p>
     * 测试Pair的访问方法。
     */
    private static void testAccessMethods() {
        System.out.println("\n2. Testing access methods...");
        
        Pair<String, Integer> pair = Pair.of("testKey", 789);
        
        // Test getKey
        String key = pair.getKey();
        System.out.println("pair.getKey() = \"" + key + "\"");
        if (!"testKey".equals(key)) {
            System.out.println("ERROR: getKey should return correct key!");
            return;
        }
        
        // Test getValue
        Integer value = pair.getValue();
        System.out.println("pair.getValue() = " + value);
        if (value != 789) {
            System.out.println("ERROR: getValue should return correct value!");
            return;
        }
        
        // Test with null values
        Pair<String, Integer> nullPair = Pair.of(null, null);
        System.out.println("nullPair.getKey() = " + nullPair.getKey());
        System.out.println("nullPair.getValue() = " + nullPair.getValue());
        if (nullPair.getKey() != null || nullPair.getValue() != null) {
            System.out.println("ERROR: Pair should handle null values correctly!");
            return;
        }
        
        System.out.println("access methods test passed.");
    }

    /**
     * Test the equality methods of Pair.
     * <p>
     * 测试Pair的相等性方法。
     */
    private static void testEqualityMethods() {
        System.out.println("\n3. Testing equality methods...");
        
        Pair<String, Integer> pair1 = Pair.of("key", 123);
        Pair<String, Integer> pair2 = Pair.of("key", 123);
        Pair<String, Integer> pair3 = Pair.of("different", 123);
        Pair<String, Integer> pair4 = Pair.of("key", 456);
        
        // Test same object
        boolean result = pair1.equals(pair1);
        System.out.println("pair1.equals(pair1) = " + result);
        if (!result) {
            System.out.println("ERROR: Pair should be equal to itself!");
            return;
        }
        
        // Test equal pairs
        result = pair1.equals(pair2);
        System.out.println("pair1.equals(pair2) = " + result);
        if (!result) {
            System.out.println("ERROR: Pairs with same key and value should be equal!");
            return;
        }
        
        // Test different key
        result = pair1.equals(pair3);
        System.out.println("pair1.equals(pair3) = " + result);
        if (result) {
            System.out.println("ERROR: Pairs with different keys should not be equal!");
            return;
        }
        
        // Test different value
        result = pair1.equals(pair4);
        System.out.println("pair1.equals(pair4) = " + result);
        if (result) {
            System.out.println("ERROR: Pairs with different values should not be equal!");
            return;
        }
        
        // Test with null
        result = pair1.equals(null);
        System.out.println("pair1.equals(null) = " + result);
        if (result) {
            System.out.println("ERROR: Pair should not be equal to null!");
            return;
        }
        
        // Test with different type
        result = pair1.equals("not a pair");
        System.out.println("pair1.equals(\"not a pair\") = " + result);
        if (result) {
            System.out.println("ERROR: Pair should not be equal to different type!");
            return;
        }
        
        System.out.println("equality methods test passed.");
    }

    /**
     * Test the hash code methods of Pair.
     * <p>
     * 测试Pair的哈希码方法。
     */
    private static void testHashCodeMethods() {
        System.out.println("\n4. Testing hash code methods...");
        
        Pair<String, Integer> pair1 = Pair.of("key", 123);
        Pair<String, Integer> pair2 = Pair.of("key", 123);
        Pair<String, Integer> pair3 = Pair.of("different", 123);
        
        // Test same hash code for equal pairs
        int hash1 = pair1.hashCode();
        int hash2 = pair2.hashCode();
        System.out.println("pair1.hashCode() = " + hash1);
        System.out.println("pair2.hashCode() = " + hash2);
        if (hash1 != hash2) {
            System.out.println("ERROR: Equal pairs should have same hash code!");
            return;
        }
        
        // Test different hash code for different pairs
        int hash3 = pair3.hashCode();
        System.out.println("pair3.hashCode() = " + hash3);
        // Note: Different objects can have same hash code, but it's unlikely here
        
        System.out.println("hash code methods test passed.");
    }

    /**
     * Test the toString methods of Pair.
     * <p>
     * 测试Pair的toString方法。
     */
    private static void testToStringMethods() {
        System.out.println("\n5. Testing toString methods...");
        
        Pair<String, Integer> pair = Pair.of("testKey", 789);
        String result = pair.toString();
        System.out.println("pair.toString() = \"" + result + "\"");
        if (result == null || result.isEmpty()) {
            System.out.println("ERROR: toString should not return null or empty!");
            return;
        }
        
        // Test toString contains key and value
        if (!result.contains("testKey") || !result.contains("789")) {
            System.out.println("ERROR: toString should contain key and value!");
            return;
        }
        
        System.out.println("toString methods test passed.");
    }
}
