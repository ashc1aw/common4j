// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for TreeUtil result verification.
 * <p>
 * TreeUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class TreeUtilTest {

    // Test node class implementing TreeNode interface
    private static class TestNode implements TreeUtil.TreeNode<Integer, TestNode> {
        private Integer id;
        private Integer parentId;
        private List<TestNode> children;
        private String name;

        public TestNode(Integer id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.children = new ArrayList<>();
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public Integer getParentId() {
            return parentId;
        }

        @Override
        public List<TestNode> getChildren() {
            return children;
        }

        @Override
        public void setChildren(List<TestNode> children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "TestNode{id=" + id + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args) {
        System.out.println("===== TreeUtil Result Verification Test Start =====");
        
        // Test buildTree method
        testBuildTree();
        
        // Test toFlatList method
        testToFlatList();
        
        // Test findNode method
        testFindNode();
        
        // Test getMaxDepth method
        testGetMaxDepth();
        
        // Test traversal methods
        testTraversalMethods();
        
        // Test edge cases
        testEdgeCases();
        
        System.out.println("===== TreeUtil Result Verification Test End =====");
    }

    /**
     * Test the buildTree method of TreeUtil.
     * <p>
     * 测试TreeUtil的buildTree方法。
     */
    private static void testBuildTree() {
        System.out.println("\n1. Testing buildTree method...");
        
        // Create flat list of nodes
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root1"));
        flatNodes.add(new TestNode(2, null, "Root2"));
        flatNodes.add(new TestNode(3, 1, "Child1-1"));
        flatNodes.add(new TestNode(4, 1, "Child1-2"));
        flatNodes.add(new TestNode(5, 3, "Child1-1-1"));
        flatNodes.add(new TestNode(6, 3, "Child1-1-2"));
        flatNodes.add(new TestNode(7, 2, "Child2-1"));
        
        // Build tree
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        System.out.println("Root nodes count: " + rootNodes.size());
        if (rootNodes.size() != 2) {
            System.out.println("ERROR: buildTree should return 2 root nodes!\n");
            return;
        }
        
        // Verify tree structure
        TestNode root1 = rootNodes.get(0);
        TestNode root2 = rootNodes.get(1);
        
        if (root1.getId() != 1 || !"Root1".equals(root1.getName())) {
            System.out.println("ERROR: Root1 has incorrect properties!\n");
            return;
        }
        
        if (root2.getId() != 2 || !"Root2".equals(root2.getName())) {
            System.out.println("ERROR: Root2 has incorrect properties!\n");
            return;
        }
        
        List<TestNode> root1Children = root1.getChildren();
        System.out.println("Root1 children count: " + root1Children.size());
        if (root1Children.size() != 2) {
            System.out.println("ERROR: Root1 should have 2 children!\n");
            return;
        }
        
        List<TestNode> root2Children = root2.getChildren();
        System.out.println("Root2 children count: " + root2Children.size());
        if (root2Children.size() != 1) {
            System.out.println("ERROR: Root2 should have 1 child!\n");
            return;
        }
        
        System.out.println("buildTree method test passed.");
    }

    /**
     * Test the toFlatList method of TreeUtil.
     * <p>
     * 测试TreeUtil的toFlatList方法。
     */
    private static void testToFlatList() {
        System.out.println("\n2. Testing toFlatList method...");
        
        // Create tree structure
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root1"));
        flatNodes.add(new TestNode(2, null, "Root2"));
        flatNodes.add(new TestNode(3, 1, "Child1-1"));
        flatNodes.add(new TestNode(4, 1, "Child1-2"));
        flatNodes.add(new TestNode(5, 3, "Child1-1-1"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        
        // Convert tree to flat list
        List<TestNode> flatList = TreeUtil.toFlatList(rootNodes);
        System.out.println("Flat list size: " + flatList.size());
        if (flatList.size() != 5) {
            System.out.println("ERROR: toFlatList should return 5 nodes!\n");
            return;
        }
        
        // Verify flat list contains all nodes
        System.out.println("Flat list nodes: ");
        for (TestNode node : flatList) {
            System.out.println("  " + node);
        }
        
        // Just verify all nodes are present, not the exact order
        boolean allNodesPresent = true;
        Integer[] expectedIds = {1, 2, 3, 4, 5};
        for (Integer id : expectedIds) {
            boolean found = flatList.stream().anyMatch(node -> id.equals(node.getId()));
            if (!found) {
                allNodesPresent = false;
                break;
            }
        }
        
        if (!allNodesPresent) {
            System.out.println("ERROR: toFlatList is missing some nodes!\n");
            return;
        }
        
        System.out.println("toFlatList method test passed.");
    }

    /**
     * Test the findNode method of TreeUtil.
     * <p>
     * 测试TreeUtil的findNode方法。
     */
    private static void testFindNode() {
        System.out.println("\n3. Testing findNode method...");
        
        // Create tree structure
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root1"));
        flatNodes.add(new TestNode(2, 1, "Child1-1"));
        flatNodes.add(new TestNode(3, 2, "Child1-1-1"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        
        // Find existing node
        TestNode foundNode = TreeUtil.findNode(rootNodes, 3);
        System.out.println("Found node: " + foundNode);
        if (foundNode == null || foundNode.getId() != 3 || !"Child1-1-1".equals(foundNode.getName())) {
            System.out.println("ERROR: findNode should find existing node!\n");
            return;
        }
        
        // Find non-existing node
        foundNode = TreeUtil.findNode(rootNodes, 999);
        System.out.println("Find non-existing node: " + foundNode);
        if (foundNode != null) {
            System.out.println("ERROR: findNode should return null for non-existing node!\n");
            return;
        }
        
        // Find root node
        foundNode = TreeUtil.findNode(rootNodes, 1);
        System.out.println("Find root node: " + foundNode);
        if (foundNode == null || foundNode.getId() != 1) {
            System.out.println("ERROR: findNode should find root node!\n");
            return;
        }
        
        System.out.println("findNode method test passed.");
    }

    /**
     * Test the getMaxDepth method of TreeUtil.
     * <p>
     * 测试TreeUtil的getMaxDepth方法。
     */
    private static void testGetMaxDepth() {
        System.out.println("\n4. Testing getMaxDepth method...");
        
        // Test empty tree
        int depth = TreeUtil.getMaxDepth(new ArrayList<TestNode>());
        System.out.println("Empty tree depth: " + depth);
        if (depth != 0) {
            System.out.println("ERROR: getMaxDepth should return 0 for empty tree!\n");
            return;
        }
        
        // Test single node tree
        List<TestNode> singleNode = new ArrayList<>();
        singleNode.add(new TestNode(1, null, "SingleNode"));
        depth = TreeUtil.getMaxDepth(singleNode);
        System.out.println("Single node tree depth: " + depth);
        if (depth != 1) {
            System.out.println("ERROR: getMaxDepth should return 1 for single node!\n");
            return;
        }
        
        // Test multi-level tree
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Level1"));
        flatNodes.add(new TestNode(2, 1, "Level2"));
        flatNodes.add(new TestNode(3, 2, "Level3"));
        flatNodes.add(new TestNode(4, 3, "Level4"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        depth = TreeUtil.getMaxDepth(rootNodes);
        System.out.println("Multi-level tree depth: " + depth);
        if (depth != 4) {
            System.out.println("ERROR: getMaxDepth should return 4 for 4-level tree!\n");
            return;
        }
        
        // Test multi-root tree with different depths
        List<TestNode> multiRootNodes = new ArrayList<>();
        multiRootNodes.add(new TestNode(1, null, "Root1"));
        multiRootNodes.add(new TestNode(2, null, "Root2"));
        multiRootNodes.add(new TestNode(3, 1, "Child1-1"));
        multiRootNodes.add(new TestNode(4, 3, "Child1-1-1"));
        multiRootNodes.add(new TestNode(5, 2, "Child2-1"));
        
        List<TestNode> builtRoots = TreeUtil.buildTree(multiRootNodes);
        depth = TreeUtil.getMaxDepth(builtRoots);
        System.out.println("Multi-root tree max depth: " + depth);
        if (depth != 3) {
            System.out.println("ERROR: getMaxDepth should return 3 for max depth!\n");
            return;
        }
        
        System.out.println("getMaxDepth method test passed.");
    }

    /**
     * Test the traversal methods of TreeUtil.
     * <p>
     * 测试TreeUtil的遍历方法。
     */
    private static void testTraversalMethods() {
        System.out.println("\n5. Testing traversal methods...");
        
        // Create tree structure
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root"));
        flatNodes.add(new TestNode(2, 1, "Child1"));
        flatNodes.add(new TestNode(3, 1, "Child2"));
        flatNodes.add(new TestNode(4, 2, "Child1-1"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        
        // Test pre-order traversal
        System.out.println("Pre-order traversal:");
        List<Integer> preOrderIds = new ArrayList<>();
        TreeUtil.preOrderTraversal(rootNodes, node -> {
            System.out.println("  " + node);
            preOrderIds.add(node.getId());
        });
        
        Integer[] expectedPreOrder = {1, 2, 4, 3};
        for (int i = 0; i < expectedPreOrder.length; i++) {
            if (!expectedPreOrder[i].equals(preOrderIds.get(i))) {
                System.out.println("ERROR: preOrderTraversal has incorrect order!\n");
                return;
            }
        }
        
        // Test post-order traversal
        System.out.println("Post-order traversal:");
        List<Integer> postOrderIds = new ArrayList<>();
        TreeUtil.postOrderTraversal(rootNodes, node -> {
            System.out.println("  " + node);
            postOrderIds.add(node.getId());
        });
        
        Integer[] expectedPostOrder = {4, 2, 3, 1};
        for (int i = 0; i < expectedPostOrder.length; i++) {
            if (!expectedPostOrder[i].equals(postOrderIds.get(i))) {
                System.out.println("ERROR: postOrderTraversal has incorrect order!\n");
                return;
            }
        }
        
        System.out.println("Traversal methods test passed.");
    }

    /**
     * Test edge cases for TreeUtil methods.
     * <p>
     * 测试TreeUtil方法的边界情况。
     */
    private static void testEdgeCases() {
        System.out.println("\n6. Testing edge cases...");
        
        // Test buildTree with empty list
        List<TestNode> emptyList = new ArrayList<>();
        List<TestNode> result = TreeUtil.buildTree(emptyList);
        System.out.println("buildTree with empty list: " + result);
        if (result == null || result.size() != 0) {
            System.out.println("ERROR: buildTree should return empty list for empty input!\n");
            return;
        }
        
        // Test buildTree with single node
        List<TestNode> singleNodeList = new ArrayList<>();
        singleNodeList.add(new TestNode(1, null, "SingleNode"));
        result = TreeUtil.buildTree(singleNodeList);
        System.out.println("buildTree with single node: " + result);
        if (result.size() != 1) {
            System.out.println("ERROR: buildTree should return single root for single node!\n");
            return;
        }
        
        // Test toFlatList with empty list
        List<TestNode> flatResult = TreeUtil.toFlatList(new ArrayList<TestNode>());
        System.out.println("toFlatList with empty list: " + flatResult);
        if (flatResult == null || flatResult.size() != 0) {
            System.out.println("ERROR: toFlatList should return empty list for empty input!\n");
            return;
        }
        
        // Test findNode with empty tree
        TestNode found = TreeUtil.findNode(new ArrayList<TestNode>(), 1);
        System.out.println("findNode with empty tree: " + found);
        if (found != null) {
            System.out.println("ERROR: findNode should return null for empty tree!\n");
            return;
        }
        
        // Test cycle detection (should throw exception)
        System.out.println("Testing cycle detection...");
        List<TestNode> cyclicNodes = new ArrayList<>();
        cyclicNodes.add(new TestNode(1, null, "Root")); // Root node
        cyclicNodes.add(new TestNode(2, 1, "Child"));  // Child of root
        cyclicNodes.add(new TestNode(3, 2, "Grandchild")); // Child of child
        cyclicNodes.add(new TestNode(2, 3, "CycleNode")); // Cycle: Node2's parent is Node3
        
        try {
            TreeUtil.buildTree(cyclicNodes);
            System.out.println("ERROR: buildTree should detect cycle!\n");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Cycle detection works: " + e.getMessage());
        }
        
        System.out.println("Edge cases test passed.");
    }
}
