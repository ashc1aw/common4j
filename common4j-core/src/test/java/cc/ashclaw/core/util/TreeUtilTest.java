// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.util;

import java.util.ArrayList;
import java.util.List;

import cc.ashclaw.common4j.core.util.TreeUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TreeUtil result verification using JUnit 5.
 * <p>
 * TreeUtil结果验证测试类，使用JUnit 5。
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

    /**
     * Test the buildTree method of TreeUtil.
     * <p>
     * 测试TreeUtil的buildTree方法。
     */
    @Test
    void testBuildTree() {
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
        assertEquals(2, rootNodes.size(), "buildTree should return 2 root nodes");
        
        // Verify tree structure
        TestNode root1 = rootNodes.get(0);
        TestNode root2 = rootNodes.get(1);
        
        assertEquals(1, root1.getId(), "Root1 should have id 1");
        assertEquals("Root1", root1.getName(), "Root1 should have name 'Root1'");
        
        assertEquals(2, root2.getId(), "Root2 should have id 2");
        assertEquals("Root2", root2.getName(), "Root2 should have name 'Root2'");
        
        List<TestNode> root1Children = root1.getChildren();
        assertEquals(2, root1Children.size(), "Root1 should have 2 children");
        
        List<TestNode> root2Children = root2.getChildren();
        assertEquals(1, root2Children.size(), "Root2 should have 1 child");
    }

    /**
     * Test the toFlatList method of TreeUtil.
     * <p>
     * 测试TreeUtil的toFlatList方法。
     */
    @Test
    void testToFlatList() {
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
        assertEquals(5, flatList.size(), "toFlatList should return 5 nodes");
        
        // Verify flat list contains all nodes
        Integer[] expectedIds = {1, 2, 3, 4, 5};
        for (Integer id : expectedIds) {
            boolean found = flatList.stream().anyMatch(node -> id.equals(node.getId()));
            assertTrue(found, "toFlatList should contain node with id " + id);
        }
    }

    /**
     * Test the findNode method of TreeUtil.
     * <p>
     * 测试TreeUtil的findNode方法。
     */
    @Test
    void testFindNode() {
        // Create tree structure
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root1"));
        flatNodes.add(new TestNode(2, 1, "Child1-1"));
        flatNodes.add(new TestNode(3, 2, "Child1-1-1"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        
        // Find existing node
        TestNode foundNode = TreeUtil.findNode(rootNodes, 3);
        assertNotNull(foundNode, "findNode should find existing node");
        assertEquals(3, foundNode.getId(), "Found node should have id 3");
        assertEquals("Child1-1-1", foundNode.getName(), "Found node should have correct name");
        
        // Find non-existing node
        foundNode = TreeUtil.findNode(rootNodes, 999);
        assertNull(foundNode, "findNode should return null for non-existing node");
        
        // Find root node
        foundNode = TreeUtil.findNode(rootNodes, 1);
        assertNotNull(foundNode, "findNode should find root node");
        assertEquals(1, foundNode.getId(), "Found root node should have id 1");
    }

    /**
     * Test the getMaxDepth method of TreeUtil.
     * <p>
     * 测试TreeUtil的getMaxDepth方法。
     */
    @Test
    void testGetMaxDepth() {
        // Test empty tree
        int depth = TreeUtil.getMaxDepth(new ArrayList<TestNode>());
        assertEquals(0, depth, "getMaxDepth should return 0 for empty tree");
        
        // Test single node tree
        List<TestNode> singleNode = new ArrayList<>();
        singleNode.add(new TestNode(1, null, "SingleNode"));
        depth = TreeUtil.getMaxDepth(singleNode);
        assertEquals(1, depth, "getMaxDepth should return 1 for single node");
        
        // Test multi-level tree
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Level1"));
        flatNodes.add(new TestNode(2, 1, "Level2"));
        flatNodes.add(new TestNode(3, 2, "Level3"));
        flatNodes.add(new TestNode(4, 3, "Level4"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        depth = TreeUtil.getMaxDepth(rootNodes);
        assertEquals(4, depth, "getMaxDepth should return 4 for 4-level tree");
        
        // Test multi-root tree with different depths
        List<TestNode> multiRootNodes = new ArrayList<>();
        multiRootNodes.add(new TestNode(1, null, "Root1"));
        multiRootNodes.add(new TestNode(2, null, "Root2"));
        multiRootNodes.add(new TestNode(3, 1, "Child1-1"));
        multiRootNodes.add(new TestNode(4, 3, "Child1-1-1"));
        multiRootNodes.add(new TestNode(5, 2, "Child2-1"));
        
        List<TestNode> builtRoots = TreeUtil.buildTree(multiRootNodes);
        depth = TreeUtil.getMaxDepth(builtRoots);
        assertEquals(3, depth, "getMaxDepth should return 3 for max depth");
    }

    /**
     * Test the traversal methods of TreeUtil.
     * <p>
     * 测试TreeUtil的遍历方法。
     */
    @Test
    void testTraversalMethods() {
        // Create tree structure
        List<TestNode> flatNodes = new ArrayList<>();
        flatNodes.add(new TestNode(1, null, "Root"));
        flatNodes.add(new TestNode(2, 1, "Child1"));
        flatNodes.add(new TestNode(3, 1, "Child2"));
        flatNodes.add(new TestNode(4, 2, "Child1-1"));
        
        List<TestNode> rootNodes = TreeUtil.buildTree(flatNodes);
        
        // Test pre-order traversal
        List<Integer> preOrderIds = new ArrayList<>();
        TreeUtil.preOrderTraversal(rootNodes, node -> {
            preOrderIds.add(node.getId());
        });
        
        Integer[] expectedPreOrder = {1, 2, 4, 3};
        assertArrayEquals(expectedPreOrder, preOrderIds.toArray(new Integer[0]), "preOrderTraversal should have correct order");
        
        // Test post-order traversal
        List<Integer> postOrderIds = new ArrayList<>();
        TreeUtil.postOrderTraversal(rootNodes, node -> {
            postOrderIds.add(node.getId());
        });
        
        Integer[] expectedPostOrder = {4, 2, 3, 1};
        assertArrayEquals(expectedPostOrder, postOrderIds.toArray(new Integer[0]), "postOrderTraversal should have correct order");
    }

    /**
     * Test edge cases for TreeUtil methods.
     * <p>
     * 测试TreeUtil方法的边界情况。
     */
    @Test
    void testEdgeCases() {
        // Test buildTree with empty list
        List<TestNode> result = TreeUtil.buildTree(new ArrayList<TestNode>());
        assertNotNull(result, "buildTree should not return null for empty input");
        assertTrue(result.isEmpty(), "buildTree should return empty list for empty input");
        
        // Test buildTree with single node
        List<TestNode> singleNodeList = new ArrayList<>();
        singleNodeList.add(new TestNode(1, null, "SingleNode"));
        result = TreeUtil.buildTree(singleNodeList);
        assertEquals(1, result.size(), "buildTree should return single root for single node");
        
        // Test toFlatList with empty list
        List<TestNode> flatResult = TreeUtil.toFlatList(new ArrayList<TestNode>());
        assertNotNull(flatResult, "toFlatList should not return null for empty input");
        assertTrue(flatResult.isEmpty(), "toFlatList should return empty list for empty input");
        
        // Test findNode with empty tree
        TestNode found = TreeUtil.findNode(new ArrayList<TestNode>(), 1);
        assertNull(found, "findNode should return null for empty tree");
        
        // Test cycle detection (should throw exception)
        List<TestNode> cyclicNodes = new ArrayList<>();
        cyclicNodes.add(new TestNode(1, null, "Root")); // Root node
        cyclicNodes.add(new TestNode(2, 1, "Child"));  // Child of root
        cyclicNodes.add(new TestNode(3, 2, "Grandchild")); // Child of child
        cyclicNodes.add(new TestNode(2, 3, "CycleNode")); // Cycle: Node2's parent is Node3
        
        assertThrows(IllegalArgumentException.class, () -> TreeUtil.buildTree(cyclicNodes), "buildTree should detect cycle");
    }
}