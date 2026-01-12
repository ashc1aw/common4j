// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class TreeUtil {

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
     * @param <E> the type of the node itself
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
     * @param <T>   the type of the node's identifier
     * @param <E>   the type of the node itself
     * @return the root nodes of the tree
     */
    public static <T, E extends TreeNode<T, E>> List<E> buildTree(List<E> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return new ArrayList<>();
        }

        // Group nodes by parent ID
        var nodesByParentId = nodes.stream()
                .collect(Collectors.groupingBy(TreeNode::getParentId));

        // Find root nodes (nodes with no parent)
        return nodes.stream()
                .filter(node -> node.getParentId() == null)
                .peek(node -> buildTreeRecursive(node, nodesByParentId))
                .collect(Collectors.toList());
    }

    /**
     * Recursively builds the tree structure for a node.
     * <p>
     * 递归地为节点构建树结构。
     *
     * @param node           the current node
     * @param nodesByParentId the map of nodes grouped by parent ID
     * @param <T>            the type of the node's identifier
     * @param <E>            the type of the node itself
     */
    private static <T, E extends TreeNode<T, E>> void buildTreeRecursive(E node, java.util.Map<T, List<E>> nodesByParentId) {
        List<E> children = nodesByParentId.get(node.getId());
        if (children != null && !children.isEmpty()) {
            node.setChildren(children);
            children.forEach(child -> buildTreeRecursive(child, nodesByParentId));
        } else {
            node.setChildren(new ArrayList<>());
        }
    }

    /**
     * Converts a tree structure to a flat list using pre-order traversal.
     * <p>
     * 使用前序遍历将树结构转换为扁平列表。
     *
     * @param rootNodes the root nodes of the tree
     * @param <T>       the type of the node's identifier
     * @param <E>       the type of the node itself
     * @return the flat list of nodes
     */
    public static <T, E extends TreeNode<T, E>> List<E> toFlatList(List<E> rootNodes) {
        List<E> result = new ArrayList<>();
        if (rootNodes != null) {
            rootNodes.forEach(node -> toFlatListRecursive(node, result));
        }
        return result;
    }

    /**
     * Recursively flattens a tree structure using pre-order traversal.
     * <p>
     * 使用前序遍历递归地将树结构展平。
     *
     * @param node   the current node
     * @param result the result list to accumulate nodes
     * @param <T>    the type of the node's identifier
     * @param <E>    the type of the node itself
     */
    private static <T, E extends TreeNode<T, E>> void toFlatListRecursive(E node, List<E> result) {
        if (node != null) {
            result.add(node);
            List<E> children = node.getChildren();
            if (children != null) {
                children.forEach(child -> toFlatListRecursive(child, result));
            }
        }
    }

    /**
     * Finds a node in the tree by its ID using pre-order traversal.
     * <p>
     * 使用前序遍历在树中查找指定ID的节点。
     *
     * @param rootNodes the root nodes of the tree
     * @param id        the ID of the node to find
     * @param <T>       the type of the node's identifier
     * @param <E>       the type of the node itself
     * @return the found node, or null if not found
     */
    public static <T, E extends TreeNode<T, E>> E findNode(List<E> rootNodes, T id) {
        if (rootNodes == null || id == null) {
            return null;
        }

        for (E node : rootNodes) {
            E found = findNodeRecursive(node, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Recursively finds a node in the tree by its ID using pre-order traversal.
     * <p>
     * 使用前序遍历递归地在树中查找指定ID的节点。
     *
     * @param node the current node
     * @param id   the ID of the node to find
     * @param <T>  the type of the node's identifier
     * @param <E>  the type of the node itself
     * @return the found node, or null if not found
     */
    private static <T, E extends TreeNode<T, E>> E findNodeRecursive(E node, T id) {
        if (node == null) {
            return null;
        }

        if (id.equals(node.getId())) {
            return node;
        }

        List<E> children = node.getChildren();
        if (children != null) {
            for (E child : children) {
                E found = findNodeRecursive(child, id);
                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    /**
     * Gets the maximum depth of the tree.
     * <p>
     * 获取树的最大深度。
     *
     * @param rootNodes the root nodes of the tree
     * @param <T>       the type of the node's identifier
     * @param <E>       the type of the node itself
     * @return the maximum depth of the tree
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
     * @param depth the current depth
     * @param <T>   the type of the node's identifier
     * @param <E>   the type of the node itself
     * @return the maximum depth of the tree starting from the current node
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
     * Traverses the tree using pre-order traversal and applies the given function to each node.
     * <p>
     * 使用前序遍历遍历树，并对每个节点应用给定的函数。
     *
     * @param rootNodes the root nodes of the tree
     * @param function  the function to apply to each node
     * @param <T>       the type of the node's identifier
     * @param <E>       the type of the node itself
     */
    public static <T, E extends TreeNode<T, E>> void preOrderTraversal(List<E> rootNodes, Function<E, Void> function) {
        if (rootNodes != null && function != null) {
            rootNodes.forEach(node -> preOrderTraversalRecursive(node, function));
        }
    }

    /**
     * Recursively traverses the tree using pre-order traversal and applies the given function to each node.
     * <p>
     * 使用前序遍历递归地遍历树，并对每个节点应用给定的函数。
     *
     * @param node     the current node
     * @param function the function to apply to each node
     * @param <T>      the type of the node's identifier
     * @param <E>      the type of the node itself
     */
    private static <T, E extends TreeNode<T, E>> void preOrderTraversalRecursive(E node, Function<E, Void> function) {
        if (node != null) {
            function.apply(node);
            List<E> children = node.getChildren();
            if (children != null) {
                children.forEach(child -> preOrderTraversalRecursive(child, function));
            }
        }
    }

    /**
     * Traverses the tree using post-order traversal and applies the given function to each node.
     * <p>
     * 使用后序遍历遍历树，并对每个节点应用给定的函数。
     *
     * @param rootNodes the root nodes of the tree
     * @param function  the function to apply to each node
     * @param <T>       the type of the node's identifier
     * @param <E>       the type of the node itself
     */
    public static <T, E extends TreeNode<T, E>> void postOrderTraversal(List<E> rootNodes, Function<E, Void> function) {
        if (rootNodes != null && function != null) {
            rootNodes.forEach(node -> postOrderTraversalRecursive(node, function));
        }
    }

    /**
     * Recursively traverses the tree using post-order traversal and applies the given function to each node.
     * <p>
     * 使用后序遍历递归地遍历树，并对每个节点应用给定的函数。
     *
     * @param node     the current node
     * @param function the function to apply to each node
     * @param <T>      the type of the node's identifier
     * @param <E>      the type of the node itself
     */
    private static <T, E extends TreeNode<T, E>> void postOrderTraversalRecursive(E node, Function<E, Void> function) {
        if (node != null) {
            List<E> children = node.getChildren();
            if (children != null) {
                children.forEach(child -> postOrderTraversalRecursive(child, function));
            }
            function.apply(node);
        }
    }
}