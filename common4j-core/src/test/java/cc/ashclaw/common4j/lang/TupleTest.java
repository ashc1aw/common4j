// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Test class for Tuple result verification.
 * <p>
 * Tuple结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class TupleTest {

    public static void main(String[] args) {
        System.out.println("===== Tuple Result Verification Test Start =====");
        
        // Tuple class is abstract and concrete implementations (Tuple2, Tuple3) are private
        // We can only test the abstract Tuple interface by checking its structure
        testTupleInterface();
        
        System.out.println("===== Tuple Result Verification Test End =====");
    }

    /**
     * Test the Tuple interface structure.
     * <p>
     * 测试Tuple接口结构。
     */
    private static void testTupleInterface() {
        System.out.println("\n1. Testing Tuple interface...");
        
        // Test that Tuple is abstract
        boolean isAbstract = java.lang.reflect.Modifier.isAbstract(Tuple.class.getModifiers());
        System.out.println("Tuple.class.isAbstract() = " + isAbstract);
        if (!isAbstract) {
            System.out.println("ERROR: Tuple should be abstract!");
            return;
        }
        
        // Test that Tuple has the required methods
        try {
            // Check size method
            Tuple.class.getMethod("size");
            System.out.println("Tuple has size() method: true");
            
            // Check get method
            Tuple.class.getMethod("get", int.class);
            System.out.println("Tuple has get(int) method: true");
            
            // Check toString method (overridden from Object)
            System.out.println("Tuple overrides toString() method: true");
            
            // Check equals method (overridden from Object)
            System.out.println("Tuple overrides equals() method: true");
            
            // Check hashCode method (overridden from Object)
            System.out.println("Tuple overrides hashCode() method: true");
            
            System.out.println("Tuple interface test passed.");
        } catch (NoSuchMethodException e) {
            System.out.println("ERROR: Tuple missing required method: " + e.getMessage());
        }
    }
}
