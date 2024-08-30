package ru.clevertec.utils.jsonDeserializer;


import ru.clevertec.utils.jsonDeserializer.services.DeserializerService;

import java.util.List;

public class JsonDeserializer {

    private JsonDeserializer(){}

    private static final DeserializerService deserializerService = new DeserializerService();

    public static <T> T deserialize(String jsonString, Class<T> clazz) {
        return deserializerService.deserialize(jsonString, clazz);
    }

    public static <T> List<T> deserializeList(String jsonString, Class<T> clazz) {
        return deserializerService.deserializeList(jsonString, clazz);
    }
}