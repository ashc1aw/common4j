// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic tree node structure.
 * <p>
 * 表示通用的树节点结构。
 * <p>
 * Provides a flexible data structure for representing hierarchical data with parent-child relationships.
 * <p>
 * 提供灵活的数据结构，用于表示具有父子关系的层级数据。
 *
 * @param <T> the type of node value
 * @author b1itz7
 * @since 1.0.0
 */
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Node unique identifier.
     * <p>
     * 节点唯一标识符。
     */
    private String id;

    /**
     * Parent node identifier.
     * <p>
     * 父节点标识符。
     */
    private String parentId;

    /**
     * Node name or label.
     * <p>
     * 节点名称或标签。
     */
    private String name;

    /**
     * Node value.
     * <p>
     * 节点值。
     */
    private T value;

    /**
     * List of child nodes.
     * <p>
     * 子节点列表。
     */
    private List<TreeNode<T>> children;

    /**
     * Node level in the tree hierarchy (root is level 0).
     * <p>
     * 节点在树层级中的级别（根节点为级别0）。
     */
    private int level;

    /**
     * Whether the node is expanded.
     * <p>
     * 节点是否展开。
     */
    private boolean expanded;

    /**
     * Whether the node is selected.
     * <p>
     * 节点是否被选中。
     */
    private boolean selected;

    /**
     * Whether the node is a leaf node (has no children).
     * <p>
     * 节点是否为叶子节点（没有子节点）。
     */
    private boolean leaf;

    /**
     * Node sort order.
     * <p>
     * 节点排序顺序。
     */
    private int sortOrder;

    /**
     * Node extra attributes.
     * <p>
     * 节点额外属性。
     */
    private Object extra;

    /**
     * Creates a new TreeNode with default values.
     * <p>
     * 创建一个具有默认值的新TreeNode。
     */
    public TreeNode() {
        this.children = new ArrayList<>();
        this.level = 0;
        this.expanded = false;
        this.selected = false;
        this.leaf = true;
        this.sortOrder = 0;
    }

    /**
     * Creates a new TreeNode with specified id, parentId, and name.
     * <p>
     * 创建一个具有指定id、parentId和name的新TreeNode。
     *
     * @param id       the node identifier
     * @param parentId the parent node identifier
     * @param name     the node name
     */
    public TreeNode(String id, String parentId, String name) {
        this();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    /**
     * Creates a new TreeNode with specified id, parentId, name, and value.
     * <p>
     * 创建一个具有指定id、parentId、name和value的新TreeNode。
     *
     * @param id       the node identifier
     * @param parentId the parent node identifier
     * @param name     the node name
     * @param value    the node value
     */
    public TreeNode(String id, String parentId, String name, T value) {
        this(id, parentId, name);
        this.value = value;
    }

    /**
     * Adds a child node to this node.
     * <p>
     * 向此节点添加子节点。
     *
     * @param child the child node to add
     */
    public void addChild(TreeNode<T> child) {
        if (child != null) {
            children.add(child);
            child.setLevel(this.level + 1);
            this.leaf = false;
        }
    }

    /**
     * Adds multiple child nodes to this node.
     * <p>
     * 向此节点添加多个子节点。
     *
     * @param children the list of child nodes to add
     */
    public void addChildren(List<TreeNode<T>> children) {
        if (children != null && !children.isEmpty()) {
            this.children.addAll(children);
            children.forEach(child -> child.setLevel(this.level + 1));
            this.leaf = false;
        }
    }

    /**
     * Removes a child node from this node.
     * <p>
     * 从此节点移除子节点。
     *
     * @param child the child node to remove
     * @return true if the child was removed, false otherwise
     */
    public boolean removeChild(TreeNode<T> child) {
        boolean removed = children.remove(child);
        if (removed) {
            this.leaf = children.isEmpty();
        }
        return removed;
    }

    /**
     * Removes a child node by its id.
     * <p>
     * 通过id移除子节点。
     *
     * @param childId the id of the child node to remove
     * @return true if the child was removed, false otherwise
     */
    public boolean removeChildById(String childId) {
        boolean removed = children.removeIf(child -> child.getId().equals(childId));
        if (removed) {
            this.leaf = children.isEmpty();
        }
        return removed;
    }

    /**
     * Finds a child node by its id.
     * <p>
     * 通过id查找子节点。
     *
     * @param childId the id of the child node to find
     * @return the found child node, or null if not found
     */
    public TreeNode<T> findChildById(String childId) {
        for (TreeNode<T> child : children) {
            if (child.getId().equals(childId)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Checks if this node has any children.
     * <p>
     * 检查此节点是否有子节点。
     *
     * @return true if the node has children, false otherwise
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Gets the number of children.
     * <p>
     * 获取子节点数量。
     *
     * @return the number of children
     */
    public int getChildrenCount() {
        return children.size();
    }

    /**
     * Expands or collapses this node.
     * <p>
     * 展开或折叠此节点。
     *
     * @param expanded true to expand, false to collapse
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * Toggles the expanded state of this node.
     * <p>
     * 切换此节点的展开状态。
     */
    public void toggleExpanded() {
        this.expanded = !this.expanded;
    }

    /**
     * Selects or deselects this node.
     * <p>
     * 选中或取消选中此节点。
     *
     * @param selected true to select, false to deselect
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Toggles the selected state of this node.
     * <p>
     * 切换此节点的选中状态。
     */
    public void toggleSelected() {
        this.selected = !this.selected;
    }

    /**
     * Updates the leaf status based on the presence of children.
     * <p>
     * 根据子节点的存在更新叶子状态。
     */
    public void updateLeafStatus() {
        this.leaf = children.isEmpty();
    }

    // Getters and Setters

    /**
     * Gets the node identifier.
     * <p>
     * 获取节点标识符。
     *
     * @return the node identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the node identifier.
     * <p>
     * 设置节点标识符。
     *
     * @param id the node identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the parent node identifier.
     * <p>
     * 获取父节点标识符。
     *
     * @return the parent node identifier
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Sets the parent node identifier.
     * <p>
     * 设置父节点标识符。
     *
     * @param parentId the parent node identifier
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets the node name.
     * <p>
     * 获取节点名称。
     *
     * @return the node name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the node name.
     * <p>
     * 设置节点名称。
     *
     * @param name the node name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the node value.
     * <p>
     * 获取节点值。
     *
     * @return the node value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the node value.
     * <p>
     * 设置节点值。
     *
     * @param value the node value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Gets the list of child nodes.
     * <p>
     * 获取子节点列表。
     *
     * @return the list of child nodes
     */
    public List<TreeNode<T>> getChildren() {
        return children;
    }

    /**
     * Sets the list of child nodes.
     * <p>
     * 设置子节点列表。
     *
     * @param children the list of child nodes
     */
    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
        if (children != null) {
            this.leaf = children.isEmpty();
            children.forEach(child -> child.setLevel(this.level + 1));
        } else {
            this.children = new ArrayList<>();
            this.leaf = true;
        }
    }

    /**
     * Gets the node level.
     * <p>
     * 获取节点级别。
     *
     * @return the node level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the node level.
     * <p>
     * 设置节点级别。
     *
     * @param level the node level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Checks if the node is expanded.
     * <p>
     * 检查节点是否展开。
     *
     * @return true if expanded, false otherwise
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Checks if the node is selected.
     * <p>
     * 检查节点是否被选中。
     *
     * @return true if selected, false otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Checks if the node is a leaf node.
     * <p>
     * 检查节点是否为叶子节点。
     *
     * @return true if leaf, false otherwise
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Sets whether the node is a leaf node.
     * <p>
     * 设置节点是否为叶子节点。
     *
     * @param leaf true if leaf, false otherwise
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * Gets the node sort order.
     * <p>
     * 获取节点排序顺序。
     *
     * @return the node sort order
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the node sort order.
     * <p>
     * 设置节点排序顺序。
     *
     * @param sortOrder the node sort order
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Gets the node extra attributes.
     * <p>
     * 获取节点额外属性。
     *
     * @return the node extra attributes
     */
    public Object getExtra() {
        return extra;
    }

    /**
     * Sets the node extra attributes.
     * <p>
     * 设置节点额外属性。
     *
     * @param extra the node extra attributes
     */
    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", expanded=" + expanded +
                ", selected=" + selected +
                ", leaf=" + leaf +
                ", childrenCount=" + children.size() +
                '}';
    }
}