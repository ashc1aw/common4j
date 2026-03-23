// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.web.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilTest {

    @Test
    void testToJsonWithNull() {
        assertEquals("null", JsonUtil.toJson(null));
    }

    @Test
    void testToJsonWithString() {
        String result = JsonUtil.toJson("hello");
        assertEquals("\"hello\"", result);
    }

    @Test
    void testToJsonWithMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put("number", 42);
        String result = JsonUtil.toJson(map);
        assertTrue(result.contains("\"key\""));
        assertTrue(result.contains("\"value\""));
        assertTrue(result.contains("42"));
    }

    @Test
    void testToJsonWithList() {
        List<String> list = List.of("a", "b", "c");
        String result = JsonUtil.toJson(list);
        assertTrue(result.contains("\"a\""));
        assertTrue(result.contains("\"b\""));
        assertTrue(result.contains("\"c\""));
    }

    @Test
    void testToPrettyJsonWithNull() {
        assertEquals("null", JsonUtil.toPrettyJson(null));
    }

    @Test
    void testToPrettyJsonWithObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("value", 123);
        String result = JsonUtil.toPrettyJson(map);
        assertTrue(result.contains("\"name\""));
        assertTrue(result.contains("\"test\""));
        assertTrue(result.contains("\n"));
    }

    @Test
    void testFromJsonWithClass() {
        String json = "{\"name\":\"test\",\"age\":25}";
        TestBean bean = JsonUtil.fromJson(json, TestBean.class);
        assertEquals("test", bean.getName());
        assertEquals(25, bean.getAge());
    }

    @Test
    void testFromJsonWithNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.fromJson(null, TestBean.class));
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.fromJson("", TestBean.class));
    }

    @Test
    void testFromJsonWithTypeReference() {
        String json = "[{\"name\":\"a\",\"age\":1},{\"name\":\"b\",\"age\":2}]";
        List<TestBean> list = JsonUtil.fromJson(json, new TypeReference<List<TestBean>>() {});
        assertEquals(2, list.size());
        assertEquals("a", list.get(0).getName());
        assertEquals("b", list.get(1).getName());
    }

    @Test
    void testFromJsonWithTypeReferenceNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.fromJson(null, new TypeReference<List<TestBean>>() {}));
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.fromJson("", new TypeReference<Map<String, Object>>() {}));
    }

    @Test
    void testToMapFromJson() {
        String json = "{\"key\":\"value\",\"number\":42}";
        Map<String, Object> map = JsonUtil.toMap(json);
        assertEquals("value", map.get("key"));
        assertEquals(42, map.get("number"));
    }

    @Test
    void testToMapFromJsonNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.toMap(null));
        assertThrows(IllegalArgumentException.class, () -> JsonUtil.toMap(""));
    }

    @Test
    void testToMapFromObject() {
        TestBean bean = new TestBean();
        bean.setName("test");
        bean.setAge(30);
        Map<String, Object> map = JsonUtil.toMap(bean);
        assertEquals("test", map.get("name"));
        assertEquals(30, map.get("age"));
    }

    @Test
    void testToMapFromObjectNull() {
        Map<String, Object> map = JsonUtil.toMap((Object) null);
        assertNotNull(map);
        assertTrue(map.isEmpty());
    }

    public static class TestBean {
        private String name;
        private int age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}