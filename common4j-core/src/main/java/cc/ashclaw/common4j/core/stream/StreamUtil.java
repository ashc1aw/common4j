// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Utility class for Stream API operations.
 * <p>
 * 用于Stream API操作的工具类。
 * <p>
 * This class provides various utility methods to simplify working with Java 8+ Streams,
 * including common stream operations, collectors, and stream creation utilities.
 * <p>
 * 此类提供各种实用方法来简化Java 8+ Streams的使用，包括常见的流操作、收集器和流创建工具。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class StreamUtil {
    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private StreamUtil() {
        throw new UnsupportedOperationException("StreamUtil cannot be instantiated.");
    }

    /**
     * Creates a stream from a nullable collection.
     * <p>
     * 从可能为空的集合创建流。
     *
     * @param <T>        the type of elements in the collection
     *                   <p>
     *                   集合中元素的类型
     * @param collection the collection, may be null
     *                   <p>
     *                   集合，可能为null
     * @return an empty stream if the collection is null or empty, otherwise a stream of the collection's elements
     *         <p>
     *         如果集合为null或空，则返回空流；否则返回集合元素的流
     */
    public static <T> Stream<T> ofNullable(Collection<T> collection) {
        return Objects.isNull(collection) ? Stream.empty() : collection.stream();
    }

    /**
     * Creates a parallel stream from a nullable collection.
     * <p>
     * 从可能为空的集合创建并行流。
     *
     * @param <T>        the type of elements in the collection
     *                   <p>
     *                   集合中元素的类型
     * @param collection the collection, may be null
     *                   <p>
     *                   集合，可能为null
     * @return an empty parallel stream if the collection is null or empty, otherwise a parallel stream of the collection's elements
     *         <p>
     *         如果集合为null或空，则返回空并行流；否则返回集合元素的并行流
     */
    public static <T> Stream<T> parallelOfNullable(Collection<T> collection) {
        return Objects.isNull(collection) ? Stream.empty() : collection.parallelStream();
    }

    /**
     * Creates a stream from a nullable array.
     * <p>
     * 从可能为空的数组创建流。
     *
     * @param <T>   the type of elements in the array
     *              <p>
     *              数组中元素的类型
     * @param array the array, may be null
     *              <p>
     *              数组，可能为null
     * @return an empty stream if the array is null or empty, otherwise a stream of the array's elements
     *         <p>
     *         如果数组为null或空，则返回空流；否则返回数组元素的流
     */
    @SafeVarargs
    public static <T> Stream<T> ofNullable(T... array) {
        return Objects.isNull(array) ? Stream.empty() : Arrays.stream(array);
    }

    /**
     * Collects a stream to a list, handling null values.
     * <p>
     * 将流收集到列表，处理null值。
     *
     * @param <T>    the type of elements in the stream
     *               <p>
     *               流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @return an empty list if the stream is null, otherwise a list containing the stream's elements
     *         <p>
     *         如果流为null，则返回空列表；否则返回包含流元素的列表
     */
    public static <T> List<T> toList(Stream<T> stream) {
        return Objects.isNull(stream) ? Collections.emptyList() : stream.collect(Collectors.toList());
    }

    /**
     * Collects a stream to a set, handling null values.
     * <p>
     * 将流收集到集合，处理null值。
     *
     * @param <T>    the type of elements in the stream
     *               <p>
     *               流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @return an empty set if the stream is null, otherwise a set containing the stream's elements
     *         <p>
     *         如果流为null，则返回空集合；否则返回包含流元素的集合
     */
    public static <T> Set<T> toSet(Stream<T> stream) {
        return Objects.isNull(stream) ? Collections.emptySet() : stream.collect(Collectors.toSet());
    }

    /**
     * Collects a stream to a map using the provided key mapper and value mapper, handling null values.
     * <p>
     * 使用提供的键映射器和值映射器将流收集到映射，处理null值。
     *
     * @param <T>        the type of elements in the stream
     *                   <p>
     *                   流中元素的类型
     * @param <K>        the type of keys in the map
     *                   <p>
     *                   映射中键的类型
     * @param <U>        the type of values in the map
     *                   <p>
     *                   映射中值的类型
     * @param stream     the stream, may be null
     *                   <p>
     *                   流，可能为null
     * @param keyMapper  a function to extract the map key from the stream element
     *                   <p>
     *                   从流元素中提取映射键的函数
     * @param valueMapper a function to extract the map value from the stream element
     *                    <p>
     *                    从流元素中提取映射值的函数
     * @return an empty map if the stream is null, otherwise a map containing the mapped elements
     *         <p>
     *         如果流为null，则返回空映射；否则返回包含映射元素的映射
     */
    public static <T, K, U> Map<K, U> toMap(Stream<T> stream, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return Objects.isNull(stream) ? Collections.emptyMap() : stream.collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * Collects a stream to a map using the provided key mapper, value mapper and merge function, handling null values.
     * <p>
     * 使用提供的键映射器、值映射器和合并函数将流收集到映射，处理null值。
     *
     * @param <T>        the type of elements in the stream
     *                   <p>
     *                   流中元素的类型
     * @param <K>        the type of keys in the map
     *                   <p>
     *                   映射中键的类型
     * @param <U>        the type of values in the map
     *                   <p>
     *                   映射中值的类型
     * @param stream     the stream, may be null
     *                   <p>
     *                   流，可能为null
     * @param keyMapper  a function to extract the map key from the stream element
     *                   <p>
     *                   从流元素中提取映射键的函数
     * @param valueMapper a function to extract the map value from the stream element
     *                    <p>
     *                    从流元素中提取映射值的函数
     * @param mergeFunction a function to resolve collisions between values associated with the same key
     *                      <p>
     *                      用于解决与同一键关联的值之间冲突的函数
     * @return an empty map if the stream is null, otherwise a map containing the mapped elements
     *         <p>
     *         如果流为null，则返回空映射；否则返回包含映射元素的映射
     */
    public static <T, K, U> Map<K, U> toMap(Stream<T> stream, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
        return Objects.isNull(stream) ? Collections.emptyMap() : stream.collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    /**
     * Filters non-null elements from a stream.
     * <p>
     * 从流中过滤掉非null元素。
     *
     * @param <T>    the type of elements in the stream
     *               <p>
     *               流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @return a stream of non-null elements, or an empty stream if the input stream is null
     *         <p>
     *         非null元素的流，如果输入流为null则返回空流
     */
    public static <T> Stream<T> filterNotNull(Stream<T> stream) {
        return Objects.isNull(stream) ? Stream.empty() : stream.filter(Objects::nonNull);
    }

    /**
     * Maps elements of a stream, handling null values.
     * <p>
     * 映射流的元素，处理null值。
     *
     * @param <T>    the type of elements in the input stream
     *               <p>
     *               输入流中元素的类型
     * @param <R>    the type of elements in the output stream
     *               <p>
     *               输出流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @param mapper a function to map elements
     *               <p>
     *               映射元素的函数
     * @return a stream of mapped elements, or an empty stream if the input stream is null
     *         <p>
     *         映射元素的流，如果输入流为null则返回空流
     */
    public static <T, R> Stream<R> mapNullable(Stream<T> stream, Function<? super T, ? extends R> mapper) {
        return Objects.isNull(stream) ? Stream.empty() : stream.map(mapper);
    }

    /**
     * Flat maps elements of a stream, handling null values.
     * <p>
     * 扁平映射流的元素，处理null值。
     *
     * @param <T>    the type of elements in the input stream
     *               <p>
     *               输入流中元素的类型
     * @param <R>    the type of elements in the output stream
     *               <p>
     *               输出流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @param mapper a function to map elements to streams
     *               <p>
     *               将元素映射到流的函数
     * @return a stream of flattened elements, or an empty stream if the input stream is null
     *         <p>
     *         扁平元素的流，如果输入流为null则返回空流
     */
    public static <T, R> Stream<R> flatMapNullable(Stream<T> stream, Function<? super T, ? extends Stream<? extends R>> mapper) {
        return Objects.isNull(stream) ? Stream.empty() : stream.flatMap(mapper);
    }

    /**
     * Creates a stream from an iterator.
     * <p>
     * 从迭代器创建流。
     *
     * @param <T>      the type of elements
     *                 <p>
     *                 元素的类型
     * @param iterator the iterator, may be null
     *                 <p>
     *                 迭代器，可能为null
     * @return an empty stream if the iterator is null, otherwise a stream of the iterator's elements
     *         <p>
     *         如果迭代器为null，则返回空流；否则返回迭代器元素的流
     */
    public static <T> Stream<T> fromIterator(Iterator<T> iterator) {
        return Objects.isNull(iterator) ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    /**
     * Creates a parallel stream from an iterator.
     * <p>
     * 从迭代器创建并行流。
     *
     * @param <T>      the type of elements
     *                 <p>
     *                 元素的类型
     * @param iterator the iterator, may be null
     *                 <p>
     *                 迭代器，可能为null
     * @return an empty parallel stream if the iterator is null, otherwise a parallel stream of the iterator's elements
     *         <p>
     *         如果迭代器为null，则返回空并行流；否则返回迭代器元素的并行流
     */
    public static <T> Stream<T> fromIteratorParallel(Iterator<T> iterator) {
        return Objects.isNull(iterator) ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), true);
    }

    /**
     * Creates a stream from an iterable.
     * <p>
     * 从可迭代对象创建流。
     *
     * @param <T>      the type of elements
     *                 <p>
     *                 元素的类型
     * @param iterable the iterable, may be null
     *                 <p>
     *                 可迭代对象，可能为null
     * @return an empty stream if the iterable is null, otherwise a stream of the iterable's elements
     *         <p>
     *         如果可迭代对象为null，则返回空流；否则返回可迭代对象元素的流
     */
    public static <T> Stream<T> fromIterable(Iterable<T> iterable) {
        return Objects.isNull(iterable) ? Stream.empty() : StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Creates a parallel stream from an iterable.
     * <p>
     * 从可迭代对象创建并行流。
     *
     * @param <T>      the type of elements
     *                 <p>
     *                 元素的类型
     * @param iterable the iterable, may be null
     *                 <p>
     *                 可迭代对象，可能为null
     * @return an empty parallel stream if the iterable is null, otherwise a parallel stream of the iterable's elements
     *         <p>
     *         如果可迭代对象为null，则返回空并行流；否则返回可迭代对象元素的并行流
     */
    public static <T> Stream<T> fromIterableParallel(Iterable<T> iterable) {
        return Objects.isNull(iterable) ? Stream.empty() : StreamSupport.stream(iterable.spliterator(), true);
    }

    /**
     * Collects a stream to a list, excluding null values.
     * <p>
     * 将流收集到列表，排除null值。
     *
     * @param <T>    the type of elements in the stream
     *               <p>
     *               流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @return an empty list if the stream is null, otherwise a list containing non-null elements
     *         <p>
     *         如果流为null，则返回空列表；否则返回包含非null元素的列表
     */
    public static <T> List<T> toListWithoutNull(Stream<T> stream) {
        return filterNotNull(stream).collect(Collectors.toList());
    }

    /**
     * Collects a stream to a set, excluding null values.
     * <p>
     * 将流收集到集合，排除null值。
     *
     * @param <T>    the type of elements in the stream
     *               <p>
     *               流中元素的类型
     * @param stream the stream, may be null
     *               <p>
     *               流，可能为null
     * @return an empty set if the stream is null, otherwise a set containing non-null elements
     *         <p>
     *         如果流为null，则返回空集合；否则返回包含非null元素的集合
     */
    public static <T> Set<T> toSetWithoutNull(Stream<T> stream) {
        return filterNotNull(stream).collect(Collectors.toSet());
    }

    /**
     * Joins string elements of a stream with a delimiter.
     * <p>
     * 使用分隔符连接流中的字符串元素。
     *
     * @param stream    the stream of strings, may be null
     *                  <p>
     *                  字符串流，可能为null
     * @param delimiter the delimiter to use
     *                  <p>
     *                  要使用的分隔符
     * @return an empty string if the stream is null or empty, otherwise a joined string
     *         <p>
     *         如果流为null或空，则返回空字符串；否则返回连接后的字符串
     */
    public static String join(Stream<String> stream, CharSequence delimiter) {
        return Objects.isNull(stream) ? "" : stream.collect(Collectors.joining(delimiter));
    }

    /**
     * Joins string elements of a stream with a delimiter, prefix, and suffix.
     * <p>
     * 使用分隔符、前缀和后缀连接流中的字符串元素。
     *
     * @param stream    the stream of strings, may be null
     *                  <p>
     *                  字符串流，可能为null
     * @param delimiter the delimiter to use
     *                  <p>
     *                  要使用的分隔符
     * @param prefix    the prefix to use
     *                  <p>
     *                  要使用的前缀
     * @param suffix    the suffix to use
     *                  <p>
     *                  要使用的后缀
     * @return an empty string if the stream is null or empty, otherwise a joined string
     *         <p>
     *         如果流为null或空，则返回空字符串；否则返回连接后的字符串
     */
    public static String join(Stream<String> stream, CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return Objects.isNull(stream) ? "" : stream.collect(Collectors.joining(delimiter, prefix, suffix));
    }
}
