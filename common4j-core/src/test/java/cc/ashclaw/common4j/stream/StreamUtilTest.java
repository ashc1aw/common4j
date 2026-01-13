// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.stream;

import java.util.*;
import java.util.stream.*;

/**
 * Test class for StreamUtil result verification.
 * <p>
 * StreamUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class StreamUtilTest {

    public static void main(String[] args) {
        System.out.println("===== StreamUtil Result Verification Test Start =====");
        
        // Test stream creation methods
        testStreamCreationMethods();
        
        // Test collection methods
        testCollectionMethods();
        
        // Test filter methods
        testFilterMethods();
        
        // Test map methods
        testMapMethods();
        
        // Test joining methods
        testJoiningMethods();
        
        // Test from iterator/iterable methods
        testFromIteratorIterableMethods();
        
        System.out.println("===== StreamUtil Result Verification Test End =====");
    }

    /**
     * Test the stream creation methods of StreamUtil.
     * <p>
     * 测试StreamUtil的流创建方法。
     */
    private static void testStreamCreationMethods() {
        System.out.println("\n1. Testing stream creation methods...");
        
        // Test ofNullable(Collection)
        System.out.println("Testing ofNullable(Collection)...");
        List<String> list = null;
        Stream<String> stream = StreamUtil.ofNullable(list);
        long count = stream.count();
        System.out.println("ofNullable(null Collection) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: ofNullable should return empty stream for null collection!\n");
            return;
        }
        
        list = Collections.emptyList();
        stream = StreamUtil.ofNullable(list);
        count = stream.count();
        System.out.println("ofNullable(empty Collection) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: ofNullable should return empty stream for empty collection!\n");
            return;
        }
        
        list = Arrays.asList("a", "b", "c");
        stream = StreamUtil.ofNullable(list);
        count = stream.count();
        System.out.println("ofNullable(non-empty Collection) count = " + count);
        if (count != 3) {
            System.out.println("ERROR: ofNullable should return stream with correct elements!\n");
            return;
        }
        
        // Test parallelOfNullable(Collection)
        System.out.println("Testing parallelOfNullable(Collection)...");
        list = Arrays.asList("a", "b", "c");
        stream = StreamUtil.parallelOfNullable(list);
        System.out.println("parallelOfNullable(Collection) is parallel = " + stream.isParallel());
        if (!stream.isParallel()) {
            System.out.println("ERROR: parallelOfNullable should return parallel stream!\n");
            return;
        }
        
        // Test ofNullable(T...)
        System.out.println("Testing ofNullable(T...)...");
        stream = StreamUtil.ofNullable((String[]) null);
        count = stream.count();
        System.out.println("ofNullable(null Array) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: ofNullable should return empty stream for null array!\n");
            return;
        }
        
        stream = StreamUtil.ofNullable(new String[]{});
        count = stream.count();
        System.out.println("ofNullable(empty Array) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: ofNullable should return empty stream for empty array!\n");
            return;
        }
        
        stream = StreamUtil.ofNullable("a", "b", "c");
        count = stream.count();
        System.out.println("ofNullable(non-empty Array) count = " + count);
        if (count != 3) {
            System.out.println("ERROR: ofNullable should return stream with correct elements!\n");
            return;
        }
        
        System.out.println("stream creation methods test passed.");
    }

    /**
     * Test the collection methods of StreamUtil.
     * <p>
     * 测试StreamUtil的收集方法。
     */
    private static void testCollectionMethods() {
        System.out.println("\n2. Testing collection methods...");
        
        // Test toList
        System.out.println("Testing toList...");
        List<String> result = StreamUtil.toList(null);
        System.out.println("toList(null Stream) is empty = " + result.isEmpty());
        if (!result.isEmpty()) {
            System.out.println("ERROR: toList should return empty list for null stream!\n");
            return;
        }
        
        result = StreamUtil.toList(Stream.of("a", "b", "c"));
        System.out.println("toList(non-empty Stream) size = " + result.size());
        if (result.size() != 3) {
            System.out.println("ERROR: toList should return list with correct elements!\n");
            return;
        }
        
        // Test toSet
        System.out.println("Testing toSet...");
        Set<String> setResult = StreamUtil.toSet(null);
        System.out.println("toSet(null Stream) is empty = " + setResult.isEmpty());
        if (!setResult.isEmpty()) {
            System.out.println("ERROR: toSet should return empty set for null stream!\n");
            return;
        }
        
        setResult = StreamUtil.toSet(Stream.of("a", "b", "a"));
        System.out.println("toSet(non-empty Stream) size = " + setResult.size());
        if (setResult.size() != 2) {
            System.out.println("ERROR: toSet should return set with unique elements!\n");
            return;
        }
        
        // Test toMap
        System.out.println("Testing toMap...");
        Map<String, Integer> mapResult = StreamUtil.toMap(null, (String s) -> s, (String s) -> s.length());
        System.out.println("toMap(null Stream) is empty = " + mapResult.isEmpty());
        if (!mapResult.isEmpty()) {
            System.out.println("ERROR: toMap should return empty map for null stream!\n");
            return;
        }
        
        mapResult = StreamUtil.toMap(Stream.of("a", "bb", "ccc"), (String s) -> s, (String s) -> s.length());
        System.out.println("toMap(non-empty Stream) size = " + mapResult.size());
        if (mapResult.size() != 3 || mapResult.get("bb") != 2) {
            System.out.println("ERROR: toMap should return map with correct key-value pairs!\n");
            return;
        }
        
        // Test toListWithoutNull
        System.out.println("Testing toListWithoutNull...");
        result = StreamUtil.toListWithoutNull(Stream.of("a", null, "b", null, "c"));
        System.out.println("toListWithoutNull(stream with nulls) size = " + result.size());
        if (result.size() != 3) {
            System.out.println("ERROR: toListWithoutNull should return list without nulls!\n");
            return;
        }
        
        // Test toSetWithoutNull
        System.out.println("Testing toSetWithoutNull...");
        setResult = StreamUtil.toSetWithoutNull(Stream.of("a", null, "b", null, "a"));
        System.out.println("toSetWithoutNull(stream with nulls) size = " + setResult.size());
        if (setResult.size() != 2) {
            System.out.println("ERROR: toSetWithoutNull should return set without nulls and duplicates!\n");
            return;
        }
        
        System.out.println("collection methods test passed.");
    }

    /**
     * Test the filter methods of StreamUtil.
     * <p>
     * 测试StreamUtil的过滤方法。
     */
    private static void testFilterMethods() {
        System.out.println("\n3. Testing filter methods...");
        
        // Test filterNotNull
        System.out.println("Testing filterNotNull...");
        Stream<String> stream = StreamUtil.filterNotNull(null);
        long count = stream.count();
        System.out.println("filterNotNull(null Stream) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: filterNotNull should return empty stream for null input!\n");
            return;
        }
        
        stream = StreamUtil.filterNotNull(Stream.of("a", null, "b", null, "c"));
        count = stream.count();
        System.out.println("filterNotNull(stream with nulls) count = " + count);
        if (count != 3) {
            System.out.println("ERROR: filterNotNull should filter out null elements!\n");
            return;
        }
        
        System.out.println("filter methods test passed.");
    }

    /**
     * Test the map methods of StreamUtil.
     * <p>
     * 测试StreamUtil的映射方法。
     */
    private static void testMapMethods() {
        System.out.println("\n4. Testing map methods...");
        
        // Test mapNullable
        System.out.println("Testing mapNullable...");
        Stream<Integer> stream = StreamUtil.mapNullable(null, String::length);
        long count = stream.count();
        System.out.println("mapNullable(null Stream) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: mapNullable should return empty stream for null input!\n");
            return;
        }
        
        stream = StreamUtil.mapNullable(Stream.of("a", "bb", "ccc"), String::length);
        List<Integer> result = stream.collect(Collectors.toList());
        System.out.println("mapNullable(non-empty Stream) result = " + result);
        if (result.size() != 3 || result.get(0) != 1 || result.get(1) != 2 || result.get(2) != 3) {
            System.out.println("ERROR: mapNullable should apply mapping function correctly!\n");
            return;
        }
        
        // Test flatMapNullable
        System.out.println("Testing flatMapNullable...");
        Stream<String> flatMapStream = StreamUtil.flatMapNullable(null, (String s) -> Stream.of(s.split("")));
        count = flatMapStream.count();
        System.out.println("flatMapNullable(null Stream) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: flatMapNullable should return empty stream for null input!\n");
            return;
        }
        
        Stream<String> stringStream = StreamUtil.flatMapNullable(Stream.of("ab", "cd"), (String s) -> Stream.of(s.split("")));
        List<String> flatResult = stringStream.collect(Collectors.toList());
        System.out.println("flatMapNullable(non-empty Stream) result = " + flatResult);
        if (flatResult.size() != 4 || !flatResult.contains("a") || !flatResult.contains("b") || !flatResult.contains("c") || !flatResult.contains("d")) {
            System.out.println("ERROR: flatMapNullable should apply flat mapping function correctly!\n");
            return;
        }
        
        System.out.println("map methods test passed.");
    }

    /**
     * Test the joining methods of StreamUtil.
     * <p>
     * 测试StreamUtil的连接方法。
     */
    private static void testJoiningMethods() {
        System.out.println("\n5. Testing joining methods...");
        
        // Test join(Stream, CharSequence)
        System.out.println("Testing join(Stream, CharSequence)...");
        String result = StreamUtil.join(null, ",");
        System.out.println("join(null Stream, \",\") = \"" + result + "\"");
        if (!result.isEmpty()) {
            System.out.println("ERROR: join should return empty string for null stream!\n");
            return;
        }
        
        result = StreamUtil.join(Stream.empty(), ",");
        System.out.println("join(empty Stream, \",\") = \"" + result + "\"");
        if (!result.isEmpty()) {
            System.out.println("ERROR: join should return empty string for empty stream!\n");
            return;
        }
        
        result = StreamUtil.join(Stream.of("a", "b", "c"), ",");
        System.out.println("join(non-empty Stream, \",\") = \"" + result + "\"");
        if (!"a,b,c".equals(result)) {
            System.out.println("ERROR: join should join elements with delimiter!\n");
            return;
        }
        
        // Test join(Stream, CharSequence, CharSequence, CharSequence)
        System.out.println("Testing join(Stream, CharSequence, CharSequence, CharSequence)...");
        result = StreamUtil.join(null, ",", "[", "]");
        System.out.println("join(null Stream, \",\", \"[\", \"]\") = \"" + result + "\"");
        if (!result.isEmpty()) {
            System.out.println("ERROR: join should return empty string for null stream!\n");
            return;
        }
        
        result = StreamUtil.join(Stream.of("a", "b", "c"), ",", "[", "]");
        System.out.println("join(non-empty Stream, \",\", \"[\", \"]\") = \"" + result + "\"");
        if (!"[a,b,c]".equals(result)) {
            System.out.println("ERROR: join should join elements with delimiter, prefix and suffix!\n");
            return;
        }
        
        System.out.println("joining methods test passed.");
    }

    /**
     * Test the from iterator/iterable methods of StreamUtil.
     * <p>
     * 测试StreamUtil的从迭代器/可迭代对象创建流的方法。
     */
    private static void testFromIteratorIterableMethods() {
        System.out.println("\n6. Testing from iterator/iterable methods...");
        
        // Test fromIterator
        System.out.println("Testing fromIterator...");
        Stream<String> stream = StreamUtil.fromIterator(null);
        long count = stream.count();
        System.out.println("fromIterator(null) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: fromIterator should return empty stream for null iterator!\n");
            return;
        }
        
        List<String> list = Arrays.asList("a", "b", "c");
        stream = StreamUtil.fromIterator(list.iterator());
        count = stream.count();
        System.out.println("fromIterator(non-empty iterator) count = " + count);
        if (count != 3) {
            System.out.println("ERROR: fromIterator should return stream with correct elements!\n");
            return;
        }
        
        // Test fromIteratorParallel
        System.out.println("Testing fromIteratorParallel...");
        stream = StreamUtil.fromIteratorParallel(list.iterator());
        System.out.println("fromIteratorParallel is parallel = " + stream.isParallel());
        if (!stream.isParallel()) {
            System.out.println("ERROR: fromIteratorParallel should return parallel stream!\n");
            return;
        }
        
        // Test fromIterable
        System.out.println("Testing fromIterable...");
        stream = StreamUtil.fromIterable(null);
        count = stream.count();
        System.out.println("fromIterable(null) count = " + count);
        if (count != 0) {
            System.out.println("ERROR: fromIterable should return empty stream for null iterable!\n");
            return;
        }
        
        stream = StreamUtil.fromIterable(list);
        count = stream.count();
        System.out.println("fromIterable(non-empty iterable) count = " + count);
        if (count != 3) {
            System.out.println("ERROR: fromIterable should return stream with correct elements!\n");
            return;
        }
        
        // Test fromIterableParallel
        System.out.println("Testing fromIterableParallel...");
        stream = StreamUtil.fromIterableParallel(list);
        System.out.println("fromIterableParallel is parallel = " + stream.isParallel());
        if (!stream.isParallel()) {
            System.out.println("ERROR: fromIterableParallel should return parallel stream!\n");
            return;
        }
        
        System.out.println("from iterator/iterable methods test passed.");
    }
}
