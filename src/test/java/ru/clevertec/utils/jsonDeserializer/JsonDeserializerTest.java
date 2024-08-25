package ru.clevertec.utils.jsonDeserializer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSON Deserializer util class tests")
public class JsonDeserializerTest {

    @Test
    @DisplayName("Test deserializing a valid JSON string")
    public void testDeserialize() {
        String jsonString = """
                {
                "name": "John",
                "age": 30,
                "isStudent": true,
                "address": {
                "city": "New York"
                },
                "courses": [
                {
                "title": "Math"
                },
                {
                "title": "Science"
                }
                ]
                }
                """;
        Person person = JsonDeserializer.deserialize(jsonString, Person.class);

        assertAll("person",
                () -> assertNotNull(person),
                () -> assertEquals("John", person.getName()),
                () -> assertEquals(30, person.getAge()),
                () -> assertTrue(person.isStudent()),
                () -> assertEquals("New York", person.getAddress().getCity()),
                () -> assertEquals("Math", person.getCourses().get(0).getTitle()),
                () -> assertEquals("Science", person.getCourses().get(1).getTitle())
        );
    }

    @Test
    @DisplayName("Test deserializing JSON with empty objects")
    public void testDeserializeEmptyJsonObjects() {
        String jsonString = """
                {
                "": "",
                "": {
                "": ""
                },
                "": [
                {
                "": ""
                },
                {
                "": ""
                }
                ]
                }
                """;
        Person person = JsonDeserializer.deserialize(jsonString, Person.class);

        assertAll("person",
                () -> assertNotNull(person),
                () -> assertNull(person.getName()),
                () -> assertEquals(0, person.getAge()),
                () -> assertFalse(person.isStudent()),
                () -> assertNull(person.getAddress()),
                () -> assertNull(person.getCourses())
        );
    }

    @Test
    @DisplayName("Test deserializing an empty JSON string")
    public void testDeserializeEmptyString() {
        String jsonString = "";

        assertThrows(IllegalArgumentException.class, () -> {
            JsonDeserializer.deserialize(jsonString, Person.class);
        });
    }

    @Test
    @DisplayName("Test deserializing a null JSON string")
    public void testDeserializeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonDeserializer.deserialize(null, Person.class);
        });
    }

    @Test
    @DisplayName("Test deserializing an invalid JSON string")
    public void testDeserializeWithInvalidJson() {
        String invalidJsonString = """
                {
                "name": "John",
                "age": "thirty",
                "isStudent": "yes"
                }
                """;
        assertThrows(RuntimeException.class, () -> {
            JsonDeserializer.deserialize(invalidJsonString, Person.class);
        });
    }

    @Test
    @DisplayName("Test deserializing JSON with invalid array format")
    public void testDeserializeWithInvalidArray() {
        String invalidArrayJsonString = """
                {
                "name": "John",
                "age": 30,
                "isStudent": true,
                "courses": "Math, Science"
                }
                """;
        assertThrows(RuntimeException.class, () -> {
            JsonDeserializer.deserialize(invalidArrayJsonString, Person.class);
        });
    }

    @Test
    @DisplayName("Test deserializing JSON with invalid object format")
    public void testDeserializeWithInvalidObject() {
        String invalidObjectJsonString = """
                {
                "name": "John",
                "age": 30,
                "isStudent": true,
                "address": "New York"
                }
                """;
        assertThrows(RuntimeException.class, () -> {
            JsonDeserializer.deserialize(invalidObjectJsonString, Person.class);
        });
    }

    @Test
    @DisplayName("Test deserializing JSON with syntax error")
    public void testDeserializeThrowsException() {
        String invalidJsonString = """
                {
                "name": "John",
                "age": "thirty"
                "isStudent": false,
                }
                """;

        assertThrows(RuntimeException.class, () -> {
            JsonDeserializer.deserialize(invalidJsonString, Person.class);
        });
    }
}