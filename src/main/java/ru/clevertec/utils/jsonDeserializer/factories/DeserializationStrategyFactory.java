package ru.clevertec.utils.jsonDeserializer.factories;

import ru.clevertec.utils.jsonDeserializer.strategies.*;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl.*;

import java.lang.reflect.Field;
import java.util.List;

public class DeserializationStrategyFactory {
    public static DeserializationStrategy getStrategy(Field field) {
        Class<?> type = field.getType();
        if (type.equals(String.class)) {
            return new StringDeserializationStrategy();
        } else if (type.equals(int.class) || type.equals(Integer.class)) {
            return new IntegerDeserializationStrategy();
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return new BooleanDeserializationStrategy();
        } else if (type.equals(List.class)) {
            return new ListDeserializationStrategy();
        } else {
            return new ObjectDeserializationStrategy();
        }
    }
}
