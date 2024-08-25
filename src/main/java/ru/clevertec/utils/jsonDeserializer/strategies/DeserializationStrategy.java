package ru.clevertec.utils.jsonDeserializer.strategies;

import java.lang.reflect.Field;

public interface DeserializationStrategy {
    Object deserialize(String value, Field field);
}
