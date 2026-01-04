package cc.ashclaw.common4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode<T, ID> {
    private final T data;
    private final ID id;
    private final ID parentId;
    private final List<TreeNode<T, ID>> children;

    public TreeNode(T data, ID id, ID parentId) {
        this.data = data;
        this.id = id;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public ID getId() {
        return id;
    }

    public ID getParentId() {
        return parentId;
    }

    public List<TreeNode<T, ID>> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(TreeNode<T, ID> child) {
        if (child != null) {
            children.add(child);
        }
    }

    public void addChildren(List<TreeNode<T, ID>> children) {
        if (children != null) {
            this.children.addAll(children);
        }
    }

    public boolean removeChild(TreeNode<T, ID> child) {
        return child != null && children.remove(child);
    }

    public boolean removeChildById(ID childId) {
        if (childId == null) return false;
        return children.removeIf(child -> childId.equals(child.getId()));
    }

    public void clearChildren() {
        children.clear();
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parentId == null;
    }

    public int getChildCount() {
        return children.size();
    }

}
