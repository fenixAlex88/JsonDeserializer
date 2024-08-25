package ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl;

import ru.clevertec.utils.jsonDeserializer.JsonDeserializer;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategy;

import java.lang.reflect.Field;

public class ObjectDeserializationStrategy implements DeserializationStrategy {

    @Override
    public Object deserialize(String value, Field field) {
        return JsonDeserializer.deserialize(value, field.getType());
    }
}
