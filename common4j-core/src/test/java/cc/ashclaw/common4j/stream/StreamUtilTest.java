// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for StreamUtil result verification using JUnit 5.
 * <p>
 * StreamUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class StreamUtilTest {

    /**
     * Test the stream creation methods of StreamUtil.
     * <p>
     * 测试StreamUtil的流创建方法。
     */
    @Test
    void testStreamCreationMethods() {
        // Test ofNullable(Collection)
        List<String> list = null;
        Stream<String> stream = StreamUtil.ofNullable(list);
        long count = stream.count();
        assertEquals(0, count, "ofNullable should return empty stream for null collection");
        
        list = Collections.emptyList();
        stream = StreamUtil.ofNullable(list);
        count = stream.count();
        assertEquals(0, count, "ofNullable should return empty stream for empty collection");
        
        list = Arrays.asList("a", "b", "c");
        stream = StreamUtil.ofNullable(list);
        count = stream.count();
        assertEquals(3, count, "ofNullable should return stream with correct elements");
        
        // Test parallelOfNullable(Collection)
        list = Arrays.asList("a", "b", "c");
        stream = StreamUtil.parallelOfNullable(list);
        assertTrue(stream.isParallel(), "parallelOfNullable should return parallel stream");
        
        // Test ofNullable(T...)
        stream = StreamUtil.ofNullable((String[]) null);
        count = stream.count();
        assertEquals(0, count, "ofNullable should return empty stream for null array");
        
        stream = StreamUtil.ofNullable(new String[]{});
        count = stream.count();
        assertEquals(0, count, "ofNullable should return empty stream for empty array");
        
        stream = StreamUtil.ofNullable("a", "b", "c");
        count = stream.count();
        assertEquals(3, count, "ofNullable should return stream with correct elements");
    }

    /**
     * Test the collection methods of StreamUtil.
     * <p>
     * 测试StreamUtil的收集方法。
     */
    @Test
    void testCollectionMethods() {
        // Test toList
        List<String> result = StreamUtil.toList(null);
        assertTrue(result.isEmpty(), "toList should return empty list for null stream");
        
        result = StreamUtil.toList(Stream.of("a", "b", "c"));
        assertEquals(3, result.size(), "toList should return list with correct elements");
        
        // Test toSet
        Set<String> setResult = StreamUtil.toSet(null);
        assertTrue(setResult.isEmpty(), "toSet should return empty set for null stream");
        
        setResult = StreamUtil.toSet(Stream.of("a", "b", "a"));
        assertEquals(2, setResult.size(), "toSet should return set with unique elements");
        
        // Test toMap (with keyMapper and valueMapper)
        Map<String, Integer> mapResult = StreamUtil.toMap(null, (String s) -> s, (String s) -> s.length());
        assertTrue(mapResult.isEmpty(), "toMap should return empty map for null stream");
        
        mapResult = StreamUtil.toMap(Stream.of("a", "bb", "ccc"), (String s) -> s, (String s) -> s.length());
        assertEquals(3, mapResult.size(), "toMap should return map with correct size");
        assertEquals(2, mapResult.get("bb"), "toMap should return map with correct key-value pairs");
        
        // Test toMap (with merge function)
        mapResult = StreamUtil.toMap(Stream.of("a", "bb", "a"), (String s) -> s, (String s) -> s.length(), Integer::max);
        assertEquals(2, mapResult.size(), "toMap with merge function should handle duplicate keys");
        assertEquals(1, mapResult.get("a"), "toMap with merge function should use merge function for duplicate keys");
        
        // Test toListWithoutNull
        result = StreamUtil.toListWithoutNull(Stream.of("a", null, "b", null, "c"));
        assertEquals(3, result.size(), "toListWithoutNull should return list without nulls");
        
        // Test toSetWithoutNull
        setResult = StreamUtil.toSetWithoutNull(Stream.of("a", null, "b", null, "a"));
        assertEquals(2, setResult.size(), "toSetWithoutNull should return set without nulls and duplicates");
    }

    /**
     * Test the filter methods of StreamUtil.
     * <p>
     * 测试StreamUtil的过滤方法。
     */
    @Test
    void testFilterMethods() {
        // Test filterNotNull
        Stream<String> stream = StreamUtil.filterNotNull(null);
        long count = stream.count();
        assertEquals(0, count, "filterNotNull should return empty stream for null input");
        
        stream = StreamUtil.filterNotNull(Stream.of("a", null, "b", null, "c"));
        count = stream.count();
        assertEquals(3, count, "filterNotNull should filter out null elements");
    }

    /**
     * Test the map methods of StreamUtil.
     * <p>
     * 测试StreamUtil的映射方法。
     */
    @Test
    void testMapMethods() {
        // Test mapNullable
        Stream<Integer> mapStream1 = StreamUtil.mapNullable(null, String::length);
        long count = mapStream1.count();
        assertEquals(0, count, "mapNullable should return empty stream for null input");
        
        Stream<Integer> mapStream2 = StreamUtil.mapNullable(Stream.of("a", "bb", "ccc"), String::length);
        List<Integer> result = mapStream2.collect(Collectors.toList());
        assertEquals(3, result.size(), "mapNullable should return stream with mapped elements");
        assertEquals(2, result.get(1), "mapNullable should correctly map elements");
        
        // Test flatMapNullable
        Stream<String> flatMapStream1 = StreamUtil.flatMapNullable(null, (String s) -> Stream.of(s.split("")));
        count = flatMapStream1.count();
        assertEquals(0, count, "flatMapNullable should return empty stream for null input");
        
        Stream<String> flatMapStream2 = StreamUtil.flatMapNullable(Stream.of("ab", "cd"), s -> Stream.of(s.split("")));
        List<String> stringResult = flatMapStream2.collect(Collectors.toList());
        assertEquals(4, stringResult.size(), "flatMapNullable should return stream with flattened elements");
        assertEquals("a", stringResult.get(0), "flatMapNullable should return correct flattened elements");
        assertEquals("b", stringResult.get(1), "flatMapNullable should return correct flattened elements");
        assertEquals("c", stringResult.get(2), "flatMapNullable should return correct flattened elements");
        assertEquals("d", stringResult.get(3), "flatMapNullable should return correct flattened elements");
    }

    /**
     * Test the iterator and iterable methods of StreamUtil.
     * <p>
     * 测试StreamUtil的迭代器和可迭代对象方法。
     */
    @Test
    void testIteratorIterableMethods() {
        // Test fromIterator
        Iterator<String> iterator = null;
        Stream<String> stream = StreamUtil.fromIterator(iterator);
        long count = stream.count();
        assertEquals(0, count, "fromIterator should return empty stream for null iterator");
        
        iterator = Arrays.asList("a", "b", "c").iterator();
        stream = StreamUtil.fromIterator(iterator);
        count = stream.count();
        assertEquals(3, count, "fromIterator should return stream with elements from iterator");
        
        // Test fromIteratorParallel
        iterator = Arrays.asList("a", "b", "c").iterator();
        stream = StreamUtil.fromIteratorParallel(iterator);
        assertTrue(stream.isParallel(), "fromIteratorParallel should return parallel stream");
        
        // Test fromIterable
        Iterable<String> iterable = null;
        stream = StreamUtil.fromIterable(iterable);
        count = stream.count();
        assertEquals(0, count, "fromIterable should return empty stream for null iterable");
        
        iterable = Arrays.asList("a", "b", "c");
        stream = StreamUtil.fromIterable(iterable);
        count = stream.count();
        assertEquals(3, count, "fromIterable should return stream with elements from iterable");
        
        // Test fromIterableParallel
        iterable = Arrays.asList("a", "b", "c");
        stream = StreamUtil.fromIterableParallel(iterable);
        assertTrue(stream.isParallel(), "fromIterableParallel should return parallel stream");
    }

    /**
     * Test the joining methods of StreamUtil.
     * <p>
     * 测试StreamUtil的连接方法。
     */
    @Test
    void testJoiningMethods() {
        // Test join with delimiter
        Stream<String> stream = null;
        String result = StreamUtil.join(stream, ",");
        assertEquals("", result, "join should return empty string for null stream");
        
        stream = Stream.of("a", "b", "c");
        result = StreamUtil.join(stream, ",");
        assertEquals("a,b,c", result, "join should return correctly joined string with delimiter");
        
        // Test join with delimiter, prefix, and suffix
        stream = Stream.of("a", "b", "c");
        result = StreamUtil.join(stream, ",", "[", "]");
        assertEquals("[a,b,c]", result, "join should return correctly joined string with delimiter, prefix, and suffix");
    }
}