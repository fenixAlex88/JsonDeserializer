package ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl;

import ru.clevertec.utils.jsonDeserializer.JsonDeserializer;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListDeserializationStrategy implements DeserializationStrategy {

    @Override
    public Object deserialize(String value, Field field) {
        Type genericType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        return JsonDeserializer.deserializeList(value, (Class<?>) genericType);
    }
}
