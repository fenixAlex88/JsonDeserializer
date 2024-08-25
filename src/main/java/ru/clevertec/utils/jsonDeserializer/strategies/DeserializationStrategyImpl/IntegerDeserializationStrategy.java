package ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl;

import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategy;

import java.lang.reflect.Field;

public class IntegerDeserializationStrategy implements DeserializationStrategy {
    @Override
    public Object deserialize(String value, Field field) {
        return Integer.parseInt(value);
    }
}
