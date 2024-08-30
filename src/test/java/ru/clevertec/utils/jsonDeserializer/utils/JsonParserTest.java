package ru.clevertec.utils.jsonDeserializer.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSON Parser util class tests")
public class JsonParserTest {

    @Test
    @DisplayName("Test parsing a simple JSON object")
    public void testParseJsonObject() {
        String jsonString = """
                {
                "name": "John",
                "age": "30",
                "isStudent": "true"
                }
                """;

        Map<String, String> result = JsonParser.parseJsonObject(jsonString);

        assertAll("jsonObject",
                () -> assertEquals(3, result.size()),
                () -> assertEquals("\"John\"", result.get("name")),
                () -> assertEquals("\"30\"", result.get("age")),
                () -> assertEquals("\"true\"", result.get("isStudent"))
        );
    }

    @Test
    @DisplayName("Test parsing a JSON object with a nested object")
    public void testParseJsonObjectWithNestedObject() {
        String jsonString = """
                {
                "name": "John",
                "address": {"city":"New York"}
                }
                """;

        Map<String, String> result = JsonParser.parseJsonObject(jsonString);

        assertAll("jsonObjectWithNestedObject",
                () -> assertEquals(2, result.size()),
                () -> assertEquals("\"John\"", result.get("name")),
                () -> assertEquals("{\"city\":\"New York\"}", result.get("address"))
        );
    }

    @Test
    @DisplayName("Test parsing a JSON object with an array")
    public void testParseJsonObjectWithArray() {
        String jsonString = """
                {
                "name": "John",
                "courses": ["Math","Science"]
                }
                """;

        Map<String, String> result = JsonParser.parseJsonObject(jsonString);

        assertAll("jsonObjectWithArray",
                () -> assertEquals(2, result.size()),
                () -> assertEquals("\"John\"", result.get("name")),
                () -> assertEquals("[\"Math\",\"Science\"]", result.get("courses"))
        );
    }

    @Test
    @DisplayName("Test parsing an invalid JSON object")
    public void testParseInvalidJsonObject() {
        String invalidJsonString = """
                {
                "name": "John",
                "age": "30",
                "isStudent": "true"
                """;

        assertThrows(IllegalArgumentException.class, () -> JsonParser.parseJsonObject(invalidJsonString));
    }

    @Test
    @DisplayName("Test parsing a simple JSON array")
    public void testParseJsonArray() {
        String jsonString = """
                [
                "Math",
                "Science",
                "History"
                ]
                """;

        List<String> result = JsonParser.parseJsonArray(jsonString);

        assertAll("jsonArray",
                () -> assertEquals(3, result.size()),
                () -> assertEquals("\"Math\"", result.get(0)),
                () -> assertEquals("\"Science\"", result.get(1)),
                () -> assertEquals("\"History\"", result.get(2))
        );
    }

    @Test
    @DisplayName("Test parsing a JSON array with objects")
    public void testParseJsonArrayWithObjects() {
        String jsonString = """
                [
                {"title":"Math"},
                {"title":"Science"}
                ]
                """;

        List<String> result = JsonParser.parseJsonArray(jsonString);

        assertAll("jsonArrayWithObjects",
                () -> assertEquals(2, result.size()),
                () -> assertEquals("{\"title\":\"Math\"}", result.get(0)),
                () -> assertEquals("{\"title\":\"Science\"}", result.get(1))
        );
    }

    @Test
    @DisplayName("Test parsing an invalid JSON array")
    public void testParseInvalidJsonArray() {
        String invalidJsonString = """
                [
                "Math",
                "Science",
                "History"
                """;

        assertThrows(IllegalArgumentException.class, () -> JsonParser.parseJsonArray(invalidJsonString));
    }

    @Test
    @DisplayName("Test parsing a JSON object with an empty value")
    public void testParseJsonObjectWithEmptyValue() {
        String jsonString = """
                {
                "name": "John",
                "courses": ""
                }
                """;

        Map<String, String> result = JsonParser.parseJsonObject(jsonString);

        assertAll("objectWithEmptyValue",
                () -> assertEquals(2, result.size()),
                () -> assertEquals("\"John\"", result.get("name")),
                () -> assertEquals("\"\"", result.get("courses"))
        );
    }

    @Test
    @DisplayName("Test parsing a JSON array with an empty value")
    public void testParseJsonArrayWithEmptyValue() {
        String jsonString = """
                 [
                 "Math",
                 "Science",
                 ]
                """;

        List<String> result = JsonParser.parseJsonArray(jsonString);

        assertAll("jsonArrayWithEmptyValue",
                () -> assertEquals(2, result.size()),
                () -> assertEquals("\"Math\"", result.get(0)),
                () -> assertEquals("\"Science\"", result.get(1))
        );
    }
}