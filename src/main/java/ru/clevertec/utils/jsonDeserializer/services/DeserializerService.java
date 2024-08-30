package ru.clevertec.utils.jsonDeserializer.services;

import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategy;
import ru.clevertec.utils.jsonDeserializer.factories.DeserializationStrategyFactory;
import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;
import ru.clevertec.utils.jsonDeserializer.utils.JsonParser;


import java.lang.reflect.Field;
import java.util.*;

public class DeserializerService {

    public <T> T deserialize(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("json is null or empty");
        }
        Map<String, String> jsonMap = JsonParser.parseJsonObject(json);

        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            Arrays.stream(fields)
                    .filter(field -> field.isAnnotationPresent(JsonField.class))
                    .forEach(field -> {
                        boolean wasAccessible = field.canAccess(instance);
                        if (!wasAccessible) {
                            field.setAccessible(true);
                        }
                        String jsonFieldName = field.getAnnotation(JsonField.class).value();
                        Optional.ofNullable(jsonMap.get(jsonFieldName))
                                .ifPresent(jsonValue -> {
                                    DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
                                    Object value = strategy.deserialize(jsonValue, field);
                                    try {
                                        field.set(instance, value);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException("Failed to set field value", e);
                                    }
                                });
                        if (!wasAccessible) {
                            field.setAccessible(false);
                        }
                    });
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }

    public <T> List<T> deserializeList(String jsonString, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        List<String> items = JsonParser.parseJsonArray(jsonString);

        for (String item : items) {
            list.add(deserialize(item, clazz));
        }

        return list;
    }
}