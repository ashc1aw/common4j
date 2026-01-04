package cc.ashclaw.common4j.util;

import cc.ashclaw.common4j.model.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeUtils {
    public static <T, ID> List<TreeNode<T, ID>> toTree(Stream<T> stream,
                                                       Function<T, ID> idExtractor,
                                                       Function<T, ID> parentIdExtractor,
                                                       ID rootParentId) {
        List<TreeNode<T, ID>> nodes = stream
                .map(item -> new TreeNode<>(item, idExtractor.apply(item), parentIdExtractor.apply(item)))
                .collect(Collectors.toList());

        Map<ID, List<TreeNode<T, ID>>> parentIdMap = nodes.stream()
                .collect(Collectors.groupingBy(TreeNode::getParentId));

        nodes.forEach(node -> node.addChildren(parentIdMap.get(node.getId())));

        return nodes.stream()
                .filter(node -> Objects.equals(node.getParentId(), rootParentId))
                .collect(Collectors.toList());
    }
}
