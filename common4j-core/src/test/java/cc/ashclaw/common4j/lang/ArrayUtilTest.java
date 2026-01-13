// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Test class for ArrayUtil result verification.
 * <p>
 * ArrayUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ArrayUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ArrayUtil Result Verification Test Start =====");
        
        // Test null/empty checking methods
        testNullEmptyChecks();
        
        // Test length methods
        testLengthMethods();
        
        // Test element access methods
        testElementAccessMethods();
        
        // Test array manipulation methods
        testArrayManipulationMethods();
        
        // Test comparison methods
        testComparisonMethods();
        
        System.out.println("===== ArrayUtil Result Verification Test End =====");
    }

    /**
     * Test the null and empty checking methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的null和空值检查方法。
     */
    private static void testNullEmptyChecks() {
        System.out.println("\n1. Testing null/empty checking methods...");
        
        // Test isNull
        boolean result = ArrayUtil.isNull(null);
        System.out.println("isNull(null) = " + result);
        if (!result) {
            System.out.println("ERROR: isNull should return true for null!");
            return;
        }
        
        result = ArrayUtil.isNull(new String[]{});
        System.out.println("isNull(new String[]{}) = " + result);
        if (result) {
            System.out.println("ERROR: isNull should return false for empty array!");
            return;
        }
        
        // Test nonNull
        result = ArrayUtil.nonNull(null);
        System.out.println("nonNull(null) = " + result);
        if (result) {
            System.out.println("ERROR: nonNull should return false for null!");
            return;
        }
        
        result = ArrayUtil.nonNull(new String[]{});
        System.out.println("nonNull(new String[]{}) = " + result);
        if (!result) {
            System.out.println("ERROR: nonNull should return true for non-null array!");
            return;
        }
        
        // Test isEmpty
        result = ArrayUtil.isEmpty(null);
        System.out.println("isEmpty(null) = " + result);
        if (!result) {
            System.out.println("ERROR: isEmpty should return true for null!");
            return;
        }
        
        result = ArrayUtil.isEmpty(new String[]{});
        System.out.println("isEmpty(new String[]{}) = " + result);
        if (!result) {
            System.out.println("ERROR: isEmpty should return true for empty array!");
            return;
        }
        
        result = ArrayUtil.isEmpty(new String[]{"test"});
        System.out.println("isEmpty(new String[]{\"test\"}) = " + result);
        if (result) {
            System.out.println("ERROR: isEmpty should return false for non-empty array!");
            return;
        }
        
        // Test isNotEmpty
        result = ArrayUtil.isNotEmpty(null);
        System.out.println("isNotEmpty(null) = " + result);
        if (result) {
            System.out.println("ERROR: isNotEmpty should return false for null!");
            return;
        }
        
        result = ArrayUtil.isNotEmpty(new String[]{});
        System.out.println("isNotEmpty(new String[]{}) = " + result);
        if (result) {
            System.out.println("ERROR: isNotEmpty should return false for empty array!");
            return;
        }
        
        result = ArrayUtil.isNotEmpty(new String[]{"test"});
        System.out.println("isNotEmpty(new String[]{\"test\"}) = " + result);
        if (!result) {
            System.out.println("ERROR: isNotEmpty should return true for non-empty array!");
            return;
        }
        
        System.out.println("null/empty checking methods test passed.");
    }

    /**
     * Test the length methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的长度方法。
     */
    private static void testLengthMethods() {
        System.out.println("\n2. Testing length methods...");
        
        // Test length
        int result = ArrayUtil.length(null);
        System.out.println("length(null) = " + result);
        if (result != 0) {
            System.out.println("ERROR: length should return 0 for null!");
            return;
        }
        
        result = ArrayUtil.length(new String[]{});
        System.out.println("length(new String[]{}) = " + result);
        if (result != 0) {
            System.out.println("ERROR: length should return 0 for empty array!");
            return;
        }
        
        result = ArrayUtil.length(new String[]{"a", "b", "c"});
        System.out.println("length(new String[]{\"a\", \"b\", \"c\"}) = " + result);
        if (result != 3) {
            System.out.println("ERROR: length should return correct length!");
            return;
        }
        
        System.out.println("length methods test passed.");
    }

    /**
     * Test the element access methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的元素访问方法。
     */
    private static void testElementAccessMethods() {
        System.out.println("\n3. Testing element access methods...");
        
        String[] array = {"first", "second", "third"};
        
        // Test getFirst
        String result = ArrayUtil.getFirst(null);
        System.out.println("getFirst(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: getFirst should return null for null!");
            return;
        }
        
        result = ArrayUtil.getFirst(new String[]{});
        System.out.println("getFirst(new String[]{}) = " + result);
        if (result != null) {
            System.out.println("ERROR: getFirst should return null for empty array!");
            return;
        }
        
        result = ArrayUtil.getFirst(array);
        System.out.println("getFirst(array) = \"" + result + "\"");
        if (!"first".equals(result)) {
            System.out.println("ERROR: getFirst should return first element!");
            return;
        }
        
        // Test getLast
        result = ArrayUtil.getLast(null);
        System.out.println("getLast(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: getLast should return null for null!");
            return;
        }
        
        result = ArrayUtil.getLast(new String[]{});
        System.out.println("getLast(new String[]{}) = " + result);
        if (result != null) {
            System.out.println("ERROR: getLast should return null for empty array!");
            return;
        }
        
        result = ArrayUtil.getLast(array);
        System.out.println("getLast(array) = \"" + result + "\"");
        if (!"third".equals(result)) {
            System.out.println("ERROR: getLast should return last element!");
            return;
        }
        
        // Test get
        result = ArrayUtil.get(null, 0);
        System.out.println("get(null, 0) = " + result);
        if (result != null) {
            System.out.println("ERROR: get should return null for null array!");
            return;
        }
        
        result = ArrayUtil.get(array, 1);
        System.out.println("get(array, 1) = \"" + result + "\"");
        if (!"second".equals(result)) {
            System.out.println("ERROR: get should return correct element!");
            return;
        }
        
        result = ArrayUtil.get(array, 10);
        System.out.println("get(array, 10) = " + result);
        if (result != null) {
            System.out.println("ERROR: get should return null for index out of bounds!");
            return;
        }
        
        System.out.println("element access methods test passed.");
    }

    /**
     * Test the array manipulation methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的数组操作方法。
     */
    private static void testArrayManipulationMethods() {
        System.out.println("\n4. Testing array manipulation methods...");
        
        // Test clone
        String[] array = {"a", "b", "c"};
        String[] cloned = ArrayUtil.clone(array);
        System.out.println("clone(array) = " + (cloned != null ? "[cloned]" : "null"));
        if (cloned == null) {
            System.out.println("ERROR: clone should not return null for non-null array!");
            return;
        }
        
        // Test concat
        String[] array1 = {"a", "b"};
        String[] array2 = {"c", "d"};
        String[] concatenated = ArrayUtil.concat(array1, array2);
        System.out.println("concat(array1, array2) length = " + (concatenated != null ? concatenated.length : 0));
        if (concatenated == null || concatenated.length != 4) {
            System.out.println("ERROR: concat should combine arrays correctly!");
            return;
        }
        
        // Test contains
        boolean result = ArrayUtil.contains(array, "b");
        System.out.println("contains(array, \"b\") = " + result);
        if (!result) {
            System.out.println("ERROR: contains should find existing element!");
            return;
        }
        
        result = ArrayUtil.contains(array, "z");
        System.out.println("contains(array, \"z\") = " + result);
        if (result) {
            System.out.println("ERROR: contains should not find non-existing element!");
            return;
        }
        
        System.out.println("array manipulation methods test passed.");
    }

    /**
     * Test the comparison methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的比较方法。
     */
    private static void testComparisonMethods() {
        System.out.println("\n5. Testing comparison methods...");
        
        // Test equals
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a", "b", "c"};
        String[] array3 = {"x", "y", "z"};
        
        boolean result = ArrayUtil.equals(array1, array2);
        System.out.println("equals(array1, array2) = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for equal arrays!");
            return;
        }
        
        result = ArrayUtil.equals(array1, array3);
        System.out.println("equals(array1, array3) = " + result);
        if (result) {
            System.out.println("ERROR: equals should return false for different arrays!");
            return;
        }
        
        result = ArrayUtil.equals(null, null);
        System.out.println("equals(null, null) = " + result);
        if (!result) {
            System.out.println("ERROR: equals should return true for both null!");
            return;
        }
        
        System.out.println("comparison methods test passed.");
    }
}
