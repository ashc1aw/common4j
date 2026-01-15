// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Utility class for common tree structure operations.
 * <p>
 * 树结构工具类，提供常用的树结构操作方法。
 * <p>
 * This class provides methods for building trees from flat lists, converting trees to flat lists,
 * traversing trees, finding nodes, and other common tree operations.
 * <p>
 * 此类提供从扁平列表构建树、将树转换为扁平列表、遍历树、查找节点等常用树操作方法。
 * <p>
 * <strong>Generic Type Information:</strong>
 * <ul>
 *   <li>&lt;T&gt;: Type of the node's identifier
 *                <p>
 *                节点ID的类型</li>
 *   <li>&lt;E&gt;: Type of the tree node itself
 *                <p>
 *                树节点的类型</li>
 * </ul>
 * <p>
 * All methods in this class use these generic types to ensure type safety when working with tree structures.
 * <p>
 * 此类中的所有方法都使用这些泛型类型来确保在处理树结构时的类型安全。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class TreeUtil {

    /**
     * Default maximum depth for recursive tree operations.
     * <p>
     * 递归树操作的默认最大深度。
     */
    public static final int DEFAULT_MAX_DEPTH = 1000;

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private TreeUtil() {
        throw new UnsupportedOperationException("TreeUtil cannot be instantiated.");
    }

    /**
     * Represents a node in a tree structure.
     * <p>
     * 树结构中的节点接口。
     *
     * @param <T> the type of the node's identifier
     *            <p>
     *            节点ID的类型
     * @param <E> the type of the node itself
     *            <p>
     *            树节点的类型
     */
    public interface TreeNode<T, E extends TreeNode<T, E>> {
        /**
         * Gets the unique identifier of the node.
         * <p>
         * 获取节点的唯一标识符。
         *
         * @return the node's identifier
         */
        T getId();

        /**
         * Gets the identifier of the parent node.
         * <p>
         * 获取父节点的标识符。
         *
         * @return the parent node's identifier, or null if this is a root node
         */
        T getParentId();

        /**
         * Gets the list of child nodes.
         * <p>
         * 获取子节点列表。
         *
         * @return the list of child nodes
         */
        List<E> getChildren();

        /**
         * Sets the list of child nodes.
         * <p>
         * 设置子节点列表。
         *
         * @param children the list of child nodes to set
         */
        void setChildren(List<E> children);
    }

    /**
     * Builds a tree structure from a flat list of nodes.
     * <p>
     * 从扁平的节点列表构建树结构。
     *
     * @param nodes the flat list of nodes
     *              <p>
     *              扁平的节点列表
     * @param <T>   the type of the node's identifier
     *              <p>
     *              节点ID的类型
     * @param <E>   the type of the node itself
     *              <p>
     *              树节点的类型
     * @return the root nodes of the tree
     *         <p>
     *         树的根节点列表
     */
    public static <T, E extends TreeNode<T, E>> List<E> buildTree(List<E> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return new ArrayList<>();
        }

        // Group non-root nodes by parent ID
        var nodesByParentId = nodes.stream()
                .filter(node -> node.getParentId() != null)
                .collect(Collectors.groupingBy(TreeNode::getParentId));

        // Find root nodes (nodes with no parent) and build tree
        List<E> rootNodes = nodes.stream()
                .filter(node -> node.getParentId() == null)
                .collect(Collectors.toList());

        // Build tree recursively for each root node
        rootNodes.forEach(node -> buildTreeRecursive(node, nodesByParentId));

        return rootNodes;
    }

    /**
     * Recursively builds the tree structure for a node.
     * <p>
     * 递归地为节点构建树结构。
     *
     * @param node           the current node
     *                       <p>
     *                       当前节点
     * @param nodesByParentId the map of nodes grouped by parent ID
     *                       <p>
     *                       按父ID分组的节点映射
     * @param <T>            the type of the node's identifier
     *                       <p>
     *                       节点ID的类型
     * @param <E>            the type of the node itself
     *                       <p>
     *                       树节点的类型
     */
    private static <T, E extends TreeNode<T, E>> void buildTreeRecursive(E node, java.util.Map<T, List<E>> nodesByParentId) {
        // Use a set to detect cycles during tree building
        java.util.Set<T> visitedIds = new java.util.HashSet<>();
        buildTreeRecursive(node, nodesByParentId, visitedIds);
    }

    /**
     * Recursively builds the tree structure for a node with cycle detection.
     * <p>
     * 递归地为节点构建树结构，并进行循环检测。
     *
     * @param node           the current node
     *                       <p>
     *                       当前节点
     * @param nodesByParentId the map of nodes grouped by parent ID
     *                       <p>
     *                       按父ID分组的节点映射
     * @param visitedIds     the set of visited node IDs to detect cycles
     *                       <p>
     *                       用于检测循环的已访问节点ID集合
     * @param <T>            the type of the node's identifier
     *                       <p>
     *                       节点ID的类型
     * @param <E>            the type of the node itself
     *                       <p>
     *                       树节点的类型
     * @throws IllegalArgumentException if a cycle is detected in the tree structure
     */
    private static <T, E extends TreeNode<T, E>> void buildTreeRecursive(E node, java.util.Map<T, List<E>> nodesByParentId, java.util.Set<T> visitedIds) {
        if (node == null) {
            return;
        }

        T nodeId = node.getId();
        if (visitedIds.contains(nodeId)) {
            throw new IllegalArgumentException("Cycle detected in tree structure: node ID " + nodeId + " appears more than once in the hierarchy");
        }

        visitedIds.add(nodeId);
        try {
            List<E> children = nodesByParentId.get(nodeId);
            if (children != null && !children.isEmpty()) {
                node.setChildren(children);
                children.forEach(child -> buildTreeRecursive(child, nodesByParentId, new java.util.HashSet<>(visitedIds)));
            } else {
                node.setChildren(new ArrayList<>());
            }
        } finally {
            visitedIds.remove(nodeId);
        }
    }

    /**
     * Converts a tree structure to a flat list using pre-order traversal.
     * <p>
     * 使用前序遍历将树结构转换为扁平列表。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     * @return the flat list of nodes
     *         <p>
     *         扁平的节点列表
     */
    public static <T, E extends TreeNode<T, E>> List<E> toFlatList(List<E> rootNodes) {
        return toFlatList(rootNodes, DEFAULT_MAX_DEPTH);
    }

    /**
     * Converts a tree structure to a flat list using pre-order traversal with depth limit.
     * <p>
     * 使用前序遍历将树结构转换为扁平列表，并限制最大深度。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param maxDepth  the maximum depth to traverse
     *                  <p>
     *                  要遍历的最大深度
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     * @return the flat list of nodes
     *         <p>
     *         扁平的节点列表
     * @throws IllegalArgumentException if the tree depth exceeds the maximum depth
     */
    public static <T, E extends TreeNode<T, E>> List<E> toFlatList(List<E> rootNodes, int maxDepth) {
        List<E> result = new ArrayList<>();
        if (rootNodes == null || rootNodes.isEmpty()) {
            return result;
        }

        // Use a stack to simulate recursive traversal
        java.util.Stack<NodeWithDepth<E>> stack = new java.util.Stack<>();
        
        // Push all root nodes onto the stack
        for (E node : rootNodes) {
            if (node != null) {
                stack.push(new NodeWithDepth<>(node, 1));
            }
        }

        // Iterative pre-order traversal
        while (!stack.isEmpty()) {
            NodeWithDepth<E> current = stack.pop();
            E node = current.node;
            int depth = current.depth;

            if (depth > maxDepth) {
                throw new IllegalArgumentException("Tree depth exceeds maximum allowed depth: " + maxDepth);
            }

            // Visit the current node
            result.add(node);

            // Push children onto the stack in reverse order to maintain pre-order traversal
            List<E> children = node.getChildren();
            if (children != null && !children.isEmpty()) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    E child = children.get(i);
                    if (child != null) {
                        stack.push(new NodeWithDepth<>(child, depth + 1));
                    }
                }
            }
        }

        return result;
    }

    /**
     * Helper class to store a node along with its depth.
     * <p>
     * 辅助类，用于存储节点及其深度。
     *
     * @param <E> the type of the node
     */
    private static class NodeWithDepth<E> {
        private final E node;
        private final int depth;

        /**
         * Constructor.
         * <p>
         * 构造函数。
         *
         * @param node  the node
         *              <p>
         *              节点
         * @param depth the depth of the node
         *              <p>
         *              节点的深度
         */
        private NodeWithDepth(E node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * Finds a node in the tree by its ID using pre-order traversal.
     * <p>
     * 使用前序遍历在树中查找指定ID的节点。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param id        the ID of the node to find
     *                  <p>
     *                  要查找的节点ID
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     * @return the found node, or null if not found
     *         <p>
     *         找到的节点，如果未找到则返回null
     */
    public static <T, E extends TreeNode<T, E>> E findNode(List<E> rootNodes, T id) {
        return findNode(rootNodes, id, DEFAULT_MAX_DEPTH);
    }

    /**
     * Finds a node in the tree by its ID using pre-order traversal with depth limit.
     * <p>
     * 使用前序遍历在树中查找指定ID的节点，并限制最大深度。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param id        the ID of the node to find
     *                  <p>
     *                  要查找的节点ID
     * @param maxDepth  the maximum depth to traverse
     *                  <p>
     *                  要遍历的最大深度
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     * @return the found node, or null if not found
     *         <p>
     *         找到的节点，如果未找到则返回null
     * @throws IllegalArgumentException if the tree depth exceeds the maximum depth
     */
    public static <T, E extends TreeNode<T, E>> E findNode(List<E> rootNodes, T id, int maxDepth) {
        if (rootNodes == null || id == null) {
            return null;
        }

        // Use a stack to simulate recursive traversal
        java.util.Stack<NodeWithDepth<E>> stack = new java.util.Stack<>();
        
        // Push all root nodes onto the stack
        for (E node : rootNodes) {
            if (node != null) {
                stack.push(new NodeWithDepth<>(node, 1));
            }
        }

        // Iterative pre-order traversal to find the node
        while (!stack.isEmpty()) {
            NodeWithDepth<E> current = stack.pop();
            E node = current.node;
            int depth = current.depth;

            if (depth > maxDepth) {
                throw new IllegalArgumentException("Tree depth exceeds maximum allowed depth: " + maxDepth);
            }

            // Check if current node is the one we're looking for
            if (id.equals(node.getId())) {
                return node;
            }

            // Push children onto the stack in reverse order to maintain pre-order traversal
            List<E> children = node.getChildren();
            if (children != null && !children.isEmpty()) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    E child = children.get(i);
                    if (child != null) {
                        stack.push(new NodeWithDepth<>(child, depth + 1));
                    }
                }
            }
        }

        // Node not found
        return null;
    }

    /**
     * Gets the maximum depth of the tree.
     * <p>
     * 获取树的最大深度。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     * @return the maximum depth of the tree
     *         <p>
     *         树的最大深度
     */
    public static <T, E extends TreeNode<T, E>> int getMaxDepth(List<E> rootNodes) {
        if (rootNodes == null || rootNodes.isEmpty()) {
            return 0;
        }

        int maxDepth = 0;
        for (E node : rootNodes) {
            int depth = getMaxDepthRecursive(node, 1);
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }
        return maxDepth;
    }

    /**
     * Recursively calculates the maximum depth of the tree.
     * <p>
     * 递归地计算树的最大深度。
     *
     * @param node  the current node
     *              <p>
     *              当前节点
     * @param depth the current depth
     *              <p>
     *              当前深度
     * @param <T>   the type of the node's identifier
     *              <p>
     *              节点ID的类型
     * @param <E>   the type of the node itself
     *              <p>
     *              树节点的类型
     * @return the maximum depth of the tree starting from the current node
     *         <p>
     *         从当前节点开始的树的最大深度
     */
    private static <T, E extends TreeNode<T, E>> int getMaxDepthRecursive(E node, int depth) {
        if (node == null) {
            return depth - 1;
        }

        List<E> children = node.getChildren();
        if (children == null || children.isEmpty()) {
            return depth;
        }

        int maxDepth = depth;
        for (E child : children) {
            int childDepth = getMaxDepthRecursive(child, depth + 1);
            if (childDepth > maxDepth) {
                maxDepth = childDepth;
            }
        }
        return maxDepth;
    }

    /**
     * Traverses the tree using pre-order traversal and applies the given consumer to each node.
     * <p>
     * 使用前序遍历遍历树，并对每个节点应用给定的消费函数。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param consumer  the consumer to apply to each node
     *                  <p>
     *                  要应用到每个节点的消费函数
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     */
    public static <T, E extends TreeNode<T, E>> void preOrderTraversal(List<E> rootNodes, Consumer<E> consumer) {
        if (rootNodes != null && consumer != null) {
            rootNodes.forEach(node -> preOrderTraversalRecursive(node, consumer));
        }
    }

    /**
     * Recursively traverses the tree using pre-order traversal and applies the given consumer to each node.
     * <p>
     * 使用前序遍历递归地遍历树，并对每个节点应用给定的消费函数。
     *
     * @param node     the current node
     *                 <p>
     *                 当前节点
     * @param consumer the consumer to apply to each node
     *                 <p>
     *                 要应用到每个节点的消费函数
     * @param <T>      the type of the node's identifier
     *                 <p>
     *                 节点ID的类型
     * @param <E>      the type of the node itself
     *                 <p>
     *                 树节点的类型
     */
    private static <T, E extends TreeNode<T, E>> void preOrderTraversalRecursive(E node, Consumer<E> consumer) {
        if (node != null) {
            consumer.accept(node);
            List<E> children = node.getChildren();
            if (children != null) {
                children.forEach(child -> preOrderTraversalRecursive(child, consumer));
            }
        }
    }

    /**
     * Traverses the tree using post-order traversal and applies the given consumer to each node.
     * <p>
     * 使用后序遍历遍历树，并对每个节点应用给定的消费函数。
     *
     * @param rootNodes the root nodes of the tree
     *                  <p>
     *                  树的根节点列表
     * @param consumer  the consumer to apply to each node
     *                  <p>
     *                  要应用到每个节点的消费函数
     * @param <T>       the type of the node's identifier
     *                  <p>
     *                  节点ID的类型
     * @param <E>       the type of the node itself
     *                  <p>
     *                  树节点的类型
     */
    public static <T, E extends TreeNode<T, E>> void postOrderTraversal(List<E> rootNodes, Consumer<E> consumer) {
        if (rootNodes != null && consumer != null) {
            rootNodes.forEach(node -> postOrderTraversalRecursive(node, consumer));
        }
    }

    /**
     * Recursively traverses the tree using post-order traversal and applies the given consumer to each node.
     * <p>
     * 使用后序遍历递归地遍历树，并对每个节点应用给定的消费函数。
     *
     * @param node     the current node
     *                 <p>
     *                 当前节点
     * @param consumer the consumer to apply to each node
     *                 <p>
     *                 要应用到每个节点的消费函数
     * @param <T>      the type of the node's identifier
     *                 <p>
     *                 节点ID的类型
     * @param <E>      the type of the node itself
     *                 <p>
     *                 树节点的类型
     */
    private static <T, E extends TreeNode<T, E>> void postOrderTraversalRecursive(E node, Consumer<E> consumer) {
        if (node != null) {
            List<E> children = node.getChildren();
            if (children != null) {
                children.forEach(child -> postOrderTraversalRecursive(child, consumer));
            }
            consumer.accept(node);
        }
    }
}